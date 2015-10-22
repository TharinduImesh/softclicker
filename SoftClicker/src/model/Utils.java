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
    private volatile ArrayList<Answer> data;

    public Utils() {
         this.data = new ArrayList();
    }

    
    public ArrayList<Answer> getData() {
        return this.data;
    }

    public void setData(ArrayList<Answer> data) {
        this.data = data;
    }
    
    
}
