package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;
import view.ServerWindow;
import Codec.*;

/**
 *
 * @author THARU
 */
public class UnicastConnectionThread extends Thread {

    private final Socket client;
    private final int id;
    private String ACKMessage;
    //private Answer answer;
    private RespondMessage respondMessage;
    private Hashtable<String,RespondMessage> data;
    private final boolean isAvailable;
    private ServerWindow mainWindow;
    
    public UnicastConnectionThread(Socket socket, int id, Hashtable<String,RespondMessage> data, ServerWindow mainWindow){
        this.client = socket;
        this.id = id;
        this.data = data;
        //this.answer = new Answer();
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
            byte []fromClient = (byte[]) oin.readObject();
            //String fromClient = oin.readObject().toString();
            byte []ack;
            System.out.println("before get answer");
            if(fromClient != null && isAvailable){   
                // decode message from mobile app
                respondMessage = (RespondMessage)Codec.DecodeMessage(fromClient);
                //System.out.println("from Client: " + fromClient);
                
                ack = Codec.EncodeAcknowledgementMessage(respondMessage.getStudentID(), respondMessage.getQuestionNumber()); 
                System.out.println("getting completed");
                dout.writeObject(ack);                                   // send acknowledgement message
                System.out.println("ack sent");
                saveAnswer(respondMessage.getStudentID());                                     // sava answer
                System.out.println("saved");
            }
            else{
                ack = Codec.EncodeErrorMessage(Keys.ERROR_SERVICE_UNAVAILABLE);
                
                //ACKMessage = "";                                                // if server has stopped by lecturer 
                //ACKMessage = "ACK,Server has stopped";                          // send server stopped acknoledgement
                dout.writeObject(ack);
                System.out.println("ack......"+ack.toString());
            }
            
            oin.close();
            dout.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /*
    private void decodeClientMessage(String message){
        //Format of reply message from client:
          //[msgLength] [MessageType] [clientMAC] [studentID] [Answer][QuestionNumber]
                 
        String [] temp = message.split(",");
        answer.setId(temp[1]);
        answer.setAnswer(Integer.parseInt(temp[3]));
        answer.setQuestionNo(temp[2]);
        answer.setMac(temp[0]);
    }*/
    
    private void saveAnswer(String id){
        if(!data.containsKey(id)){
            updateLiveCount();
        }
        
        data.put(respondMessage.getStudentID(), respondMessage);
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

