package Codec;

/**
 *
 * @author Rajind
 */
public class Keys {
    
    //Error Codes
    public static final String ERROR_SERVICE_UNAVAILABLE = "001";
    
    //Messages Types
    public static final String MultiCast        = "MUL";
    public static final String Respond          = "RES";
    public static final String Acknowledgement  = "ACK";
    public static final String Error            = "ERR";
    
    //Message Types Binary Representations
    public static final String Binary_MultiCast             = "000";
    public static final String Binary_Respond               = "001";
    public static final String Binary_Acknowledgement       = "010";
    public static final String Binary_Error                 = "011";
    
    //Bit sizes for parameters
    public static final int BITS_FOR_QUESTION_NUMBER    = 5;
    public static final int BITS_FOR_ANSWER             = 3;
    public static final int BITS_FOR_MESSAGE_TYPE       = 3;
    
    //Byte lengths for parameters
    public static final int IP_LENGTH           = 4;
    public static final int PORT_LENGTH         = 4;
    public static final int MAC_LENGTH          = 6;
    public static final int STUDENTID_LENGTH    = 7;
    public static final int ANSWER_LENGTH       = 1;
    public static final int MTYPE_QNUM_LENGTH   = 1;
    
    //Message Lengths (Length of byte arrays)
    public static final int LENGTH_MULTICAST = 9;
    public static final int LENGTH_RESPOND = 15;
    public static final int LENGTH_ACKNOWLEDGEMENT = 8;
    public static final int LENGTH_ERROR = 1;
}

