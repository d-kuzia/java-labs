import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static String[] filterShorterThanAvg(String[] input) {
        if (input == null || input.length == 0) {
            return new String[0];
        }

        double avgLength = calculateAverageLength(input);
        ArrayList<String> result = new ArrayList<>();

        for (String str : input) {
            if (str.length() < avgLength) {
                result.add(str);
            }
        }

        return result.toArray(new String[0]);
    }

    public static String[] filterLongerThanAvg(String[] input) {
        if (input == null || input.length == 0) {
            return new String[0];
        }

        double avgLength = calculateAverageLength(input);
        ArrayList<String> result = new ArrayList<>();

        for (String str : input) {
            if (str.length() > avgLength) {
                result.add(str);
            }
        }

        return result.toArray(new String[0]);
    }

    private static double calculateAverageLength(String[] input) {
        int totalLength = 0;
        for (String str : input) {
            totalLength += str.length();
        }
        return (double) totalLength / input.length;
    }

    public static void main(String[] args) {
        String[] testArr = {"pipi", "bobi", "gamble", "Vitalik1", "Pokemon", "Sorry", "nah"};
        System.out.println("Array: " +  Arrays.toString(testArr));

        double avg = calculateAverageLength(testArr);
        System.out.println("Average length: " + avg);

        String[] shorter = filterShorterThanAvg(testArr);
        System.out.println("Shorter length: " + Arrays.toString(shorter));

        String[] longer = filterLongerThanAvg(testArr);
        System.out.println("Longer length: " + Arrays.toString(longer));

        // empty
        String[] empty = {};
        System.out.println("Empty length: " + Arrays.toString(empty));
        System.out.println("Shorter length: " + Arrays.toString(filterShorterThanAvg(empty)));
        System.out.println("Longer length: " + Arrays.toString(filterLongerThanAvg(empty)));

    }
}