/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;
import view.MainWindow;

/**
 *
 * @author THARU
 */
public class UnicastConnectionThread extends Thread {

    private final Socket client;
    private final int id;
    private String ACKMessage;
    private Answer answer;
    private Hashtable<String,Answer> data;
    private final boolean isAvailable;
    private MainWindow mainWindow;
    
    public UnicastConnectionThread(Socket socket, int id, Hashtable<String,Answer> data, MainWindow mainWindow){
        this.client = socket;
        this.id = id;
        this.data = data;
        this.answer = new Answer();
        this.isAvailable = true;
        this.mainWindow = mainWindow;
    }

    public UnicastConnectionThread(Socket socket, int id, boolean isAvailable){
        this.client = socket;
        this.id = id;
        this.isAvailable = isAvailable;
    }
    
    /*
    * read message from mobile app and send acknoladgement message
    */
    @Override
    public void run (){
        try {
            ObjectInputStream  oin = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream  dout = new ObjectOutputStream(client.getOutputStream());
            String fromClient = oin.readObject().toString();
            if(fromClient != null && isAvailable){   
                decodeClientMessage(fromClient);                                // decode message from mobile app
                
                System.out.println("from Client: " + fromClient);
                ACKMessage = "";
                ACKMessage = "ACK".concat(answer.getId()).concat(answer.getQuestionNo());
                dout.writeObject(ACKMessage);                                   // send acknowledgement message
                saveAnswer(answer.getId());                                     // sava answer
            }
            else{
                ACKMessage = "";                                                // if server has stopped by lecturer 
                ACKMessage = "ACK,Server has stopped";                          // send server stopped acknoledgement
                dout.writeObject(ACKMessage);
            }
            
            oin.close();
            dout.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void decodeClientMessage(String message){
        /*Format of reply message from client:
                 *[msgLength] [MessageType] [clientMAC] [studentID] [Answer][QuestionNumber]
                 */
        String [] temp = message.split(",");
        answer.setId(temp[1]);
        answer.setAnswer(Integer.parseInt(temp[3]));
        answer.setQuestionNo(temp[2]);
        answer.setMac(temp[0]);
    }
    
    private void saveAnswer(String id){
        if(!data.containsKey(id)){
            updateLiveCount();
        }
        
        data.put(answer.getId(), answer);
    }
    
    /*
    * update real time counter in main window
    */
    public void updateLiveCount(){
        synchronized(mainWindow){
            int currentCount = Integer.parseInt(mainWindow.getCountVariable().getText());
            mainWindow.getCountVariable().setText(currentCount+1+"");
        }
    }
}

