import OptionsAndRoles.ConfigOptions;
import Parser.CommandMapper;
import commands.Command;
import commands.InfoCommand;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


// TODO:
//  -   Add an event loop to handle multiple connections of the threads using a thread executor,
//      right now the thread pool executor works as and event loop to handle the number of threads and
//      resources but try to implement it in future by only using a fixed pool executor.
public class Main {





    // This is a thread pool executor that does the following things
    // - core size : 2                    # the number of cores the pool executor is allowed to work with
    // - maximumPoolSize : 10             # number of threads that will be running at the same time.
    // - keepAliveTime : 0                # The time it takes for an idle thread to wait before we kill it
    // - timeUnit : Milli-seconds         # This one is pretty obvious innit mate.
    // - workQueue: LinkedBlockingQueue   # This is a thread queue this will be able to queue up 1000 threads before rejecting new ones.
    // - ThreadFactory: A custom ThreadFactory is used to create new threads for the executor.
    //                  This factory creates daemon threads, which are low-priority threads
    //                  that do not prevent the JVM from exiting when the program finishes.
    //                  Daemon threads are typically used for background supporting tasks.

    private final static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 10, 0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(1000),
        r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    );

    public static void main(String[] args) {

        // You can use print statements as follows for debugging, they'll be visible when running tests.
        System.out.println("Logs from your program will appear here!");
//        System.out.println(Arrays.toString(args));

        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        ConfigOptions configOptions = new ConfigOptions();
        configOptions.setConfig(args);


        ConcurrentHashMap<String, Command> cmd = CommandMapper.getInstance();
        System.out.println(cmd);
        InfoCommand info = (InfoCommand) cmd.get("INFO");
        info.setServerConfig(configOptions);


        try {
            serverSocket = new ServerSocket(configOptions.getPort() );
            // Since the tester restarts the program quite often, setting SO_REUSEADDR
            // ensures that we don't run into 'Address already in use' errors
            serverSocket.setReuseAddress(true);

            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
                threadPool.execute(new ClientHandler(clientSocket));
            }


        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }
    }
}
