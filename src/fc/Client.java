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

        OutputStream os = socket.getOutputStream();

        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);

        Console console = System.console();
        String in = "";
        while (!in.equals("quit")) {
            in = console.readLine("> ").trim();
            os.write(in.getBytes());

            byte[] byteResponse = bis.readAllBytes();
            String response = new String(byteResponse, "UTF-8");
            String display = response.substring(Math.max(0, response.indexOf(":"))).
                trim();
            System.out.println("Response: " + display);
        }
        
        os.flush();
        os.close();
        bis.close();
        socket.close();
    }
}
