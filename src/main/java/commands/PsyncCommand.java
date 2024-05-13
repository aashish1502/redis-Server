package commands;

import Exceptions.BadCommandException;
import OptionsAndRoles.ConfigOptions;
import Parser.RespResponseParser;

public class PsyncCommand implements Command {
    @Override
    public boolean match(String command) {
        return false;
    }

    @Override
    public String processCommand(String... strings) {
        try {
            if (strings.length != 2 || !strings[0].equals("?") || !strings[1].equals("-1")) {
                throw new BadCommandException();
            }
        }
        catch (BadCommandException exception) {
            exception.getMessage("Bad command arguments");
        }

        ConfigOptions serverConfig = ConfigOptions.getInstance();

        return RespResponseParser.sendSimpleString("FULLRESYNC " + serverConfig.getMasterReplID() + "0");
    }
}
