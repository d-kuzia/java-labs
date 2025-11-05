package lab7;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Lab 7: Stream API and lambdas");
        System.out.println("Enter strings separated by spaces:");

        String line = SC.nextLine();
        String[] input = line == null ? new String[0]
                : Arrays.stream(line.trim().split("\\s+"))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);

        double avg = Arrays.stream(input)
                .filter(Objects::nonNull)
                .mapToInt(String::length)
                .average()
                .orElse(0.0);

        String[] shorter = StringFilters.shorterThanAverage(input);
        String[] longer = StringFilters.longerThanAverage(input);

        System.out.println("Average length: " + avg);
        System.out.println("Shorter than average: " + Arrays.toString(shorter));
        System.out.println("Longer than average:  " + Arrays.toString(longer));
    }
}

