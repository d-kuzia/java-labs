package lab3;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String[] colors = {"Red", "Blue", "Green", "Yellow", "Black"};
        Random rand = new Random();

        Shape[] shapes = new Shape[12];
        for (int i = 0; i < 4; i++) {
            shapes[i] = new Rectangle(colors[rand.nextInt(5)],
                    rand.nextDouble() * 10 + 1, rand.nextDouble() * 10 + 1);
        }
        for (int i = 4; i < 8; i++) {
            shapes[i] = new Triangle(colors[rand.nextInt(5)],
                    rand.nextDouble() * 10 + 1, rand.nextDouble() * 10 + 1);
        }
        for (int i = 8; i < 12; i++) {
            shapes[i] = new Circle(colors[rand.nextInt(5)],
                    rand.nextDouble() * 5 + 1);
        }

        ShapeController controller = new ShapeController(shapes);
        ShapeView view = new ShapeView();

        controller.display();
        view.printTotalArea(controller.totalArea());
        view.printTypeArea("Rectangle", controller.areaByType("Rectangle"));
        view.printTypeArea("Triangle", controller.areaByType("Triangle"));
        view.printTypeArea("Circle", controller.areaByType("Circle"));

        controller.sortByArea();
        view.printSorted("площею");
        controller.display();

        controller.sortByColor();
        view.printSorted("кольором");
        controller.display();
    }
}
