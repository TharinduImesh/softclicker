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
import java.util.ArrayList;

/**
 *
 * @author THARU
 */
public class ConnectionThread extends Thread {

    private Socket client;
    private int id;
    private String ACKMessage;
    private Answer answer;
    private final ArrayList<Answer> data;
    
    public ConnectionThread(Socket socket, int id, ArrayList<Answer> data){
        this.client = socket;
        this.id = id;
        this.data = data;
        this.answer = new Answer();
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
                decodeClientMessage(fromClient);
                
                System.out.println("from Client: " + fromClient);
                ACKMessage = "";
                ACKMessage = "ACK".concat(answer.getId()).concat(answer.getQuestionNo());
                out.println(ACKMessage);
                saveAnswer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void decodeClientMessage(String message){
        /*Format of reply message from client:
                 *[msgLength] [MessageType] [clientMAC] [studentID] [Answer][QuestionNumber]
                 */
        
        answer.setId("110228P");
        answer.setAnswer("1");
        answer.setQuestionNo("1");
        answer.setMac("123456789809");
    }
    
    private void saveAnswer(){
        boolean isAvailable = false;
        for(int i = 0; i < data.size();i++){
            if(data.get(i).getId().equals(answer.getId())){
                isAvailable = true;
                data.add(i, answer);
                break;
            }
        }
        
        if(!isAvailable){
            data.add(answer);
        }
    }
}

