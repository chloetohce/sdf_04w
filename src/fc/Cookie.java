package fc;

import java.io.*;
import java.security.SecureRandom;
import java.util.*;

public class Cookie {
    private final String text;

    private Cookie(String text) {
        this.text = text;
    }

    public String command(String input) {
        if (input.equals("get-cookie")) {
            return this.toString();
        } else if (input.equals("quit")) {
            return "Quitting...";
        } else {
            return "Unrecognised command";
        }
    }

    public static Cookie open(File file) throws IOException{
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        List<String> lines = new ArrayList<>();
        String line = "";
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        Random rand = new SecureRandom();
        String select = lines.get(rand.nextInt(lines.size()));

        br.close();
        return new Cookie(select);
    }

    @Override
    public String toString() {
        return "get-cookie: " + this.text;
    }
}
