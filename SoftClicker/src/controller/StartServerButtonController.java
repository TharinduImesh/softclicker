/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import model.BroadcastServer;

/**
 *
 * @author THARU
 */
public class StartServerButtonController {
    BroadcastServer broadcastServer;

    public StartServerButtonController() {
        broadcastServer = new BroadcastServer();
    }    
    
    public boolean startBroadcastSever(){
        if(broadcastServer == null || !broadcastServer.isAlive()){
            broadcastServer = new BroadcastServer();
        }   
        broadcastServer.getIPAddress();
        String [] temp = broadcastServer.getAddresses();
        if( temp[0]!= null && !temp[0].equals("")){
            broadcastServer.start();
            return true;
        }
        else{
            return false;
        }
    }
    
    public void stopBroadcastSever(){
        broadcastServer.stopBroadcastServer();
    }
    
    public boolean isBroadcastSeverAlive(){
        return broadcastServer.isAlive();
    }
    
//    public static boolean isInternetReachable(){
//        try {
//            //make a URL to a known source
//            URL url = new URL("http://www.google.com");
//
//            //open a connection to that source
//            HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();
//
//            //trying to retrieve data from the source. If there
//            //is no connection, this line will fail
//            Object objData = urlConnect.getContent();
//
//        } catch (UnknownHostException e) {
//            // TODO Auto-generated catch block
////                e.printStackTrace();
//            return false;
//        }
//        catch (IOException e) {
//            // TODO Auto-generated catch block
////                e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
    
//    public void hostpot(){
//        try {
//            ProcessBuilder builder = new ProcessBuilder(
//                    "cmd.exe", "/c", "netsh wlan show interfaces");
//            builder.redirectErrorStream(true);
//            Process p = builder.start();
//            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line;
//            while (true) {
//                line = r.readLine();
//                if (line.contains("SSID")){
//                    System.out.println(line);
//                    break;
//                }
//            }   } catch (IOException ex) {
//            Logger.getLogger(StartServerButtonController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public void broadcastip(){
//        try {
//            ProcessBuilder builder = new ProcessBuilder(
//                    "cmd.exe", "/c", "ipconfig");
//            builder.redirectErrorStream(true);
//            Process p = builder.start();
//            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line;
//            while (true) {
//                line = r.readLine();
//                if (line.contains("IPv4 Address")){
//                    System.out.println(line);
////                    break;
//                }
//                else if(line.contains("Subnet Mask")){
//                    System.out.println(line);
//                    break;
//                }
//            }   } catch (IOException ex) {
//            Logger.getLogger(StartServerButtonController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
