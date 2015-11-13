/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import model.UnicastServer;
import view.ServerWindow;

/**
 *
 * @author THARU
 */

/*
* handle unicast server functions
*/
public class ReceiveAnswerButtonController {

    UnicastServer unicastServer;
    public ReceiveAnswerButtonController(ServerWindow mainWindow) {
        unicastServer = new UnicastServer(mainWindow);                          // instantiate unicast server
    }       
    
    // start unicast server 
    public void startUnicastSever(ServerWindow mainWindow){
        
        // if unicast server is not instantiate yet, instantiate it
        if(unicastServer == null || !unicastServer.isAlive()){
            unicastServer = new UnicastServer(mainWindow);
        }
        
        unicastServer.start();
    }
    
    // stop unicast server
    public void stopUnicastSever(){
        unicastServer.stopServer();
    }
    
    // check unicast server is running
    public boolean isUnicastSeverAlive(){
        return unicastServer.isAlive();
    }
}
