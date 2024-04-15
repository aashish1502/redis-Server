import Exceptions.BadCommandException;
import Parser.CommandParserHandler;
import commands.Command;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {

    private final Socket clinetSocket;
    private  final CommandParserHandler parserHandler;

    ClientHandler(Socket clinetSocket) {
        this.clinetSocket = clinetSocket;
        this.parserHandler = new CommandParserHandler(clinetSocket);
    }

    @Override
    public void run() {

        try {
            System.out.println("Running a command");
            // reading the commands from client
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clinetSocket.getInputStream()));

            // for sending response to the client
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(clinetSocket.getOutputStream()));

            parserHandler.parseAndHandleCommand(in, out);

            in.close();
            out.close();

        } catch (IOException ex) {
            ex.getCause();
        }
        catch (BadCommandException e) {
            throw new RuntimeException(e);
        }

    }

    @Deprecated
    private void commandHandler(List<Command> commands, BufferedWriter out) {

        try{
            for(Command cmd : commands) {
                out.write(cmd.processCommand());
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
