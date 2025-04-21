// Updated Problem:
// Array of positive integers, 1-based indexing.

// You can apply the operation 0 or more times.

// In each operation, choose any x (1 ≤ x ≤ max of array).

// Subtract 1 from all elements ≥ x.

// You can choose different values of x in each operation.

// Find the number of distinct arrays that can be obtained.

// Return answer modulo 10⁹ + 7.

import java.util.*;

public class Solution {
    static final int MOD = 1_000_000_007;

    public static int getCount(List<Integer> arr) {
        Collections.sort(arr);
        long result = 1;
        int prev = 0;

        for (int a : arr) {
            result = (result * (a - prev + 1)) % MOD;
            prev = a;
        }

        return (int) result;
    }
}
