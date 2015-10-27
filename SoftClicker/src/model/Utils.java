/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Hashtable;

/**
 *
 * @author THARU
 */
public class Utils {
    private static final Hashtable<String,Answer> data = new Hashtable();
    private static int questionNo;
    private static int questionCount;
//    public Utils() {
//         this.data = new ArrayList();
//    }

    
    public static Hashtable<String,Answer> getData() {
        return data;
    }

    public static int getQuestionNo() {
        return questionNo;
    }

    public static void setQuestionNo(int questionNo) {
        Utils.questionNo = questionNo;
    }

    public static int getQuestionCount() {
        return questionCount;
    }

    public static void incrementQuestionCount() {
        Utils.questionCount++;
    }

    
    
}
