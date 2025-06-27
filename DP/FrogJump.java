 // Java Code Using DP (Tabulation)
public class FrogJump {

    public static int frogJump(int[] heights) {
        int n = heights.length;
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int oneStep = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
            int twoStep = Integer.MAX_VALUE;
            if (i > 1) {
                twoStep = dp[i - 2] + Math.abs(heights[i] - heights[i - 2]);
            }
            dp[i] = Math.min(oneStep, twoStep);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] heights = {10, 20, 30, 10};
        System.out.println("Minimum energy: " + frogJump(heights)); // Output: 20
    }
}
