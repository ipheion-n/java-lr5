package model;

public class Circle extends Shape {
    private final double radius;

    public Circle(String shapeColor, double radius) {
        super(shapeColor);
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Малюю коло радіусом " + radius + ", колір: " + getShapeColor());
    }

    @Override
    public String toString() {
        return String.format("Коло[колір=%s, радіус=%.1f, площа=%.2f]",
                getShapeColor(), radius, calcArea());
    }
}
