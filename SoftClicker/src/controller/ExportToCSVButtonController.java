/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import Codec.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import model.CSVFileWriter;
import model.CSVPOJO;
import model.FileIOOperation;
import model.Utils;

/**
 *
 * @author THARU
 */
public class ExportToCSVButtonController {
    
    public void importToCSV(String filePath){
        
        // if answers has not been saved, save them in SoftclickerUOM folder in C partition
        if(!Utils.isSaved() && !Utils.getData().isEmpty()){
            int currentCount = Utils.getQuestionCount();
            Utils.setQuestionCount(currentCount+1);                             // increment question count
            SaveAnswersController s = new SaveAnswersController();
            s.save();
            Utils.setSaved(true);                                               // set answer is saved
        }
        ArrayList<CSVPOJO> data = dataPreProcessing();                          // preprocessing
        writeToCSV(filePath, data);
                
    }
    
    /*
    * preprocessing
    * 
    * create list of CSVPOJO objects to create the csv file
    * each CSVPOJO object contains data for a tuple of csv file
    * get data from tempory files which is in SoftclickerUOM folder in C partition in to a CSVPOJO object
    */
    public ArrayList<CSVPOJO> dataPreProcessing(){
        ArrayList<CSVPOJO> data = new ArrayList<>();
        
        // read answer set of each question in to array list
        FileIOOperation fileIO = new FileIOOperation();
        ArrayList<Hashtable<String,RespondMessage>> existData = fileIO.readOperation();

        int count=0;
        
        /*
        * decode answer set of each question  
        */
        for(Hashtable<String,RespondMessage> answerSet: existData){ 
            // get key set of hash table 
            Set<String> keys = answerSet.keySet();  
            
            // decode each answer and create correspondong CSVPOJO object
            for(String key:keys){
                
                // check CSVPOJO obejct which contains give key is already in the array list
                CSVPOJO pojo = isAvailable(key, data);
                
                // if CSVPOJO object is already in the array list
                if(pojo != null){
                    /*
                    * when same index number has answered multiple question, 
                    * store answers in the same CSVPOJO object
                    */
                    int [] targetAnswers = pojo.getAnswer();
                    RespondMessage answer = answerSet.get(key);
                    targetAnswers [count] = answer.getAnswer();
                    int new_count = pojo.getTotal_no_of_response();
                    pojo.setTotal_no_of_response(new_count+1);
                }
                else{
                    // CSVPOJO object is not in the array list, create new one
                    int [] targetAnswers = new int[existData.size()];
                    RespondMessage answer = answerSet.get(key);
                    targetAnswers [count] = answer.getAnswer();
                    pojo = new CSVPOJO(answer.getStudentID(), answer.getClientMAC(),targetAnswers, 1);
                    // add created CSVPOJO object in to array list
                    data.add(pojo);                                              
                }
            }
            count++;                                                            // keep number of answers 
        }
        
        return data;
    }
    
    // create csv file
    public void writeToCSV(String filePath, ArrayList<CSVPOJO> data){
        CSVFileWriter writer = new CSVFileWriter();
        writer.writeCsvFile(filePath, data);
    }
    
    /*
    * check give CSVPOJO object in the given array list
    */
    public CSVPOJO isAvailable(String id,ArrayList<CSVPOJO> data){
        CSVPOJO pojo = null;
        for(CSVPOJO tempPojo:data){
            if(tempPojo.getStudent_ID().equals(id)){
                pojo = tempPojo;
                break;
            }
        }        
        return pojo;
    }
}
