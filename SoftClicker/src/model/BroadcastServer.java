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
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Enumeration;

/**
 *
 * @author THARU
 */
/*
* used for broadcast server ip and port numner to clients 
*/
public class BroadcastServer extends Thread{
    private ServerSocket serverSocket;
    private int count = 0;

    public static DatagramSocket datagramSocket;
    public static byte buffer[] = new byte[1024];
    private boolean shouldRun;

    /*
    * find IP address and broadcast address of network
    */
    public String[] getIPAddress(){
        System.setProperty("java.net.preferIPv4Stack", "true");
        System.out.println(java.lang.System.getProperty("java.net.preferIPv4Stack"));
        String broadcastAddress ="";
        String ipAddress ="";
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback())
                    continue;    // Don't want to broadcast to the loopback interface
                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    InetAddress broadcast = interfaceAddress.getBroadcast();
                    InetAddress ip = interfaceAddress.getAddress();
                    if (broadcast == null)
                        continue;
                    
                    broadcastAddress = broadcast.toString().substring(1);
                    System.out.println(broadcastAddress);
                    ipAddress = ip.toString();
                }
            }
        }
        catch (SocketException s){
            s.printStackTrace();
        }
        return new String[]{ipAddress,broadcastAddress};
    }

    /*
    * broadcast the message which contains server IP address and port number 
    */
    public void broadcasting() {
        this.shouldRun = true;
        String [] addresses = getIPAddress();
        // broadcast message type :  [MessageType] [serverIP] [serverPort] [QuestionNumber]
        String broadcastMessage = "BROADCAST".concat(addresses[0]).concat("3000").concat("1");
        try {
            datagramSocket = new DatagramSocket();
            while(this.shouldRun) {
                try {
                    buffer = broadcastMessage.getBytes();
                    datagramSocket.send(new DatagramPacket(buffer,broadcastMessage.length() ,InetAddress.getByName("192.168.0.255"), 8080));
    //                InetAddress.getByName(addresses[1])InetAddress.getLocalHost()
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

    /*
    * stop broadcasting
    */
    public void stopBroadcastServer(){
        this.shouldRun = false;
        datagramSocket.close();
        stop();
    }

    @Override
    public void run () {
        broadcasting();
    }
}
