package lab5.task3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CryptoDemo {
    private static final String DEFAULT_INPUT = "lab5/data/crypto_sample.txt";
    private static final String DEFAULT_ENCRYPTED = "lab5/out/encrypted.txt";
    private static final String DEFAULT_DECRYPTED = "lab5/out/decrypted.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Input file selection
            System.out.println("Оберіть вхідний файл або введіть свій шлях:");
            System.out.println("1 — " + DEFAULT_INPUT);
            System.out.print("Ваш вибір (Enter = 1): ");
            String inChoice = scanner.nextLine().trim();
            String inPath = (inChoice.isEmpty() || inChoice.equals("1")) ? DEFAULT_INPUT : inChoice;

            // Output encrypted path (default)
            System.out.print("Шлях для зашифрованого файлу (Enter = " + DEFAULT_ENCRYPTED + "): ");
            String encPathInput = scanner.nextLine().trim();
            String encPath = encPathInput.isEmpty() ? DEFAULT_ENCRYPTED : encPathInput;

            // Key char
            System.out.print("Ключовий символ (використовується перший символ): ");
            String keyInput = scanner.nextLine();
            char key = keyInput.isEmpty() ? '\0' : keyInput.charAt(0);

            File inFile = new File(inPath);
            if (!inFile.exists()) {
                System.out.println("Вхідний файл не знайдено: " + inFile.getPath());
                return;
            }

            // Ensure output directory exists for encrypted file
            File encFile = new File(encPath);
            File encDir = encFile.getParentFile();
            if (encDir != null && !encDir.exists()) {
                encDir.mkdirs();
            }

            // Encrypt
            try (BufferedReader br = new BufferedReader(new FileReader(inFile));
                 EncryptingWriter ew = new EncryptingWriter(new BufferedWriter(new FileWriter(encFile)), key)) {
                int ch;
                while ((ch = br.read()) != -1) {
                    ew.write(ch);
                }
            }
            System.out.println("Файл зашифровано: " + encFile.getPath());

            // Decrypt (optional save with default)
            System.out.print("Шлях для розшифрованого файлу (Enter = " + DEFAULT_DECRYPTED + ", або '-' щоб пропустити): ");
            String decPath = scanner.nextLine().trim();
            if (decPath.isEmpty()) {
                // If just Enter, use default path and perform decrypt
                decPath = DEFAULT_DECRYPTED;
            }
            if (!decPath.equals("-")) {
                File decFile = new File(decPath);
                File decDir = decFile.getParentFile();
                if (decDir != null && !decDir.exists()) {
                    decDir.mkdirs();
                }
                try (DecryptingReader dr = new DecryptingReader(new BufferedReader(new FileReader(encFile)), key);
                     BufferedWriter bw = new BufferedWriter(new FileWriter(decFile))) {
                    int ch;
                    while ((ch = dr.read()) != -1) {
                        bw.write(ch);
                    }
                }
                System.out.println("Файл розшифровано: " + decFile.getPath());
            }
        } catch (IOException ex) {
            System.out.println("Помилка вводу/виводу: " + ex.getMessage());
        } finally {
            scanner.close();
        }
    }
}

