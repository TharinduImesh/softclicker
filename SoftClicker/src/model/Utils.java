/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author THARU
 */
public class Utils {
    private static ArrayList<Answer> data = new ArrayList();

    public static ArrayList<Answer> getData() {
        return data;
    }

    public static void setData(ArrayList<Answer> data) {
        Utils.data = data;
    }
    
    
}
