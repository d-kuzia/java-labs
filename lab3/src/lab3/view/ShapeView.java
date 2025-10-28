package lab3.view;

public class ShapeView {
    public void printTotalArea(double area) {
        System.out.printf("Сумарна площа: %.2f\n", area);
    }

    public void printTypeArea(String type, double area) {
        System.out.printf("Площа %s: %.2f\n", type, area);
    }

    public void printSorted(String criteria) {
        System.out.println("=== Відсортовані за " + criteria + " ===");
    }
}
