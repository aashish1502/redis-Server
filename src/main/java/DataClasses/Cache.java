package DataClasses;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {


    private static ConcurrentHashMap<String, CacheValue> instance = null;

    private Cache() {
        // private constructor to prevent instantiation
    }
    public static synchronized ConcurrentHashMap<String, CacheValue> getInstance() {
        if (instance == null) {
            instance = new ConcurrentHashMap<>();
        }
        return instance;

    }



}
