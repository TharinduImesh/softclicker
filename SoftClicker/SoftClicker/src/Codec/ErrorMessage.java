package Codec;

/**
 *
 * @author Rajind
 */
public class ErrorMessage extends Message{
    private String errorCode;

    public ErrorMessage(String messageType) {
        super(messageType);
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
