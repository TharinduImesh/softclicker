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
import Codec.*;

/**
 *
 * @author THARU
 */
/*
* use to store answers of each question in a text file as a obejct and retrive when required
*/
public class FileIOOperation {
    
    public void writeInToFile(Hashtable<String,RespondMessage> data){
        TempObject temp = new TempObject();
        temp.setQuestionNo(Utils.getQuestionCount());
        temp.setData(data);
        
        try{
            File file = new File("C:\\SoftclickerUOM");
            
            if (!file.exists()) {
                file.mkdir();
            }
            
            File newFile = new File("C:\\SoftclickerUOM\\"+Utils.getQuestionCount()+".txt");
            newFile.createNewFile();
            FileOutputStream fout = new FileOutputStream("C:\\SoftclickerUOM\\"+Utils.getQuestionCount()+".txt");
            ObjectOutputStream oos = new ObjectOutputStream(fout);   
            oos.writeObject(temp);
            oos.flush();
            oos.close();
            System.out.println("Done");
		   
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    /*
    * retrive all answers of all question
    */
    public ArrayList<Hashtable<String,RespondMessage>> readOperation(){
        ArrayList<Hashtable<String,RespondMessage>> readData = new ArrayList<>();
        
        for(int i=0;i<Utils.getQuestionCount();i++){
            TempObject temp = readFromFile(i+1);
            if(temp != null){
                readData.add(temp.getData());
            }
        }        
        return readData;
    }
    
    /*
    * retrive answers of given problem
    */
    public TempObject readFromFile(int fileName){
        TempObject temp = null;
        try {            
            FileInputStream fin = new FileInputStream("C:\\SoftclickerUOM\\"+fileName+".txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            temp = (TempObject)ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
//            Logger.getLogger(FileIOOperation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return temp;
    }
}
