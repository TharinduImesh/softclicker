/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author THARU
 */
public class CSVFileWriter {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    //CSV file header
    private String FILE_HEADER = "Date,Student_ID,device_MAC,";//answer_1,answer_2,answer_3,total_no_of_response";
//    private String filePath = "C:\\Users\\";
    public void writeCsvFile(String fileName, ArrayList<CSVPOJO> data) {

        FileWriter fileWriter = null;
        try {
            if(!fileName.contains(".csv")){
                fileName = fileName.concat(".csv");
            }
            
            int count = data.get(0).answer.length;
            for(int i=0;i<count;i++){
                FILE_HEADER = FILE_HEADER.concat("answer_"+(i+1)+",");
            }
            FILE_HEADER = FILE_HEADER.concat("total_no_of_response");
            
            fileWriter = new FileWriter(fileName);
            //Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());
            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);
            //Write a new student object list to the CSV file
            
            DateFormat dateFormat = new SimpleDateFormat("M.d.yyyy");
            Date date = new Date();
            for (CSVPOJO pojo : data) {
                fileWriter.append(dateFormat.format(date));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(pojo.getStudent_ID());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(pojo.getDevice_MAC());
                fileWriter.append(COMMA_DELIMITER);
                
                int [] answer = pojo.getAnswer();
                for(int i=0;i<count;i++){
                    fileWriter.append(answer[i]+"");        // answer_1
                    fileWriter.append(COMMA_DELIMITER);
                }
                fileWriter.append(pojo.getTotal_no_of_response()+"");
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            System.out.println("CSV file was created successfully !!!");
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }

}
