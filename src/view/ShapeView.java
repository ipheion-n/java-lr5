package view;

import model.Shape;

import java.util.Scanner;

/** Відповідає лише за введення та виведення в консолі. */
public class ShapeView {
    private final Scanner scanner = new Scanner(System.in, util.ConsoleUtil.charset());

    public void printShapes(String title, Shape[] shapes) {
        System.out.println("\n=== " + title + " ===");
        for (int i = 0; i < shapes.length; i++) {
            System.out.println((i + 1) + ". " + shapes[i]);
        }
    }

    public void printDrawAll(Shape[] shapes) {
        System.out.println("\n=== Малювання фігур ===");
        for (Shape s : shapes) {
            s.draw();
        }
    }

    public void printTotalArea(double total) {
        System.out.printf("%nСумарна площа всіх фігур: %.2f%n", total);
    }

    public void printTypeArea(String typeName, double total, int count) {
        System.out.printf("%nСумарна площа фігур виду \"%s\" (%d шт.): %.2f%n", typeName, count, total);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMenu() {
        System.out.println("""

                ===== МЕНЮ =====
                1 - Показати набір фігур
                2 - Намалювати всі фігури
                3 - Сумарна площа всіх фігур
                4 - Сумарна площа фігур заданого виду
                5 - Сортування за зростанням площі
                6 - Сортування за кольором
                7 - Зберегти набір фігур у файл
                8 - Прочитати набір фігур з файлу
                9 - Пошук фігур за кольором
                0 - Вихід""");
    }

    public void printTypeMenu() {
        System.out.println("Оберіть вид фігури: 1 - Прямокутник, 2 - Трикутник, 3 - Коло");
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Введіть число: ");
            }
        }
    }
}
