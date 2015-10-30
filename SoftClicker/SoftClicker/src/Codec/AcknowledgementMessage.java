package Codec;

/**
 *
 * @author Rajind
 */
public class AcknowledgementMessage extends Message{
    private int questionNumber;
    private String StudentID;

    public AcknowledgementMessage(String messageType) {
        super(messageType);
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getStudentID() {
        return StudentID;
    }
}
