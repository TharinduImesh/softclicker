/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author THARU
 */
public class ConnectionThread extends Thread {

    private Socket client;
    private int id;
    public ConnectionThread(Socket socket, int id){
        this.client = socket;
        this.id = id;
    }

    /*
    * read message from mobile app and send acknoladgement message
    */
    @Override
    public void run (){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(),true);
            String fromClient;
            if((fromClient = in.readLine()) != null){
                if(fromClient.equalsIgnoreCase("110228P:3")){
                    System.out.println("from Client: " + fromClient);
                    out.println("ACK: accept answer from client by thread "+this.id);
                }
                else{
                    out.println("ACK: unauthorized access");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

