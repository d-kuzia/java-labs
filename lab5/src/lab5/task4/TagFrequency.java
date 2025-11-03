package lab5.task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagFrequency {
    private static final Pattern TAG_PATTERN = Pattern.compile("<\\s*([a-zA-Z][a-zA-Z0-9]*)\\b");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть URL сторінки: ");
        String urlStr = scanner.nextLine();
        scanner.close();

        String html;
        try {
            html = fetch(urlStr);
        } catch (IOException e) {
            System.out.println("Не вдалося завантажити сторінку: " + e.getMessage());
            return;
        }

        Map<String, Integer> freq = countTags(html);
        if (freq.isEmpty()) {
            System.out.println("Теги не знайдено або сторінка порожня.");
            return;
        }

        System.out.println("\n— Сортування за тегом (лексикографічно) —");
        List<Map.Entry<String, Integer>> byName = new ArrayList<>(freq.entrySet());
        byName.sort(Comparator.comparing(Map.Entry::getKey));
        for (Map.Entry<String, Integer> e : byName) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }

        System.out.println("\n— Сортування за частотою (зростання) —");
        List<Map.Entry<String, Integer>> byFreq = new ArrayList<>(freq.entrySet());
        byFreq.sort(Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                .thenComparing(Map.Entry::getKey));
        for (Map.Entry<String, Integer> e : byFreq) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }

    private static String fetch(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append('\n');
            }
        }
        return sb.toString();
    }

    private static Map<String, Integer> countTags(String html) {
        Map<String, Integer> map = new HashMap<>();
        Matcher m = TAG_PATTERN.matcher(html);
        while (m.find()) {
            String tag = m.group(1).toLowerCase();
            map.put(tag, map.getOrDefault(tag, 0) + 1);
        }
        return map;
    }
}

