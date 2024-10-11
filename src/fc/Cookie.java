package fc;

import java.io.*;
import java.security.SecureRandom;
import java.util.*;

public class Cookie implements Serializable {
    private final String text;

    private Cookie(String text) {
        this.text = text;
    }

    public static Cookie of(File file) throws IOException{
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
        return this.text;
    }
}
