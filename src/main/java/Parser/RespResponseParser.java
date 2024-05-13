package Parser;

public class RespResponseParser {

    private static final String CRFL_TERMINATOR = "\r\n";
    public static final String DOLLAR = "$";
    public static final String PLUS = "+";
    public static final String STAR = "*";


    public static String sendSimpleString(String response) {
            return PLUS + response + CRFL_TERMINATOR;
    }

    public static String sendBulkString(String response) {

        return DOLLAR + response.length() + CRFL_TERMINATOR + response + CRFL_TERMINATOR;

    }


    public static String sendArrayString(String[] command) {

        StringBuffer response = new StringBuffer();

        response.append(STAR).append(command.length).append(CRFL_TERMINATOR);

        for (int i = 0; i < command.length; i++) {
            response.append(sendBulkString(command[i]));
        }

        return response.toString();

    }

}
