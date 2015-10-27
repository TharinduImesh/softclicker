/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THARU
 */
public class FileIOOperation {
    
    public void writeInToFile(Hashtable<String,Answer> data){
        TempObject temp = new TempObject();
        temp.setQuestionNo(Utils.getQuestionNo());
        temp.setData(data);
        
        try{
            File file = new File("C:\\SoftclickerUOM");
            
            if (!file.exists()) {
                file.mkdir();
            }
            
            File newFile = new File("C:\\SoftclickerUOM\\"+Utils.getQuestionNo()+".txt");
            newFile.createNewFile();
            FileOutputStream fout = new FileOutputStream("C:\\SoftclickerUOM\\"+Utils.getQuestionNo()+".txt");
            ObjectOutputStream oos = new ObjectOutputStream(fout);   
            oos.writeObject(temp);
            oos.flush();
            oos.close();
            System.out.println("Done");
		   
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public ArrayList<Hashtable<String,Answer>> readOperation(){
        ArrayList<Hashtable<String,Answer>> readData = new ArrayList<>();
        
        for(int i=0;i<Utils.getQuestionCount();i++){
            TempObject temp = readFromFile(i+1);
            int x = temp.getQuestionNo();
            readData.add(x-1, temp.getData());
        }        
        return readData;
    }
    
    public TempObject readFromFile(int fileName){
        TempObject temp = null;
        try {            
            FileInputStream fin = new FileInputStream("C:\\SoftclickerUOM\\"+fileName+".txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            temp = (TempObject)ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(FileIOOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return temp;
    }
}
