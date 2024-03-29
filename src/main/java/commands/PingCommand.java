package commands;

public class PingCommand implements Command{

    private final String responseCommand = "+PONG\r\n";
    @Override
    public boolean match(String command) {
        return command.equalsIgnoreCase("ping");
    }

    @Override
    public String processCommand(String... strings) {
        return responseCommand;
    }
}
