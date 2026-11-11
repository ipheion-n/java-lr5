package tasks;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.*;

/** Завдання 4: частота HTML-тегів на сторінці за URL. */
public class TagCounter {
    private static final Pattern TAG = Pattern.compile("<\\s*([a-zA-Z][a-zA-Z0-9-]*)");

    public static String download(String url) throws IOException {
        URLConnection c = new URL(url).openConnection();
        c.setRequestProperty("User-Agent", "Mozilla/5.0");
        c.setConnectTimeout(8000);
        c.setReadTimeout(8000);
        try (BufferedReader r = new BufferedReader(new InputStreamReader(c.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) sb.append(line).append('\n');
            return sb.toString();
        }
    }

    public static Map<String, Integer> count(String html) {
        Map<String, Integer> m = new HashMap<>();
        Matcher mt = TAG.matcher(html);
        while (mt.find()) m.merge(mt.group(1).toLowerCase(), 1, Integer::sum);
        return m;
    }

    /** a) за зростанням тегів у лексикографічному порядку. */
    public static List<Map.Entry<String, Integer>> byName(Map<String, Integer> m) {
        List<Map.Entry<String, Integer>> l = new ArrayList<>(m.entrySet());
        l.sort(Map.Entry.comparingByKey());
        return l;
    }

    /** b) за зростанням частоти появи (при рівності - за назвою). */
    public static List<Map.Entry<String, Integer>> byFrequency(Map<String, Integer> m) {
        List<Map.Entry<String, Integer>> l = new ArrayList<>(m.entrySet());
        l.sort(Map.Entry.<String, Integer>comparingByValue().thenComparing(Map.Entry.comparingByKey()));
        return l;
    }
}