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
    
    public void startBroadcastSever(){
        if(broadcastServer == null || !broadcastServer.isAlive()){
            broadcastServer = new BroadcastServer();
        }        
        
        //starts broadcast server
        broadcastServer.start();
    }
    
    public void stopBroadcastSever(){
        //stops broadcast server
        broadcastServer.stopBroadcastServer();
    }
    
    public boolean isBroadcastSeverAlive(){
        return broadcastServer.isAlive();
    }
}
