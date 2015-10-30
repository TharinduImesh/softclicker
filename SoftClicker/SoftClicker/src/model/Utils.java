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
    private static final Hashtable<String,Answer> data = new Hashtable();       // use to store answers of each question
    private static int questionCount;                                           // number question in each lecture
    private static boolean serverState;                                         // true if server is running
                                                                                // false if server has stopped
    public static Hashtable<String,Answer> getData() {
        return data;
    }

    public static int getQuestionCount() {
        return questionCount;
    }

    public static void setQuestionCount(int questionCount) {
        Utils.questionCount = questionCount;
    }

    public static void incrementQuestionCount() {
        Utils.questionCount++;
    }

    public static boolean isServerState() {
        return serverState;
    }

    public static void setServerState(boolean serverState) {
        Utils.serverState = serverState;
    }

}
