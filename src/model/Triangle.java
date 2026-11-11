package model;

public class Triangle extends Shape {
    private final double base;
    private final double height;

    public Triangle(String shapeColor, double base, double height) {
        super(shapeColor);
        this.base = base;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return 0.5 * base * height;
    }

    @Override
    public void draw() {
        System.out.println("Малюю трикутник, основа " + base + ", висота " + height + ", колір: " + getShapeColor());
    }

    @Override
    public String toString() {
        return String.format("Трикутник[колір=%s, основа=%.1f, висота=%.1f, площа=%.2f]",
                getShapeColor(), base, height, calcArea());
    }
}
