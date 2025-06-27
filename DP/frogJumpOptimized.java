// Space Optimized DP
public static int frogJumpOptimized(int[] heights) {
    int prev = 0;  // dp[i - 1]
    int prev2 = 0; // dp[i - 2]

    for (int i = 1; i < heights.length; i++) {
        int one = prev + Math.abs(heights[i] - heights[i - 1]);
        int two = Integer.MAX_VALUE;
        if (i > 1)
            two = prev2 + Math.abs(heights[i] - heights[i - 2]);

        int curr = Math.min(one, two);
        prev2 = prev;
        prev = curr;
    }

    return prev;
}
