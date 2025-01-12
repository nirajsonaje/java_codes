class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false; // Negative numbers and zero are not powers of two
        }
        while (n % 2 == 0) {
            n /= 2; // Keep dividing by 2 as long as n is even
        }
        return n == 1; // If we end up with 1, n is a power of two
    }
}
