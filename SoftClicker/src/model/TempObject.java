/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Hashtable;
import Codec.*;

/**
 *
 * @author THARU
 */
/*
* use to store answer set of one question
*/
public class TempObject implements Serializable{
    private int questionNo;
    private Hashtable<String,RespondMessage> data;                                      // answer set

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public Hashtable<String,RespondMessage> getData() {
        return data;
    }

    public void setData(Hashtable<String,RespondMessage> data) {
        this.data = data;
    }
}
