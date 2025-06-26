// Space-Optimized DP (Iterative)
public class FibonacciOptimized {
    public static int fib(int n) {
        if (n <= 1) return n;

        int prev2 = 0, prev1 = 1, curr = 0;

        for (int i = 2; i <= n; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return curr;
    }

    public static void main(String[] args) {
        System.out.println(fib(10)); // Output: 55
    }
}
// ✅ Time complexity: O(n)
// ✅ Space complexity: O(1)


