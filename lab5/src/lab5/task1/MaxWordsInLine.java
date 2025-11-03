package lab5.task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MaxWordsInLine {
    private static final String SAMPLE1 = "lab5/data/sample1.txt";
    private static final String SAMPLE2 = "lab5/data/sample2.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Оберіть файл або введіть шлях вручну:");
        System.out.println("1 — " + SAMPLE1);
        System.out.println("2 — " + SAMPLE2);
        System.out.print("Ваш вибір (Enter = 1): ");
        String input = scanner.nextLine().trim();

        String path;
        if (input.isEmpty() || input.equals("1")) {
            path = SAMPLE1;
        } else if (input.equals("2")) {
            path = SAMPLE2;
        } else {
            path = input;
        }

        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Файл не знайдено або шлях невірний.");
            System.out.println("Спробуйте ще раз з існуючим шляхом або виберіть 1/2.");
            scanner.close();
            return;
        }

        String maxLine = null;
        int maxWords = -1;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                int words = countWords(line);
                if (words > maxWords) {
                    maxWords = words;
                    maxLine = line;
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
            scanner.close();
            return;
        }

        if (maxLine == null) {
            System.out.println("Файл порожній.");
        } else {
            System.out.println("Рядок з максимальною кількістю слів (" + maxWords + "):");
            System.out.println(maxLine);
        }
        scanner.close();
    }

    private static int countWords(String line) {
        if (line == null) return 0;
        String trimmed = line.trim();
        if (trimmed.isEmpty()) return 0;
        String[] parts = trimmed.split("\\s+");
        return parts.length;
    }
}
