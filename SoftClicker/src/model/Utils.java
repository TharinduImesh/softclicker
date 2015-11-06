/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import Codec.*;
import java.net.ServerSocket;
import java.util.Hashtable;

/**
 *
 * @author THARU
 */
public class Utils {
    private static final Hashtable<String,RespondMessage> data = new Hashtable();       // use to store answers of each question
    private static int questionCount;                                           // number question in each lecture
    private static boolean serverState;                                         // true if server is running
                                                                                // false if server has stopped
    private static boolean saved = false;
    private static ServerSocket serverSocket;// = new ServerSocket(serverPort);

    public static ServerSocket getServerSocket() {
        return serverSocket;
    }

    public static void setServerSocket(ServerSocket serverSocket) {
        Utils.serverSocket = serverSocket;
    }
    
    public static Hashtable<String,RespondMessage> getData() {
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

    public static boolean isSaved() {
        return saved;
    }

    public static void setSaved(boolean saved) {
        Utils.saved = saved;
    }

}
