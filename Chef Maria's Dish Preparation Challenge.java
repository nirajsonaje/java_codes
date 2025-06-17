// ✅ Understand the Problem
// Each dish has:

// Xi: time it takes to prepare

// Yi: deadline to finish it (relative to time 0)

// Chef Maria:

// Can only cook one dish at a time

// Wants to finish all dishes before or exactly at their deadlines

// ✅ Goal
// Find an order of dishes such that all are completed on time.

// If not possible, return -1.

// Else, return the total time taken to complete all dishes.
import java.util.*;

public class ChefMaria {

    public static long minTimeToCompleteAllDishes(int N, int[][] dishInfo) {
        // Sort by deadline (Yi)
        Arrays.sort(dishInfo, Comparator.comparingInt(d -> d[1]));

        long currentTime = 0;

        for (int[] dish : dishInfo) {
            int cookTime = dish[0];
            int deadline = dish[1];

            currentTime += cookTime;

            if (currentTime > deadline) {
                return -1; // Cannot finish this dish on time
            }
        }

        return currentTime;
    }

    // Example usage
    public static void main(String[] args) {
        int N = 5;
        int[][] dishInfo = {
            {2, 4}, {1, 9}, {1, 8}, {4, 9}, {3, 12}
        };

        long result = minTimeToCompleteAllDishes(N, dishInfo);
        System.out.println(result);  // Expected output: 11
    }
}
