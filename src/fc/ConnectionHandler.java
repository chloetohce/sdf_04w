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
            

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);

            while (!socket.isClosed()) {
                Cookie cookie = Cookie.open(file);
                
                String command = "";
                command = br.readLine();

                System.out.println("command: " + command);
                String response = cookie.command(command);
                System.out.println("response: " + response);

                osw.write(response + "\n");
                osw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
