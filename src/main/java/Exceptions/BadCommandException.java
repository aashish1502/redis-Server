package Exceptions;

public class BadCommandException extends Exception{

    int ERR_CODE = 400;
    String ERR_NAME = "400 Bad Request : Invalid argument (invalid request payload).";

    public String getMessage(String errMessage) {
        return errMessage;
    }
}
