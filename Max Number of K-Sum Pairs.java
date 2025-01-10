import java.util.Arrays;

public class Solution {
    public int maxOperations(int[] nums, int k) {
        // Step 1: Sort the array to enable the two-pointer technique
        Arrays.sort(nums);

        // Initialize two pointers
        int left = 0;                // Pointer starting at the beginning of the array
        int right = nums.length - 1; // Pointer starting at the end of the array
        int count = 0;               // To keep track of valid pairs

        // Step 2: Use the two-pointer technique
        while (left < right) {
            int sum = nums[left] + nums[right]; // Calculate the sum of the two elements

            // Case 1: Found a pair whose sum equals k
            if (sum == k) {
                count++;      // Increment the count of valid pairs
                left++;       // Move the left pointer to the right
                right--;      // Move the right pointer to the left
            } 
            // Case 2: Sum is less than k
            else if (sum < k) {
                left++;       // Move the left pointer to increase the sum
            } 
            // Case 3: Sum is greater than k
            else {
                right--;      // Move the right pointer to decrease the sum
            }
        }

        // Step 3: Return the count of valid pairs
        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example test case
        int[] nums = {3, 1, 3, 4, 3};
        int k = 6;

        // Output the result
        System.out.println(solution.maxOperations(nums, k)); // Output: 1
    }
}
