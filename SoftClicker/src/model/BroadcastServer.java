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
    //public static byte buffer[] = new byte[1024];
    private boolean shouldRun;
//    private String [] addresses = new String[2];

    /*
    * find IP address and broadcast address of network
    */
//    public void getIPAddress(){
//        System.setProperty("java.net.preferIPv4Stack", "true");
//        String broadcastAddress ="";
//        String ipAddress ="";
//        try {
//            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
//            while (interfaces.hasMoreElements()) {
//                NetworkInterface networkInterface = interfaces.nextElement();
//                if (networkInterface.isLoopback())
//                    continue;    // Don't want to broadcast to the loopback interface
//                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
//                    InetAddress broadcast = interfaceAddress.getBroadcast();
//                    InetAddress ip = interfaceAddress.getAddress();
//                    if (broadcast == null)
//                        continue;
//                    
//                    broadcastAddress = broadcast.toString().substring(1);
//                    System.out.println(broadcastAddress);
//                    ipAddress = ip.toString();
//                }
//            }
//        }
//        catch (SocketException s){
//            s.printStackTrace();
//        }
//        
////        addresses [0] = ipAddress;
////        addresses [1] = broadcastAddress;
////        return new String[]{ipAddress,broadcastAddress};
//    }
//
//    public String[] getAddresses() {
//        return addresses;
//    }
    
    public void createSockets(){
        try {
            serverSocket = new ServerSocket(0);
            Utils.setServerSocket(serverSocket);
//            System.out.println("server socket listening on port: " + serverSocket.getLocalPort());
        } catch (IOException ex) {
            ex.printStackTrace();
//            Logger.getLogger(BroadcastServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    * broadcast the message which contains server IP address and port number 
    */
    public void broadcasting() {
        this.shouldRun = true;
        createSockets();
        String ipAddress = Extractor.getIP();
        String ssid = Extractor.getConnectedSSID();
        // broadcast message type :  [MessageType] [serverIP] [serverPort] [QuestionNumber]
        if(!ipAddress.equals("NOT_SET") && !ssid.equals("NOT_SET")){
            byte []broadcastMessage = Codec.EncodeMultiCastMessage(ipAddress, serverSocket.getLocalPort(), Utils.getQuestionCount()+1, ssid);

            try {
                datagramSocket = new DatagramSocket(54000);
//                System.out.println("datagram listening on port: " + datagramSocket.getLocalPort());
                while(this.shouldRun) {
                    try {
                        datagramSocket.send(new DatagramPacket(broadcastMessage,broadcastMessage.length ,InetAddress.getByName("192.168.0.255"), 54000));//datagramSocket.getLocalPort()));
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
