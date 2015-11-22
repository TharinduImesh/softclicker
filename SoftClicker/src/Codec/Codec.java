package Codec;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rajind
 */
public class Codec {

    public static byte[] EncodeMultiCastMessage(String serverIP, int serverPort, int questionNumber, String ssid){
        try {
            byte[] packet;
            
            /*
            format:
            message type: 3 bits
            question number: 5 bits
            server ip: 4 bytes
            server port: 4 bytes
            ssid size: 4 bytes
            ssid: variable length (length of string)
            */
            
            InetAddress ip = InetAddress.getByName(serverIP);  //throws unknown host exception
            
            byte messageType_questionNumber_byte = mType_qNumberIntoByte(Keys.MultiCast, questionNumber);
            byte[] serverIP_byteArray = ip.getAddress();
            byte[] serverPort_byteArray = portIntoByteArray(serverPort);
            byte[] ssidSize_byteArray = ssidSizeIntoByteArray(ssid.length());
            byte[] ssid_byteArray = ssid.getBytes();
            
            packet = new byte[Keys.MTYPE_QNUM_LENGTH + Keys.IP_LENGTH + Keys.PORT_LENGTH + Keys.SSID_SIZE_LENGTH+ ssid_byteArray.length];
            
            packet[0] = messageType_questionNumber_byte;
            System.arraycopy(serverIP_byteArray, 0, packet, Keys.MTYPE_QNUM_LENGTH, Keys.IP_LENGTH);
            System.arraycopy(serverPort_byteArray, 0, packet, Keys.IP_LENGTH + Keys.MTYPE_QNUM_LENGTH, Keys.PORT_LENGTH);
            System.arraycopy(ssidSize_byteArray, 0, packet, Keys.IP_LENGTH + Keys.MTYPE_QNUM_LENGTH + Keys.PORT_LENGTH, Keys.SSID_SIZE_LENGTH);
            System.arraycopy(ssid_byteArray, 0, packet, Keys.IP_LENGTH + Keys.MTYPE_QNUM_LENGTH + Keys.PORT_LENGTH + Keys.SSID_SIZE_LENGTH
                    , ssid_byteArray.length);
            return packet;
        } catch (UnknownHostException ex) {
            Logger.getLogger(Codec.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static byte[] EncodeRespondMessage(String macAddress, String studentID, int answer, int questionNumber){
        byte [] packet;
        
        /*
        format:
        message type: 3 bits
        question number: 5 bits
        client mac: 6 bytes
        student id: 7 bytes
        answer : 3 bits
        */
        
        byte messageType_questionNumber_byte = mType_qNumberIntoByte(Keys.Respond, questionNumber);
 
        byte[] macAddress_byteArray = macIntoByteArray(macAddress);
        byte[] studentID_byteArray = studentID.getBytes();
        byte answer_byte = answerToByte(answer);
        
        packet = new byte[Keys.MTYPE_QNUM_LENGTH + Keys.MAC_LENGTH + Keys.STUDENTID_LENGTH + Keys.ANSWER_LENGTH];
        
        packet[0] = messageType_questionNumber_byte;
        System.arraycopy(macAddress_byteArray, 0, packet, Keys.MTYPE_QNUM_LENGTH, Keys.MAC_LENGTH);
        System.arraycopy(studentID_byteArray, 0, packet, Keys.MAC_LENGTH + Keys.MTYPE_QNUM_LENGTH , Keys.STUDENTID_LENGTH);
        packet[Keys.LENGTH_RESPOND - 1] = answer_byte; 
        return packet;
    }
   
    public static byte[] EncodeAcknowledgementMessage(String studentID, int questionNumber){
        byte []packet;
        
        /*
        format:
        message type: 3 bits
        question number: 5 bits
        student id: 7 bytes
        */
        byte messageType_questionNumber_byte = mType_qNumberIntoByte(Keys.Acknowledgement, questionNumber);
        byte[] studentID_byteArray = studentID.getBytes();
        
        packet = new byte[Keys.MTYPE_QNUM_LENGTH + Keys.STUDENTID_LENGTH];
        packet[0] = messageType_questionNumber_byte;
        System.arraycopy(studentID_byteArray, 0, packet, Keys.MTYPE_QNUM_LENGTH , Keys.STUDENTID_LENGTH);
        return packet;
    }
    
    public static byte[] EncodeErrorMessage(String errorCode){
        byte []packet;
        /*
        format:
        message type: 3 bits
        error code: 3 bits
        */
        packet = new byte[1];
        String str = messageTypeToBinary(Keys.Error) + errorCode + "00"; 
        packet[0] = (byte)Integer.parseInt(str, 2);
        return packet;
    }
    
    public static Message DecodeMessage(byte []b){
        String messageType = mTypeByteIntoMType(b[0]);
        //ssage message = new Message(messageType);
        
        switch(messageType){
            case Keys.MultiCast:
                MultiCastMessage multicastMessage = new MultiCastMessage(messageType);
                multicastMessage.setQuestionNumber(mType_qNumberByteIntoQuestionNumber(b[0]));
                multicastMessage.setIp(byteArrayIntoIPAddress(b, Keys.MTYPE_QNUM_LENGTH));
                multicastMessage.setServerPort(byteArrayIntoPort(b,Keys.IP_LENGTH + Keys.MTYPE_QNUM_LENGTH));
                multicastMessage.setSsid(byteArrayIntoSSID(b, Keys.IP_LENGTH + Keys.MTYPE_QNUM_LENGTH + Keys.PORT_LENGTH));
                return multicastMessage;
                
            case Keys.Respond:
                RespondMessage respondMessage = new RespondMessage(messageType);
                respondMessage.setQuestionNumber(mType_qNumberByteIntoQuestionNumber(b[0]));
                respondMessage.setClientMAC(byteArrayIntoMac(b, Keys.MTYPE_QNUM_LENGTH));
                respondMessage.setStudentID(byteArrayIntoStudentID(b, Keys.MAC_LENGTH + Keys.MTYPE_QNUM_LENGTH));
                respondMessage.setAnswer(byteToAnswer(b, Keys.LENGTH_RESPOND - 1));
                return respondMessage;
                
            case Keys.Acknowledgement:
                AcknowledgementMessage ackMessage = new AcknowledgementMessage(messageType);
                ackMessage.setQuestionNumber(mType_qNumberByteIntoQuestionNumber(b[0]));
                ackMessage.setStudentID(byteArrayIntoStudentID(b, Keys.MTYPE_QNUM_LENGTH));
                return ackMessage;
            
            case Keys.Error:
                ErrorMessage errorMessage = new ErrorMessage(messageType);
                errorMessage.setErrorCode(mType_errorCodeByteIntoErrorCode(b[0]));
                return errorMessage;
                
            default:
                return null;
        }
    }
    
    private static String byteArrayIntoSSID(byte []b, int start){
        byte []arr1 = new byte[Keys.SSID_SIZE_LENGTH];
        System.arraycopy(b, start, arr1, 0, Keys.SSID_SIZE_LENGTH);
        ByteBuffer bb = ByteBuffer.wrap(arr1); // big-endian by default
        int ssid_size = bb.getInt();
        
        byte [] arr = new byte[ssid_size];
        System.arraycopy(b, start + Keys.SSID_SIZE_LENGTH, arr, 0, ssid_size);
        return new String(arr, Charset.forName("UTF-8"));
    }
    
    private static String byteArrayIntoStudentID(byte []b, int start){
        byte [] arr = new byte[Keys.STUDENTID_LENGTH];
        System.arraycopy(b, start, arr, 0, Keys.STUDENTID_LENGTH);
        return new String(arr, Charset.forName("UTF-8"));
    }
    
    private static String byteArrayIntoIPAddress(byte []b, int start){
        String ip = "";
        for (int i = start; i < (start + Keys.IP_LENGTH); i++){
            ip = ip + "." + (b[i] & 0xFF);
        }
        return ip.substring(1);
    }
    
    private static String mType_errorCodeByteIntoErrorCode(byte b){
        String questionBinary = Integer.toBinaryString(b & 255 | 256).substring(4,7);
        return questionBinary;
    }
    
    private static String messageTypeToBinary(String messageType) {
        switch (messageType) {
            case Keys.MultiCast:
                return Keys.Binary_MultiCast;

            case Keys.Respond:
                return Keys.Binary_Respond;

            case Keys.Acknowledgement:
                return Keys.Binary_Acknowledgement;
                
            case Keys.Error:
                return Keys.Binary_Error;

            default:
                throw new UnknownMessageTypeException("Message type "+ messageType + " is not known.");
        }
    }

    private static String binaryToMessageType(String binary) {
        switch (binary) {
            case Keys.Binary_MultiCast:
                return Keys.MultiCast;

            case Keys.Binary_Respond:
                return Keys.Respond;

            case Keys.Binary_Acknowledgement:
                return Keys.Acknowledgement;
                
            case Keys.Binary_Error:
                return Keys.Error;

            default:
                throw new UnknownBinaryMessageTypeException("Binary Message type "+ binary + " is not known.");
        }
    }

    private static byte mType_qNumberIntoByte(String messageType, int questionNumber) {
        if(questionNumber < 1 || questionNumber > 31){
            throw new QuestionNumberOutOfRangeException("Question number: "+ questionNumber +" is not in accepted range");
        }
        String questionNumberBinary = Integer.toBinaryString(questionNumber);
        for (int i = questionNumberBinary.length(); i < Keys.BITS_FOR_QUESTION_NUMBER; i++) {
            questionNumberBinary = "0" + questionNumberBinary;
        }
        return (byte) Integer.parseInt(messageTypeToBinary(messageType) + questionNumberBinary, 2);
    }
    
    private static String mTypeByteIntoMType(byte b){
        String mTypeBinary = Integer.toBinaryString(b & 255 | 256).substring(1,4);
        return binaryToMessageType(mTypeBinary);
    }
    
    private static int mType_qNumberByteIntoQuestionNumber(byte b){
        String questionBinary = Integer.toBinaryString(b & 255 | 256).substring(4);
        return Integer.parseInt(questionBinary , 2);
    }
    
    private static byte answerToByte(int answer) {
        if(answer < 1 || answer > 5){
            throw new AnswerOutOfRangeException("Answer: "+answer+"is not in accepted range");
        }
        String ansStr = Integer.toBinaryString(answer);
        for (int i = ansStr.length(); i < Keys.BITS_FOR_ANSWER; i++) {
            ansStr = "0" + ansStr;
        }
        ansStr = ansStr + "00000";
        //System.out.println(ansStr);
        return (byte) Integer.parseInt(ansStr, 2);
    }

    private static int byteToAnswer(byte []b , int index) {
        String ansStr = Integer.toBinaryString(b[index] & 255 | 256).substring(1, Keys.BITS_FOR_ANSWER + 1);
        //System.out.println(ansStr);
        return Integer.parseInt(ansStr, 2);
    }

    private static String byteArrayIntoMac(byte[] arr, int start) {
        StringBuilder sb = new StringBuilder();
        
        for(int i=start; i < (start + Keys.MAC_LENGTH); i++){
            sb.append(String.format("%02X:", arr[i]));
        }
        
        /*for (byte b : arr) {
            sb.append(String.format("%02X:", b));
        }*/
        
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private static byte[] macIntoByteArray(String macAddress) {
        String[] bytes = macAddress.split(":");
        byte[] parsed = new byte[bytes.length];

        for (int x = 0; x < bytes.length; x++) {
            BigInteger temp = new BigInteger(bytes[x], 16);
            byte[] raw = temp.toByteArray();
            parsed[x] = raw[raw.length - 1];
        }
        return parsed;
    }

    //not needed yet
    private static String hexToBinary(String hex) {
        int i = Integer.parseInt(hex, 16);
        String bin = Integer.toBinaryString(i);
        return bin;
    }

    private static byte[] ssidSizeIntoByteArray(int value) {
        ByteBuffer bb = ByteBuffer.allocate(Keys.SSID_SIZE_LENGTH);
        bb.putInt(value);
        return bb.array(); 
    }
    
    private static byte[] portIntoByteArray(int value) {
        ByteBuffer bb = ByteBuffer.allocate(Keys.PORT_LENGTH);
        bb.putInt(value);
        return bb.array(); 
    }

    private static int byteArrayIntoPort(byte[] b, int start) {
        byte []arr = new byte[Keys.PORT_LENGTH];
        System.arraycopy(b, start, arr, 0, Keys.PORT_LENGTH);
        ByteBuffer bb = ByteBuffer.wrap(arr); // big-endian by default
        return bb.getInt();
    }
    
     public static boolean validateStudentID(String id){
        String pattern = "\\d{6}[a-zA-Z]{1}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(id);
        if(m.find()){
            System.out.println("valid");
            return true;
        }else{
            System.out.println("invalid");
            return false;
        }
    }
    
    private static void testPrint(Message m){
        //instanceOf Can be used as well
        switch(m.getMessageType()){
            case Keys.MultiCast:
                MultiCastMessage m1 = (MultiCastMessage)m;
                System.out.println(m1.getMessageType());
                System.out.println(m1.getIp());
                System.out.println(m1.getServerPort());
                System.out.println(m1.getQuestionNumber());
                System.out.println(m1.getSsid());
                System.out.println("");
                break;
            
            case Keys.Respond:
                RespondMessage m2 = (RespondMessage)m;
                System.out.println(m2.getMessageType());
                System.out.println(m2.getClientMAC());
                System.out.println(m2.getStudentID());
                System.out.println(m2.getAnswer());
                System.out.println(m2.getQuestionNumber());
                System.out.println("");
                break;
                
            case Keys.Acknowledgement:
                AcknowledgementMessage m3 = (AcknowledgementMessage)m;
                System.out.println(m3.getMessageType());
                System.out.println(m3.getStudentID());
                System.out.println(m3.getQuestionNumber());
                System.out.println("");
                break;
                
            case Keys.Error:
                ErrorMessage m4 = (ErrorMessage)m;
                System.out.println(m4.getMessageType());
                System.out.println(m4.getErrorCode());
                System.out.println("");
                break;
                
            default:
                break;
        }
    }
    
    private static void testMessages(){
        byte [] array = EncodeMultiCastMessage("192.168.2.8", 3000, 20, "Whatever_SSID_name_given_here_1");
        Message m = DecodeMessage(array);
        testPrint(m);
        
        byte [] array1 = EncodeRespondMessage("0C:71:5D:06:2D:74", "110486D", 5, 20);
        Message m1 = DecodeMessage(array1);
        testPrint(m1);
        
        byte [] array2 = EncodeAcknowledgementMessage("110486D", 20);
        Message m2 = DecodeMessage(array2);
        testPrint(m2);
        
        byte [] array3 = EncodeErrorMessage(Keys.ERROR_SERVICE_UNAVAILABLE);
        Message m3 = DecodeMessage(array3);
        testPrint(m3);
    }

    public static void main(String Args[]) {
        
        /*
         String str = "110486D";
         byte arr[] = str.getBytes();
         System.out.println(new String(arr, Charset.forName("UTF-8")));
         */

        /*
        try {
            InetAddress ip = InetAddress.getByName("192.168.2.1");
            byte[] bytes = ip.getAddress();

            for (byte b : bytes) {
                System.out.println(b & 0xFF);
            } 

            for (byte b : bytes) {
                System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
                //this is to get lowest 8 bits
            }

            System.out.println(byteArrayIntoIPAddress(bytes, 0));

        } catch (UnknownHostException ex){
            Logger.getLogger(Codec.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        //System.out.println(Integer.toBinaryString(5));
        //System.out.println(hexToBinary("0F"));
        //System.out.println(byteArrayIntoMac(macIntoByteArray("0C:71:5D:06:2D:74")));
        /*
         String str = "10110111";
         System.out.println(Integer.parseInt(str, 2));
         byte b = (byte)Integer.parseInt(str, 2);
         System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
         */
        /*
         byte b = answerToByte(1);
         System.out.println(byteToAnswer(b));
         System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
         */
        
        /*
        byte b = mType_qNumberIntoByte(Keys.Respond, 5);
        System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
        */
        
        /*
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(1);
        byte[] bytes2 = bb.array(); 
        
        bb = ByteBuffer.wrap(bytes2); // big-endian by default
        int num = bb.getInt();
        
        
        for (byte b : bytes2) {
            System.out.println(Integer.toBinaryString(b & 255 | 256).substring(1));
            //this is to get lowest 8 bits
        }
        System.out.println(num);
        */
        
        /*
        byte b = mType_qNumberIntoByte(Keys.Respond, 25);
        System.out.println(mType_qNumberByteIntoQuestionNumber(b));
        */
        
        /*
        byte []bytes2 = ServiceUnavailableMessage();
        System.out.println(mType_errorCodeByteIntoErrorCode(bytes2[0]));;
        */  
        
        //testMessages();
        
        /*
        String ssid = "D_Tap_Hello_1";
        byte[] ssid_byteArray = ssid.getBytes();
        System.out.println(ssid_byteArray.length);
        */
        
        /*byte [] array = EncodeMultiCastMessage("192.168.2.8", 3000, 20, Extractor.getConnectedSSID());
        Message m = DecodeMessage(array);
        testPrint(m);
        */
    }
}
