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

        Console console = System.console();
        String in = "";

        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        while (!in.equals("quit")) {

            in = console.readLine("> ").trim();
            bw.write(in + "\n");
            // NOTE: output is not passed on for some reason unless flush() is added directly
            // after the write() function call. 
            bw.flush(); 

            String response = br.readLine();

            String display = response.substring(Math.max(0, response.indexOf(":") + 1)).trim();
            System.out.println("Response from server: " + display);

        }

        bw.close();
        br.close();

        socket.close();
    }
}
