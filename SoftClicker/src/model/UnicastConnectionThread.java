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
    private String ACKMessage;
    private RespondMessage respondMessage;
    private Hashtable<String,RespondMessage> data;
    private final boolean isAvailable;
    private ServerWindow mainWindow;
    
    public UnicastConnectionThread(Socket socket, Hashtable<String,RespondMessage> data, ServerWindow mainWindow){
        this.client = socket;
        this.data = data;
        this.isAvailable = true;
        this.mainWindow = mainWindow;
    }

    public UnicastConnectionThread(Socket socket, boolean isAvailable){
        this.client = socket;
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
            byte []ack;
            if(fromClient != null && isAvailable){   
                // decode message from mobile app
                respondMessage = (RespondMessage)Codec.DecodeMessage(fromClient);
                
                ack = Codec.EncodeAcknowledgementMessage(respondMessage.getStudentID(), respondMessage.getQuestionNumber()); 
                dout.writeObject(ack);                                   // send acknowledgement message
                saveAnswer(respondMessage.getStudentID());                                     // sava answer
            }
            else{
                ack = Codec.EncodeErrorMessage(Keys.ERROR_SERVICE_UNAVAILABLE);
                
                         // if server has stopped by lecturer 
                         // send server stopped acknoledgement
                dout.writeObject(ack);
            }
            
            oin.close();
            dout.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
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

