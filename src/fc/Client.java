package fc;

import java.net.Socket;
import java.io.*;

public class Client {
    public static void main(String[] args) throws IOException {
        // TASK: Need to implement get-cookie command
        String input = "localhost:3000";
        if (args.length > 0) {
            input = args[0];
        }
        String host = input.substring(0, input.indexOf(":"));
        int port = Integer.parseInt(input.substring(input.indexOf(":") + 1));
        Socket socket = new Socket(host, port);

        InputStream is = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
        try {
            Object cookie = ois.readObject();
            System.out.println("Cookie received: " + cookie.toString());
        } catch (ClassNotFoundException ex) {
            System.out.println("Problem reading cookie.");
        }
        ois.close();
        socket.close();
    }
}
