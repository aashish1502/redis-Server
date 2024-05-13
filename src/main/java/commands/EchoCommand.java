package commands;

import Parser.RespResponseParser;

import java.util.Arrays;

public class EchoCommand implements Command {

    final static String NULL_BULK_STRING = "$-1\r\n";

    @Override
    public boolean match(String command) {
        return command.equalsIgnoreCase("Echo");
    }

    @Override
    public String processCommand(String... str) {

        if(str[0].isEmpty()) return NULL_BULK_STRING;

        return RespResponseParser.sendBulkString(str[0]);
    }
}
