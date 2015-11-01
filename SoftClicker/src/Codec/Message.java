package Codec;

import java.io.Serializable;

/**
 *
 * @author Rajind
 */
public class Message implements Serializable{

    private String messageType;

    public Message(String messageType) {
        this.messageType = messageType;
    }
    
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
