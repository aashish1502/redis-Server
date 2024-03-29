package DataClasses;

public class CacheValue {

    public String value;
    public long startTime;
    public long expiryTime = -1;

    public CacheValue(String value, String[] flagArgs, boolean[] flags) {
        this.value = value;
        this.startTime = System.currentTimeMillis();
        int idx = 0;

        while(idx < flags.length) {

            if(flags[idx]) {

                switch (idx){
                    case 1:
                        this.expiryTime = startTime+Integer.parseInt(flagArgs[idx]);
                }

            }

            idx++;
        }


    }


}
