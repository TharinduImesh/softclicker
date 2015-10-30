package Codec;

/**
 *
 * @author Rajind
 */
public class RespondMessage extends Message{
    private int questionNumber;
    private String clientMAC;
    private String studentID;
    private int answer;
    
    public RespondMessage(String messageType) {
        super(messageType);
    }
    
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setClientMAC(String clientMAC) {
        this.clientMAC = clientMAC;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getClientMAC() {
        return clientMAC;
    }

    public String getStudentID() {
        return studentID;
    }

    public int getAnswer() {
        return answer;
    }
}
