package util;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Налаштування кодування консолі. У Windows консоль зазвичай працює в cp866/cp1251, де немає
 * української «і», тому програма перемикає її в UTF-8 (аналог «chcp 65001») і виводить текст в UTF-8.
 */
public final class ConsoleUtil {
    private static Charset charset;

    private ConsoleUtil() { }

    /** Кодування, яке використовують введення і виведення програми. */
    public static synchronized Charset charset() {
        if (charset == null) {
            charset = detect();
        }
        return charset;
    }

    private static Charset detect() {
        String v = System.getProperty("stdout.encoding");
        if (v != null && v.toUpperCase().replace("-", "").equals("UTF8")) {
            return StandardCharsets.UTF_8;                       // вже UTF-8 (або запуск через pipe)
        }
        if (System.getProperty("os.name", "").toLowerCase().contains("win")) {
            try {
                Process p = new ProcessBuilder("cmd", "/c", "chcp 65001 >nul").inheritIO().start();
                if (p.waitFor() == 0) {
                    return StandardCharsets.UTF_8;
                }
            } catch (Exception ignored) {
                // не вдалося перемкнути - використаємо кодування, яке повідомила JVM
            }
        }
        for (String key : new String[] {"stdout.encoding", "sun.stdout.encoding"}) {
            String e = System.getProperty(key);
            if (e != null) {
                try {
                    return Charset.forName(e);
                } catch (Exception ignored) {
                    // наступний варіант
                }
            }
        }
        return Charset.defaultCharset();
    }

    /** Перевизначає System.out на потік із потрібним кодуванням. */
    public static void setup() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, charset()));
    }
}