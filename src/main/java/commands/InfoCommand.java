package commands;

import OptionsAndRoles.ConfigOptions;

import java.util.Arrays;

public class InfoCommand implements Command {

    ConfigOptions serverConfig = null;
    public void setServerConfig(ConfigOptions serverConfig) {
        this.serverConfig = serverConfig;
    }

    @Override
    public boolean match(String command) {
        return false;
    }

    @Override
    public String processCommand(String... strings) {

        StringBuffer sb = new StringBuffer();

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
