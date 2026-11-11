package tasks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

/** Task 5 (завдання 1, 3, 4): консольне меню. Завдання 2 реалізовано в застосунку Main (MVC з Task 3). */
public class TasksMain {
    private static Scanner sc;

    public static void main(String[] args) {
        util.ConsoleUtil.setup();
        sc = new Scanner(System.in, util.ConsoleUtil.charset());
        while (true) {
            System.out.println("\n===== Task 5: I/O Streams =====");
            System.out.println("1. Рядок з максимальною кількістю слів у файлі");
            System.out.println("3. Шифрування / дешифрування файлу");
            System.out.println("4. Частота тегів на веб-сторінці");
            System.out.println("0. Вихід");
            System.out.print("Ваш вибір: ");
            if (!sc.hasNextLine()) return;
            String ch = sc.nextLine().trim();
            try {
                switch (ch) {
                    case "1": task1(); break;
                    case "3": task3(); break;
                    case "4": task4(); break;
                    case "0": System.out.println("Бувайте!"); return;
                    default: System.out.println("Невідома команда: " + ch);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Помилка: файл не знайдено (" + e.getMessage() + ")");
            } catch (IOException e) {
                System.out.println("Помилка введення/виведення: " + e.getMessage());
            }
        }
    }

    private static String ask(String prompt) {
        System.out.print(prompt);
        return sc.hasNextLine() ? sc.nextLine().trim() : "";
    }

    private static long size(String path) { return new File(path).length(); }

    private static void task1() throws IOException {
        String path = ask("Шлях до текстового файлу: ");
        String line = MaxWordsLine.find(path);
        if (line == null) { System.out.println("Файл порожній."); return; }
        System.out.println("Розмір файлу: " + size(path) + " байт");
        System.out.println("Рядок з максимальною кількістю слів (" + MaxWordsLine.countWords(line) + "):");
        System.out.println(line);
    }

    private static void task3() throws IOException {
        String mode = ask("1 - шифрувати, 2 - дешифрувати: ");
        if (!mode.equals("1") && !mode.equals("2")) { System.out.println("Невірний режим."); return; }
        String in = ask("Вхідний файл: ");
        String out = ask("Вихідний файл: ");
        String k = ask("Ключовий символ: ");
        if (k.isEmpty()) { System.out.println("Ключ не може бути порожнім."); return; }
        char key = k.charAt(0);
        if (mode.equals("1")) CipherService.encrypt(in, out, key); else CipherService.decrypt(in, out, key);
        System.out.println("Готово: " + out + " (" + size(in) + " -> " + size(out) + " байт)");
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(out), StandardCharsets.UTF_8))) {
            System.out.println("--- вміст вихідного файлу ---");
            String l;
            while ((l = r.readLine()) != null) System.out.println(l);
        }
    }

    private static void task4() {
        String url = ask("URL сторінки (напр. https://www.wikipedia.org): ");
        try {
            Map<String, Integer> m = TagCounter.count(TagCounter.download(url));
            if (m.isEmpty()) { System.out.println("Тегів не знайдено."); return; }
            System.out.println("\na) Теги в лексикографічному порядку:");
            TagCounter.byName(m).forEach(e -> System.out.println("  " + e.getKey() + " = " + e.getValue()));
            System.out.println("\nb) Теги за зростанням частоти:");
            TagCounter.byFrequency(m).forEach(e -> System.out.println("  " + e.getKey() + " = " + e.getValue()));
        } catch (IOException e) {
            System.out.println("Не вдалося завантажити сторінку: " + e.getMessage());
        }
    }
}