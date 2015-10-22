/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import com.sun.corba.se.spi.activation.Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MainWindow;

/**
 *
 * @author THARU
 */
public class UnicastServer extends Thread{
    private ServerSocket serverSocket;
    private int count = 0;
    private MainWindow mainWindow;
    private int answerCount;
    private boolean shouldRun;

    public UnicastServer(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    
    public void mainServerProcess() {
        shouldRun = true;
        int serverPort = 3000;
        try {
            System.out.println("Starting Server");
            serverSocket = new ServerSocket(serverPort);

            while (shouldRun) {
                System.out.println("Waiting for request");
                try {
                    Socket client = serverSocket.accept();
                    count++;
                    System.out.println("Processing request "+count);
                    /*
                    * create separate thread to deal with each mobile client
                    */
                    ConnectionThread thread = new ConnectionThread(client, count);
                    thread.start();
                    answerCount++;
                    updateAnswerCount(answerCount);
                } catch (IOException ioe) {
                    System.out.println("Error accepting connection");
                    ioe.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Error starting Server on " + serverPort);
            e.printStackTrace();
        }
    }

    /*
    * update count of received answers 
    */
    public void updateAnswerCount(int count){
        mainWindow.getCountVariable().setText(count+"");
    }

    /*
    * to stop server
    */
    public void stopServer(){
        try {
            this.shouldRun = false;
            serverSocket.close();
            stop();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run () {
        mainServerProcess();        
    }
}
