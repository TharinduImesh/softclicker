/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import Codec.*;
import javax.swing.JOptionPane;

/**
 *
 * @author THARU
 */
/*
* used for broadcast server ip and port numner to clients 
*/
public class BroadcastServer extends Thread{
    private ServerSocket serverSocket;
    public static DatagramSocket datagramSocket;
    private boolean shouldRun;
    
    // create unicast server socket
    public void createSockets(){
        try {
            serverSocket = new ServerSocket(0);
            Utils.setServerSocket(serverSocket);
        } catch (IOException ex) {
            ex.printStackTrace();
//            Logger.getLogger(BroadcastServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    * broadcast the message which contains unicast server IP address and port number 
    */
    public void broadcasting() {
        this.shouldRun = true;
        createSockets();
        String ipAddress = Extractor.getIP();
        // broadcast message type :  [MessageType] [serverIP] [serverPort] [QuestionNumber]
        if(!ipAddress.equals("NOT_SET")){
            byte []broadcastMessage = Codec.EncodeMultiCastMessage(ipAddress, serverSocket.getLocalPort(), Utils.getQuestionCount()+1);

            try {
                datagramSocket = new DatagramSocket(54000);                     // create datagram socket which is used to broadcast
                while(this.shouldRun) {
                    try {
                        datagramSocket.send(new DatagramPacket(broadcastMessage,broadcastMessage.length ,InetAddress.getByName(Extractor.getBroadcast()), 54000));
                        sleep(500);
                    }
                    catch (IOException | InterruptedException i){
                        i.printStackTrace();
                    }
                }
            }
            catch(SocketException s){
                s.printStackTrace();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, 
                    "Connection problem. Please check your PC connects to correct access point");
        }
    }

    /*
    * stop broadcasting
    */
    public void stopBroadcastServer(){
        this.shouldRun = false;
        if(datagramSocket != null){
            datagramSocket.close();
        }
        stop();
    }

    @Override
    public void run () {
        broadcasting();
    }
}
