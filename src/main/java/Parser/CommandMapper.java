package Parser;

import DataClasses.CacheValue;
import commands.*;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CommandMapper {

    private static ConcurrentHashMap<String, Command> commandMap = null;


    static void init() {

        {
            commandMap.put("ping", new PingCommand());
            commandMap.put("echo", new EchoCommand());
            commandMap.put("set", new SetCommand());
            commandMap.put("get", new GetCommand());
            commandMap.put("info", new InfoCommand());
            commandMap.put("replconf", new ReplicaConfCommand());
            commandMap.put("psync", new PsyncCommand());

        }

    }

    public static synchronized ConcurrentHashMap<String, Command> getInstance() {
        if (commandMap == null) {
            commandMap = new ConcurrentHashMap<>();
            init();
        }
        return commandMap;

    }

}
