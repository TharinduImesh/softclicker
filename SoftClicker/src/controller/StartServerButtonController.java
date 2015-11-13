/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import Codec.Extractor;
import model.BroadcastServer;

/**
 *
 * @author THARU
 */
/*
* handle broadcast server functions
*/
public class StartServerButtonController {
    BroadcastServer broadcastServer;

    public StartServerButtonController() {
        broadcastServer = new BroadcastServer();                                // instantiate broadcast server
    }    
    
    // start broad cast server 
    public boolean startBroadcastSever(){
        // if broadcast server is not instantiate, instantiate it
        if(broadcastServer == null || !broadcastServer.isAlive()){
            broadcastServer = new BroadcastServer();
        }   
        
        // check, application is conncted to correct AP
        if(Extractor.isConnected()){
            broadcastServer.start();                                            // if connect start broadcast server
            return true;
        }
        else{
            return false;       
        }
    }
    
    // stop broadcst server
    public void stopBroadcastSever(){
        broadcastServer.stopBroadcastServer();
    }
    
    // check broadcst server is still running
    public boolean isBroadcastSeverAlive(){
        return broadcastServer.isAlive();
    }
    
}
