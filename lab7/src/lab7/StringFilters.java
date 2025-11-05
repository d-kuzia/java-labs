package lab7;

import java.util.Arrays;
import java.util.Objects;

public final class StringFilters {
    private StringFilters() {}

    public static String[] shorterThanAverage(String[] input) {
        return filterByAverage(input, Comparison.LESS);
    }

    public static String[] longerThanAverage(String[] input) {
        return filterByAverage(input, Comparison.GREATER);
    }

    private enum Comparison { LESS, GREATER }

    private static String[] filterByAverage(String[] input, Comparison cmp) {
        if (input == null || input.length == 0) return new String[0];

        double avg = Arrays.stream(input)
                .filter(Objects::nonNull)
                .mapToInt(String::length)
                .average()
                .orElse(0.0);

        return Arrays.stream(input)
                .filter(Objects::nonNull)
                .filter(s -> cmp == Comparison.LESS ? s.length() < avg : s.length() > avg)
                .toArray(String[]::new);
    }
}

