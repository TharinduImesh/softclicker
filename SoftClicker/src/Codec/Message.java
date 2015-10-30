package Codec;

/**
 *
 * @author Rajind
 */
public class Message {

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
