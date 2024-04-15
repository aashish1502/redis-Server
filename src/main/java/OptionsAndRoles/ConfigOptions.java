package OptionsAndRoles;


// this class will be a singleton object that will take the config args from the user and change it as such
// this can then be used to set config of the server easily without having to bloat up the main code.
public class ConfigOptions {


    private int portNumber = 6379;
    private boolean isMaster;
    private String role;

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

}
