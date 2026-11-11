package model;

import java.util.Random;

/**
 * Створює набір фігур. Значення беруться із заздалегідь підготовлених даних,
 * вибір — випадковий.
 */
public class ShapeGenerator {
    private static final String[] COLORS = {"червоний", "синій", "зелений", "жовтий", "чорний", "білий"};
    private static final double[] SIZES = {1.5, 2.0, 2.5, 3.0, 4.0, 5.0, 6.0};

    private final Random random = new Random();

    public Shape[] generate(int count) {
        Shape[] shapes = new Shape[count];
        for (int i = 0; i < count; i++) {
            shapes[i] = createRandomShape();
        }
        return shapes;
    }

    private Shape createRandomShape() {
        String color = COLORS[random.nextInt(COLORS.length)];
        double a = SIZES[random.nextInt(SIZES.length)];
        double b = SIZES[random.nextInt(SIZES.length)];
        switch (random.nextInt(3)) {
            case 0:  return new Rectangle(color, a, b);
            case 1:  return new Triangle(color, a, b);
            default: return new Circle(color, a);
        }
    }
}
