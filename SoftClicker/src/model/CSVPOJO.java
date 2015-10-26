/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author THARU
 */
public class CSVPOJO {
    String data;
    String student_ID;
    String device_MAC;
    String answer;
    int total_no_of_response;

    public CSVPOJO(String data, String student_ID, String device_MAC, String answer, int total_no_of_response) {
        this.data = data;
        this.student_ID = student_ID;
        this.device_MAC = device_MAC;
        this.answer = answer;
        this.total_no_of_response = total_no_of_response;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }

    public String getDevice_MAC() {
        return device_MAC;
    }

    public void setDevice_MAC(String device_MAC) {
        this.device_MAC = device_MAC;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getTotal_no_of_response() {
        return total_no_of_response;
    }

    public void setTotal_no_of_response(int total_no_of_response) {
        this.total_no_of_response = total_no_of_response;
    }
    
    
}
