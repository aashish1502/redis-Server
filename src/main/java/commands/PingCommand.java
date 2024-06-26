package commands;

import Parser.RespResponseParser;

public class PingCommand implements Command{

    @Override
    public boolean match(String command) {
        return command.equalsIgnoreCase("ping");
    }

    @Override
    public String processCommand(String... strings) {
        return RespResponseParser.sendSimpleString("PONG");
    }
}
