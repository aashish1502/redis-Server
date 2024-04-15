package Parser;


import Exceptions.BadCommandException;
import commands.Command;
import commands.EchoCommand;
import commands.PingCommand;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

// This parser is supposed to follow the RESP3 protocol to parse commands, I don't know how it will be implemented as
// I have done very little research into it but let's see how it goes
// edit 1: the input stream is not streaming the way i thought it was  :/
public class CommandParserHandler {
    ConcurrentHashMap<String, Command> cmdMapr = CommandMapper.getInstance();
    Socket client;
    public CommandParserHandler(Socket clientSocket){
        this.client = clientSocket;
    }

    public void parseAndHandleCommand(BufferedReader in, BufferedWriter out) throws IOException, BadCommandException {

        String[] clientCommand = null;
        int size = 0;
        String line;

        while((line = in.readLine()) != null) {

            if(line.charAt(0) == '*') {
                size = Integer.parseInt(line.substring(1));
                clientCommand = new String[size];
            }

            for(int i = 0, idx = 0 ; i < size*2 ; i++) {
                line = in.readLine();
                if(line.contains("$")) {
                    continue;
                }
                else {
                    clientCommand[idx] = line;
                    idx++;
                }
            }

            if( Objects.requireNonNull(clientCommand).length != 0) {
                System.out.println(Arrays.toString(clientCommand));
                out.write(commandProccessor(clientCommand));
                out.flush();
            }

        }



    }

    String commandProccessor(String[] clientCommand) throws BadCommandException {

        String command = clientCommand[0];
        System.out.println("Creating command for: " + command);
        try {
            if(cmdMapr.containsKey(command)) {
                return cmdMapr.get(command).processCommand(Arrays.copyOfRange(clientCommand, 1, clientCommand.length));
            }
            else {
                throw new BadCommandException();
            }
        } catch (BadCommandException e) {
            throw new RuntimeException(e);
        }
    }


}
