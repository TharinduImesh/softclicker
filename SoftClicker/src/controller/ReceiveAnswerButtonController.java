/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import model.UnicastServer;
import view.MainWindow;

/**
 *
 * @author THARU
 */
public class ReceiveAnswerButtonController {

    UnicastServer unicastServer;
    public ReceiveAnswerButtonController(MainWindow mainWindow) {
        unicastServer = new UnicastServer(mainWindow);
    }       
    
    public void startUnicastSever(MainWindow mainWindow){
        
        if(unicastServer == null || !unicastServer.isAlive()){
            unicastServer = new UnicastServer(mainWindow);
        }
        
        unicastServer.start();
    }
    
    public void stopUnicastSever(){
        unicastServer.stopServer();
    }
    
    public boolean isUnicastSeverAlive(){
        return unicastServer.isAlive();
    }
}
