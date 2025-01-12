class Solution {
    public int findPeakElement(int[] nums) {
        // using a binary search algorithm
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) {
                // The peak element is on the left side
                right = mid;
            } else {
                // The peak element is on the right side
                left = mid + 1;
            }
        }

        // At the end of the loop, left will be the index of the peak element
        return left;

    }

    // This code starts with the entire array and repeatedly narrows down the search
    // range by comparing the middle element with its adjacent element to determine
    // if the peak is on the left or right side. This process continues until left
    // and right pointers are equal, at which point you have found the peak element.
}

// Sure, let's do a dry run of the given code with an example array. Let's take
// the array nums = [1, 2, 1, 3, 5, 6, 4].

// Initial values:

// left = 0
// right = 6 (length of the array - 1)
// First iteration:

// Calculate mid = 0 + (6 - 0) / 2 = 3
// Check nums[3] > nums[3 + 1] (3 > 5) - False
// Update left = mid + 1 = 4
// Second iteration:

// Calculate mid = 4 + (6 - 4) / 2 = 5
// Check nums[5] > nums[5 + 1] (6 > 4) - True
// Update right = mid = 5
// Third iteration:

// Calculate mid = 4 + (5 - 4) / 2 = 4
// Check nums[4] > nums[4 + 1] (5 > 6) - False
// Update left = mid + 1 = 5
// Fourth iteration:

// Calculate mid = 5 + (5 - 5) / 2 = 5
// Check nums[5] > nums[5 + 1] (6 > 4) - True
// Update right = mid = 5
// Fifth iteration:

// Calculate mid = 5 + (5 - 5) / 2 = 5
// Check nums[5] > nums[5 + 1] (6 > 4) - True
// Update right = mid = 5
// The loop stops because left is now equal to right. At this point, the
// function returns left, which is 5.

// So, according to this algorithm, the peak element in the array nums is at
// index 5, and nums[5] = 6.
