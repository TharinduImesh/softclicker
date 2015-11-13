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
    private final ServerWindow mainWindow;
    private boolean shouldRun;

    public UnicastServer(ServerWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    
    public void mainServerProcess() {
        shouldRun = true;
        serverSocket = Utils.getServerSocket();//new ServerSocket(serverPort);

        while (shouldRun) {
            try {
                Socket client = serverSocket.accept();

                if(Utils.isServerState()){
                    /*
                    * create separate thread to deal with each mobile client
                    */
                    UnicastConnectionThread thread = new UnicastConnectionThread(client, Utils.getData(),mainWindow);
                    thread.start();
                }
                else{
                    /*
                    * when unicastserver is down
                    */
                    UnicastConnectionThread thread = new UnicastConnectionThread(client, false);
                    thread.start();
                }

            } catch (IOException ioe) {
                    ioe.printStackTrace();
            }
        }
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
        }
    }

    @Override
    public void run () {
        mainServerProcess();        
    }
}
