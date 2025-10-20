package lab3;

import java.util.Arrays;

public class ShapeController {
    private Shape[] shapes;

    public ShapeController(Shape[] shapes) {
        this.shapes = shapes;
    }

    public void display() {
        System.out.println("=== Набір даних ===");
        for (Shape shape : shapes) {
            shape.draw();
        }
    }

    public double totalArea() {
        double sum = 0;
        for (Shape shape : shapes) {
            sum += shape.calcArea();
        }
        return sum;
    }

    public double areaByType(String type) {
        double sum = 0;
        for (Shape shape : shapes) {
            if (shape.getClass().getSimpleName().equals(type)) {
                sum += shape.calcArea();
            }
        }
        return sum;
    }

    public void sortByArea() {
        Arrays.sort(shapes, new AreaComparator());
    }

    public void sortByColor() {
        Arrays.sort(shapes, new ColorComparator());
    }

    public Shape[] getShapes() {
        return shapes;
    }
}
