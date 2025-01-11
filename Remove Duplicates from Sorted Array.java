class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;

        int slow = 0; // Pointer to track unique elements

        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) { // Found a new unique element
                slow++; // Move the slow pointer
                nums[slow] = nums[fast]; // Place the unique element at slow
            }
        }

        return slow + 1; // Length of unique elements
    }
}
