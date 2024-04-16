package OptionsAndRoles;


import java.util.Arrays;

// this class will be a singleton object that will take the config args from the user and change it as such
// this can then be used to set config of the server easily without having to bloat up the main code.
public class ConfigOptions {


    private int portNumber = 6379;
    private boolean isMaster;
    private String role;
    private int  masterPort;
    private String masterHost;


    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public ConfigOptions(int portNumber, boolean isMaster, String role) {
        this.role = role;
        this.isMaster = isMaster;
        this.portNumber = portNumber;
    }

    public ConfigOptions() {
        this.isMaster = true;
        this.role = "master";
    }

    public boolean isMaster() {
        return isMaster;
    }

    public String getRole() {
        return role;
    }

    public void setMaster(boolean master) {
        isMaster = master;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPort() {
        return this.portNumber;
    }

    public String getConfigString() {

        return "role:"+this.role;

    }

    public void setConfig(String[] config) {

        if(config.length == 0) return;
        System.out.println(Arrays.toString(config));
        
        for(int i = 0 ; i < config.length ; i++) {
            if(config[i].equalsIgnoreCase("--port")) {
                this.setPortNumber(Integer.parseInt(config[++i]));
            }

            if(config[i].equalsIgnoreCase("--replicaof")) {
                this.role = "slave";
                this.masterHost = config[++i];
                this.masterPort = Integer.parseInt(config[++i]);
            }
        }


    }

}
