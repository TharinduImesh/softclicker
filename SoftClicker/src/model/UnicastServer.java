/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import view.ServerWindow;

/**
 *
 * @author THARU
 */
public class UnicastServer extends Thread{
    private ServerSocket serverSocket;
    private int count = 0;
    private ServerWindow mainWindow;
    private boolean shouldRun;

    public UnicastServer(ServerWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    
    public void mainServerProcess() {
        shouldRun = true;
//        int serverPort = 3000;
//        try {
            System.out.println("Starting Server");
            serverSocket = Utils.getServerSocket();//new ServerSocket(serverPort);

            while (shouldRun) {
                System.out.println("Waiting for request");
                try {
                    Socket client = serverSocket.accept();
                    count++;
                    System.out.println("Processing request "+count);
                    
                    if(Utils.isServerState()){
                        /*
                        * create separate thread to deal with each mobile client
                        */
                        System.out.println("new answer");
                        UnicastConnectionThread thread = new UnicastConnectionThread(client, count, Utils.getData(),mainWindow);
                        thread.start();
                    }
                    else{
                        System.out.println("server down");
                        UnicastConnectionThread thread = new UnicastConnectionThread(client, count, false);
                        thread.start();
                    }
                    
                } catch (IOException ioe) {
                    System.out.println("Error accepting connection");
                    ioe.printStackTrace();
                }
            }
//        } catch (IOException e) {
//            System.out.println("Error starting Server on " + serverPort);
//            e.printStackTrace();
//        }
    }

    /*
    * to stop server=
    */
    public void stopServer(){
        try {
            this.shouldRun = false;
            serverSocket.close();
            stop();
        } catch (IOException ex) {
        }
    }

    @Override
    public void run () {
        mainServerProcess();        
    }
}
