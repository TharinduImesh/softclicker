/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Hashtable;

/**
 *
 * @author THARU
 */
public class TempObject implements Serializable{
    private int questionNo;
    private Hashtable<String,Answer> data;

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public Hashtable<String,Answer> getData() {
        return data;
    }

    public void setData(Hashtable<String,Answer> data) {
        this.data = data;
    }
}
