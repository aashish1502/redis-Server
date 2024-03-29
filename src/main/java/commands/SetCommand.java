package commands;

import DataClasses.Cache;
import DataClasses.CacheValue;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class SetCommand implements Command {

    /*
     *   --- index : flag values ---
     *
     *   EX seconds -- Set the specified expire time, in seconds (a positive integer).
     *   PX milliseconds -- Set the specified expire time, in milliseconds (a positive integer).
     *   EXAT timestamp-seconds -- Set the specified Unix time at which the key will expire, in seconds (a positive integer).
     *   PXAT timestamp-milliseconds -- Set the specified Unix time at which the key will expire, in milliseconds (a positive integer).
     *   NX -- Only set the key if it does not already exist.
     *   XX -- Only set the key if it already exists.
     *   KEEPTTL -- Retain the time to live associated with the key.
     *   GET -- Return the old string stored at key, or nil if key did not exist. An error is returned and SET aborted if the value stored at key is not a string.
     *
     * */

    boolean[] flags = new boolean[8];
    String[] flagArg = new String[8];
    private static final ConcurrentHashMap<String, CacheValue> instance = Cache.getInstance();

    @Override
    public boolean match(String command) {
        return command.equalsIgnoreCase("set");
    }

    @Override
    public String processCommand(String... strings) {

        if (strings.length > 2) {
            proccessFlags(Arrays.copyOfRange(strings, 2, strings.length));
        }

        try {

            String key = strings[0];
            CacheValue value = new CacheValue(strings[1], flagArg, flags) ;
            setArgs(key, value);

        } catch (ArrayIndexOutOfBoundsException exception) {
            exception.printStackTrace();
        }
        return "+OK\r\n";
    }

    private void proccessFlags(String... strings) {


        int n = strings.length;
        int i = 0;
        while (i < n) {

            String flag = strings[i].toLowerCase();

            switch (flag) {
                case "px":
                    flags[1] = true;
                    flagArg[1] = strings[i + 1];
                    i += 2;
                    break;
                default:
                    break;

            }

        }


    }

    private static boolean setArgs(String key, CacheValue value) {

        try {
            instance.put(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


}
