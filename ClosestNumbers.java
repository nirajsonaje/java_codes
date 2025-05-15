import java.util.*;

public class ClosestNumbers {

    public static void closestNumbers(int[] numbers) {
        // Step 1: Sort the array
        Arrays.sort(numbers);

        int minDiff = Integer.MAX_VALUE;

        // Step 2: Find the minimum difference
        for (int i = 1; i < numbers.length; i++) {
            int diff = numbers[i] - numbers[i - 1];
            if (diff < minDiff) {
                minDiff = diff;
            }
        }

        // Step 3: Print all pairs with that minimum difference
        for (int i = 1; i < numbers.length; i++) {
            int diff = numbers[i] - numbers[i - 1];
            if (diff == minDiff) {
                System.out.println(numbers[i - 1] + " " + numbers[i]);
            }
        }
    }

    // Example usage
    public static void main(String[] args) {
        int[] numbers = {6, 2, 4, 10};
        closestNumbers(numbers);
    }
}
