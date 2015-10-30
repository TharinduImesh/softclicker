/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import model.FileIOOperation;
import model.Utils;

/**
 *
 * @author THARU
 */
public class SaveAnswersController {
    
    public void save(){
        FileIOOperation fileIO = new FileIOOperation();
        fileIO.writeInToFile(Utils.getData());
    }
}
