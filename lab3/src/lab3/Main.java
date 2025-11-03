package lab3;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

import lab3.controller.ShapeController;
import lab3.io.ShapeFileManager;
import lab3.model.*;
import lab3.view.ShapeView;

public class Main {
    private static final String DEFAULT_PATH = "lab3/shapes.ser";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shape[] shapes = generateRandomShapes();
        ShapeController controller = new ShapeController(shapes);
        ShapeView view = new ShapeView();

        boolean running = true;
        while (running) {
            System.out.println("\n=== МЕНЮ LAB3 ===");
            System.out.println("1. Показати фігури");
            System.out.println("2. Загальна площа");
            System.out.println("3. Площа за типом (введіть тип)");
            System.out.println("4. Сортувати за площею");
            System.out.println("5. Сортувати за кольором");
            System.out.println("6. Зберегти фігури в файл");
            System.out.println("7. Завантажити фігури з файлу");
            System.out.println("8. Згенерувати нові випадкові фігури");
            System.out.println("0. Вихід");
            System.out.print("Оберіть пункт: ");

            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1":
                        controller.display();
                        break;
                    case "2":
                        view.printTotalArea(controller.totalArea());
                        break;
                    case "3":
                        System.out.print("Введіть тип (Rectangle/Triangle/Circle): ");
                        String type = scanner.nextLine().trim();
                        view.printTypeArea(type, controller.areaByType(type));
                        break;
                    case "4":
                        controller.sortByArea();
                        view.printSorted("за площею");
                        controller.display();
                        break;
                    case "5":
                        controller.sortByColor();
                        view.printSorted("за кольором");
                        controller.display();
                        break;
                    case "6":
                        System.out.print("Вкажіть шлях до файлу для збереження (Enter = " + DEFAULT_PATH + "): ");
                        String savePath = scanner.nextLine().trim();
                        if (savePath.isEmpty()) savePath = DEFAULT_PATH;
                        File outFile = new File(savePath);
                        File outDir = outFile.getParentFile();
                        if (outDir != null && !outDir.exists()) {
                            outDir.mkdirs();
                        }
                        ShapeFileManager.saveToFile(controller.getShapes(), outFile);
                        System.out.println("Збережено успішно у: " + outFile.getPath());
                        break;
                    case "7":
                        System.out.print("Вкажіть шлях до файлу для завантаження (Enter = " + DEFAULT_PATH + "): ");
                        String loadPath = scanner.nextLine().trim();
                        if (loadPath.isEmpty()) loadPath = DEFAULT_PATH;
                        File inFile = new File(loadPath);
                        if (!inFile.exists() || !inFile.isFile()) {
                            System.out.println("Файл не знайдено: " + inFile.getPath());
                            break;
                        }
                        Shape[] loaded = ShapeFileManager.loadFromFile(inFile);
                        controller = new ShapeController(loaded);
                        System.out.println("Завантажено успішно. Поточний набір оновлено.");
                        break;
                    case "8":
                        shapes = generateRandomShapes();
                        controller = new ShapeController(shapes);
                        System.out.println("Згенеровано новий набір фігур.");
                        break;
                    case "0":
                        running = false;
                        break;
                    default:
                        System.out.println("Невірний вибір. Спробуйте ще раз.");
                }
            } catch (Exception ex) {
                System.out.println("Сталася помилка: " + ex.getMessage());
            }
        }

        scanner.close();
    }

    private static Shape[] generateRandomShapes() {
        String[] colors = { "Red", "Blue", "Green", "Yellow", "Black" };
        Random rand = new Random();

        Shape[] shapes = new Shape[12];
        for (int i = 0; i < 4; i++) {
            shapes[i] = new Rectangle(colors[rand.nextInt(colors.length)], rand.nextDouble() * 10 + 1,
                    rand.nextDouble() * 10 + 1);
        }
        for (int i = 4; i < 8; i++) {
            shapes[i] = new Triangle(colors[rand.nextInt(colors.length)], rand.nextDouble() * 10 + 1,
                    rand.nextDouble() * 10 + 1);
        }
        for (int i = 8; i < 12; i++) {
            shapes[i] = new Circle(colors[rand.nextInt(colors.length)], rand.nextDouble() * 5 + 1);
        }

        return shapes;
    }
}

