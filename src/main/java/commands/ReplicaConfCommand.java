package commands;

import Parser.RespResponseParser;

public class ReplicaConfCommand implements Command{


    @Override
    public boolean match(String command) {
        return false;
    }

    @Override
    public String processCommand(String... strings) {
        return RespResponseParser.sendSimpleString("OK");
    }
}
