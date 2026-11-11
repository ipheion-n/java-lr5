package tasks;

import java.io.*;
import java.nio.charset.StandardCharsets;

/** Методи шифрування та дешифрування файлу на основі FilterWriter / FilterReader. */
public class CipherService {

    public static void encrypt(String in, String out, char key) throws IOException {
        try (Reader r = new BufferedReader(new InputStreamReader(new FileInputStream(in), StandardCharsets.UTF_8));
             Writer w = new CipherWriter(new BufferedWriter(
                     new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8)), key)) {
            int c;
            while ((c = r.read()) != -1) w.write(c);
        }
    }

    public static void decrypt(String in, String out, char key) throws IOException {
        try (Reader r = new CipherReader(new BufferedReader(
                new InputStreamReader(new FileInputStream(in), StandardCharsets.UTF_8)), key);
             Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8))) {
            int c;
            while ((c = r.read()) != -1) w.write(c);
        }
    }
}