package Codec;

/**
 *
 * @author Rajind
 */
public class MultiCastMessage extends Message{
    private int questionNumber;
    private String ip;
    private int serverPort;

    public MultiCastMessage(String messageType) {
        super(messageType);
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getIp() {
        return ip;
    }

    public int getServerPort() {
        return serverPort;
    }
}
