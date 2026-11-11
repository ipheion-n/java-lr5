package model;

public class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(String shapeColor, double width, double height) {
        super(shapeColor);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Малюю прямокутник " + width + " x " + height + ", колір: " + getShapeColor());
    }

    @Override
    public String toString() {
        return String.format("Прямокутник[колір=%s, %.1f x %.1f, площа=%.2f]",
                getShapeColor(), width, height, calcArea());
    }
}
