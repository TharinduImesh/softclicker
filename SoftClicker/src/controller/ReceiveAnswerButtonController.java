/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import model.UnicastServer;

/**
 *
 * @author THARU
 */
public class ReceiveAnswerButtonController {

    UnicastServer unicastServer;
    public ReceiveAnswerButtonController() {
        unicastServer = new UnicastServer();
    }       
    
    public void startUnicastSever(){
        unicastServer.start();
    }
    
    public void stopUnicastSever(){
        unicastServer.stopServer();
    }
    
    public boolean isUnicastSeverAlive(){
        return unicastServer.isAlive();
    }
}
