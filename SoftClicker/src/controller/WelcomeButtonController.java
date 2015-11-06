/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

/**
 *
 * @author THARU
 */
public class WelcomeButtonController {
    public static boolean isInternetReachable(){
        try {
            //make a URL to a known source
            URL url = new URL("http://www.google.com");

            //open a connection to that source
            HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();

            //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            Object objData = urlConnect.getContent();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
//                e.printStackTrace();
            return false;
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
//                e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static String hostpot(){
        String ssid = null;
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "netsh wlan show interfaces");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line.contains("SSID")){
                    return line;
                }
            }   } catch (Exception ex) {
//            Logger.getLogger(StartServerButtonController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
        }
        
//        return ssid;
    }
}
