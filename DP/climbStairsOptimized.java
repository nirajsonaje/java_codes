public static int climbStairsOptimized(int n) {
    if (n <= 2) return n;

    int prev1 = 2; // f(n-1)
    int prev2 = 1; // f(n-2)

    for (int i = 3; i <= n; i++) {
        int curr = prev1 + prev2;
        prev2 = prev1;
        prev1 = curr;
    }

    return prev1;
}
