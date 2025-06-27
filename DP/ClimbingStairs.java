public class ClimbingStairs {

    public static int climbStairs(int n) {
        if (n <= 2) return n;

        int[] dp = new int[n + 1];
        dp[0] = 1; // 1 way to stand at ground (base case)
        dp[1] = 1; // 1 way to reach step 1
        dp[2] = 2; // two ways: (1+1) or (2)

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println("Ways to climb stairs: " + climbStairs(n)); // Output: 8
    }
}
