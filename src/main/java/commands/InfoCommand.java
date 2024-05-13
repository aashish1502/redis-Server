package commands;

import OptionsAndRoles.ConfigOptions;

import java.util.Arrays;

public class InfoCommand implements Command {



    @Override
    public boolean match(String command) {
        return false;
    }

    @Override
    public String processCommand(String... strings) {

        StringBuffer sb = new StringBuffer();
        ConfigOptions serverConfig = ConfigOptions.getInstance();
//        if(strings.length > 0) {
//            sb.append("#"+ strings[0]);
//            sb.append("\r\n");
//        }

        sb.append(serverConfig.getConfigString());
        System.out.println(serverConfig.getConfigString());
        EchoCommand response = new EchoCommand();
        return response.processCommand(sb.toString());

    }

}
