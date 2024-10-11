package fc;

import java.net.Socket;
import java.io.*;

public class ConnectionHandler implements Runnable {
    private final Socket socket;
    private final File file;

    public ConnectionHandler(Socket socket, File file) {
        this.socket = socket;
        this.file = file;
    }

    @Override
    public void run() {
        /* 
         * 1. Create a new cookie instance
         * 2. Use cookie to read the file given and extract a cookie line. 
         * 3. Set up a connection with the client
         * 4. Pass the string to client
         */
        
        try {
            Cookie cookie = Cookie.of(file);
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(cookie);

            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
