package commands;

public interface Command {

    boolean match(String command);
    String processCommand(String... strings);

}
