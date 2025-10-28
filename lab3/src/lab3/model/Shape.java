package lab3.model;

public abstract class Shape implements Drawable {
    protected String shapeColor;

    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public String getColor() {
        return shapeColor;
    }

    public abstract double calcArea();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + shapeColor + ", area=" + String.format("%.2f", calcArea()) + ")";
    }

    @Override
    public void draw() {
        System.out.println(toString());
    }
}
