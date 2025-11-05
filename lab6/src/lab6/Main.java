package lab6;

import java.util.*;

public class Main {
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        System.out.println("Лабораторна 6: Червоно-чорне дерево");

        while (true) {
            printMenu();
            String choice = SC.nextLine().trim();
            switch (choice) {
                case "1":
                    handleRandom(tree, false);
                    break;
                case "2":
                    handleRandom(tree, true);
                    break;
                case "3":
                    handleManual(tree);
                    break;
                case "4":
                    handleDelete(tree);
                    break;
                case "5":
                    printTraversals(tree);
                    tree.printTree();
                    break;
                case "0":
                    System.out.println("Вихід...");
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Меню:");
        System.out.println("  1) Додати випадкові числа (довільний порядок)");
        System.out.println("  2) Додати випадкові числа (впорядковано зростанням)");
        System.out.println("  3) Ввести числа з клавіатури");
        System.out.println("  4) Видалити число з дерева");
        System.out.println("  5) Показати обходи і відобразити дерево");
        System.out.println("  0) Вихід");
        System.out.print("Ваш вибір: ");
    }

    private static void handleRandom(RedBlackTree tree, boolean sorted) {
        int n = askInt("Кількість елементів n: ", 1, 1000000);
        int max = askInt("Максимальне значення (включно): ", 1, Integer.MAX_VALUE);
        int seed = (int)System.currentTimeMillis();
        Random rnd = new Random(seed);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(max + 1);

        System.out.println("Згенерований масив (seed=" + seed + "): ");
        printArray(arr);

        if (sorted) {
            Arrays.sort(arr);
            System.out.println("Відсортований масив для додавання: ");
            printArray(arr);
        }

        tree.clear();
        for (int v : arr) tree.insert(v);

        System.out.println("Дерево побудовано. Обходи та відображення:");
        printTraversals(tree);
        tree.printTree();
    }

    private static void handleManual(RedBlackTree tree) {
        System.out.println("Введіть цілі числа, розділені пробілами (порожній рядок — скасувати):");
        String line = SC.nextLine().trim();
        if (line.isEmpty()) {
            System.out.println("Скасовано.");
            return;
        }
        String[] parts = line.split("\\s+");
        List<Integer> values = new ArrayList<>();
        for (String p : parts) {
            try {
                values.add(Integer.parseInt(p));
            } catch (NumberFormatException e) {
                System.out.println("Пропущено невалідне значення: " + p);
            }
        }
        if (values.isEmpty()) {
            System.out.println("Немає коректних значень для додавання.");
            return;
        }
        System.out.println("Порядок додавання: " + values);
        for (int v : values) tree.insert(v);
        System.out.println("Додано. Обходи та відображення:");
        printTraversals(tree);
        tree.printTree();
    }

    private static void handleDelete(RedBlackTree tree) {
        int x = askInt("Введіть значення для видалення: ", Integer.MIN_VALUE, Integer.MAX_VALUE);
        boolean ok = tree.delete(x);
        if (ok) {
            System.out.println("Успішно видалено " + x + ". Дерево:");
        } else {
            System.out.println("Елемент " + x + " не знайдено. Дерево без змін:");
        }
        printTraversals(tree);
        tree.printTree();
    }

    private static void printTraversals(RedBlackTree tree) {
        System.out.println("In-order : " + tree.inOrder());
        System.out.println("Pre-order: " + tree.preOrder());
        System.out.println("Post-order: " + tree.postOrder());
    }

    private static int askInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String s = SC.nextLine().trim();
            try {
                long v = Long.parseLong(s);
                if (v < min || v > max) {
                    System.out.println("Повинно бути у діапазоні [" + min + ", " + max + "]: " + s);
                } else {
                    return (int)v;
                }
            } catch (NumberFormatException e) {
                System.out.println("Невірне число. Спробуйте ще раз.");
            }
        }
    }

    private static void printArray(int[] arr) {
        if (arr.length == 0) { System.out.println("[]"); return; }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(',').append(' ');
            sb.append(arr[i]);
        }
        sb.append(']');
        System.out.println(sb.toString());
    }
}

