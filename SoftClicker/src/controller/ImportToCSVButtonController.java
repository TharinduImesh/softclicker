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
public class ImportToCSVButtonController {
    
    public void importToCSV(String filePath){   
        if(!Utils.isSaved()){
            SaveAnswersController s = new SaveAnswersController();
            s.save();
            Utils.setSaved(true);
        }
        ArrayList<CSVPOJO> data = dataPreProcessing();
        writeToCSV(filePath, data);
                
    }
    
    public ArrayList<CSVPOJO> dataPreProcessing(){
        ArrayList<CSVPOJO> data = new ArrayList<>();
        
        FileIOOperation fileIO = new FileIOOperation();
        ArrayList<Hashtable<String,RespondMessage>> existData = fileIO.readOperation();

        int count=0;
        for(Hashtable<String,RespondMessage> answerSet: existData){            
            Set<String> keys = answerSet.keySet();            
            for(String key:keys){ 
//                Answer answer = answerSet.get(key);
//                System.out.println("new answer");
//                System.out.println("id: "+answer.getId());
//                System.out.println("answer: "+answer.getAnswer());
//                System.out.println("mac: "+answer.getMac());
//                System.out.println("Q: "+answer.getQuestionNo());
//                System.out.println("\n");
                CSVPOJO pojo = isAvailable(key, data);
                if(pojo != null){
                    int [] targetAnswers = pojo.getAnswer();
                    RespondMessage answer = answerSet.get(key);
                    targetAnswers [count] = answer.getAnswer();
                    int new_count = pojo.getTotal_no_of_response();
                    pojo.setTotal_no_of_response(new_count+1);
                }
                else{
                    int [] targetAnswers = new int[existData.size()];
                    RespondMessage answer = answerSet.get(key);
                    targetAnswers [count] = answer.getAnswer();
                    pojo = new CSVPOJO(answer.getStudentID(), answer.getClientMAC(),targetAnswers, 1);
                    data.add(pojo);
                }
            }
            count++;
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
