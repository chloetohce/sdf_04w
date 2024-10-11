package fc;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    // Create jar: [go to classes folder] jar cvf ../fortunecookie.jar fc/*.class
    // Rune jar: java -cp fortunecookie.jar fc.Server
    public static void main(String[] args) {
        int port = 3000;
        String f = "cookie_file.txt";
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
            f = args[1];
        }

        File file = new File(f);
        try {
            ServerSocket socket = new ServerSocket(port);
            ExecutorService thrPool = Executors.newFixedThreadPool(2);
            while (!socket.isClosed()) {
                Socket conn = socket.accept();
                ConnectionHandler handler = new ConnectionHandler(conn, file);
                thrPool.submit(handler);
            }
        } catch (IOException e) {
            System.err.println("Unable to open socket");
            System.exit(-1);
        }
    }
}