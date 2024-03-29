package commands;

import DataClasses.Cache;
import DataClasses.CacheValue;

import java.util.concurrent.ConcurrentHashMap;

public class GetCommand implements Command{

    private final static ConcurrentHashMap<String, CacheValue> instance = Cache.getInstance();

    @Override
    public boolean match(String command) {
        return command.equalsIgnoreCase("get");
    }

    @Override
    public String processCommand(String... strings) {
        String response = "$-1\r\n";
        EchoCommand echo = new EchoCommand();
        try {
            String key = strings[0];
            response =  echo.processCommand(getMapping(key));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;

    }

    public String getMapping(String key) {

        try {

            CacheValue resp = instance.getOrDefault(key, null);
            if(resp == null || (resp.expiryTime < System.currentTimeMillis() && resp.expiryTime != -1)) {
                return "";
            }
            else {
                return resp.value;
            }


        } catch (Exception e) {
            return "-Exception Occured";
        }

    }


}
