public class KadanesAlgorithm {
    public static int maxSubArraySum(int[] arr) {
        int maxSoFar = Integer.MIN_VALUE; // Keeps track of the maximum sum found
        int maxEndingHere = 0;           // Tracks the sum of the current subarray

        for (int num : arr) {
            maxEndingHere += num;

            if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere; // Update maxSoFar if we find a new maximum
            }

            if (maxEndingHere < 0) {
                maxEndingHere = 0; // Reset maxEndingHere if it goes negative
            }
        }

        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum Subarray Sum is: " + maxSubArraySum(arr));
    }
}
