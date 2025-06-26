import java.util.*;

public class FibonacciMemo {
    static int[] dp;

    public static int fib(int n) {
        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return helper(n);
    }

    private static int helper(int n) {
        if (n <= 1) return n;
        if (dp[n] != -1) return dp[n];

        dp[n] = helper(n - 1) + helper(n - 2);
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(fib(10)); // Output: 55
    }
}
