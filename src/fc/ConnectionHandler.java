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
        try {
            Cookie cookie = Cookie.open(file);
            InputStream is = socket.getInputStream();

            OutputStream os = socket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);

            byte[] inBytes = is.readAllBytes();
            String command = new String(inBytes, "UTF-8");
            String response = cookie.command(command);

            bos.write(response.getBytes());

            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
