/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codec;

/**
 *
 * @author Rajind
 */
public class UnknownBinaryMessageTypeException extends RuntimeException{
    /**
     * @param message the exception message
     * @param cause the cause
     */
    public UnknownBinaryMessageTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message the exception message
     */
    public UnknownBinaryMessageTypeException(String message) {
        super(message);
    }

    /**
     * @param cause the cause
     */
    public UnknownBinaryMessageTypeException(Throwable cause) {
        super(cause);
    }
}
