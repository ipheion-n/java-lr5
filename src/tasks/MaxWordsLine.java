package tasks;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class MaxWordsLine {

    public static int countWords(String line) {
        String t = line.trim();
        return t.isEmpty() ? 0 : t.split("\\s+").length;
    }

    
    public static String find(String path) throws IOException {
        String best = null;
        int max = -1;
        try (BufferedReader r = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line;
            while ((line = r.readLine()) != null) {
                int c = countWords(line);
                if (c > max) { max = c; best = line; }
            }
        }
        return best;
    }
}