package model;


public abstract class Shape implements Drawable, java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private final String shapeColor;

    protected Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public String getShapeColor() {
        return shapeColor;
    }

    /** Обчислення площі фігури. */
    public abstract double calcArea();

    @Override
    public String toString() {
        return "Фігура[колір=" + shapeColor + ", площа=" + String.format("%.2f", calcArea()) + "]";
    }
}
