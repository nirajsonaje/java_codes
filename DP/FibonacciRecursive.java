// Recursive Solution (Brute Force – Exponential Time)
public class FibonacciRecursive {
    public static int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fib(10)); // Output: 55
    }
}
// ❌ Time complexity: O(2^n)
// ❌ Not efficient for large n due to repeated calculations.
