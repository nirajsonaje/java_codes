public class EvenSumDifferencePartitions {
    public static int countEvenDifferencePartitions(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0, count = 0;

        // Iterate through the array to find partitions
        for (int i = 0; i < nums.length - 1; i++) {
            leftSum += nums[i]; // Sum of left subarray
            int rightSum = totalSum - leftSum; // Sum of right subarray

            // Check if the difference is even
            if (Math.abs(leftSum - rightSum) % 2 == 0) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums1 = {10, 10, 3, 7, 6};
        System.out.println(countEvenDifferencePartitions(nums1)); // Output: 4

        int[] nums2 = {1, 2, 2};
        System.out.println(countEvenDifferencePartitions(nums2)); // Output: 0

        int[] nums3 = {2, 4, 6, 8};
        System.out.println(countEvenDifferencePartitions(nums3)); // Output: 3
    }
}
