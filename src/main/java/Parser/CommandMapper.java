package Parser;

import commands.*;

import java.util.HashMap;

public class CommandMapper {

    public final HashMap<String, Command> commandMap = new HashMap<>();
    {
        commandMap.put("ping", new PingCommand());
        commandMap.put("echo", new EchoCommand());
        commandMap.put("set", new SetCommand());
        commandMap.put("get", new GetCommand());
    }

}
