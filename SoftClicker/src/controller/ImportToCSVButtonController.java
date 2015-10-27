/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import model.Answer;
import model.CSVFileWriter;
import model.CSVPOJO;
import model.FileIOOperation;

/**
 *
 * @author THARU
 */
public class ImportToCSVButtonController {
    
    public void importToCSV(String filePath){
        ArrayList<CSVPOJO> data = dataPreProcessing();
        writeToCSV(filePath, data);
    }
    
    public ArrayList<CSVPOJO> dataPreProcessing(){
        ArrayList<CSVPOJO> data = new ArrayList<>();
        
        FileIOOperation fileIO = new FileIOOperation();
        ArrayList<Hashtable<String,Answer>> existData = fileIO.readOperation();

        int count=0;
        for(Hashtable<String,Answer> answerSet: existData){            
            Set<String> keys = answerSet.keySet();            
            for(String key:keys){ 
                CSVPOJO pojo = isAvailable(key, data);
                if(pojo != null){
                    int [] targetAnswers = pojo.getAnswer();
                    Answer answer = answerSet.get(key);
                    targetAnswers [count] = answer.getAnswer();
                    int new_count = pojo.getTotal_no_of_response();
                    pojo.setTotal_no_of_response(new_count+1);
                }
                else{
                    int [] targetAnswers = new int[existData.size()];
                    Answer answer = answerSet.get(key);
                    targetAnswers [count] = answer.getAnswer();
                    pojo = new CSVPOJO(answer.getId(), answer.getMac(),targetAnswers, 1);
                    data.add(pojo);
                }
            }
        }
        
        return data;
    }
    
    public void writeToCSV(String filePath, ArrayList<CSVPOJO> data){
        CSVFileWriter writer = new CSVFileWriter();
        writer.writeCsvFile(filePath, data);
    }
    
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