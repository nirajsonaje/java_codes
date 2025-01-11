class Solution {

    // Function to find the longest palindromic substring in the given string
    public static String longestPalindrome(String str) {
        if (str == null || str.length() < 1) {
            return "";
        }

        String longestPalindrome = "";

        for (int i = 0; i < str.length(); i++) {
            // For odd length palindromes
            String palindrome1 = expandAroundCenter(str, i, i);
            // For even length palindromes
            String palindrome2 = expandAroundCenter(str, i, i + 1);

            // Update the longest palindrome if a longer one is found
            if (palindrome1.length() > longestPalindrome.length()) {
                longestPalindrome = palindrome1;
            }

            if (palindrome2.length() > longestPalindrome.length()) {
                longestPalindrome = palindrome2;
            }
        }

        // Return the longest palindromic substring
        return longestPalindrome;
    }
    // Function to expand around the center and find the longest palindromic
    // substring
    private static String expandAroundCenter(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        // Return the palindromic substring
        return str.substring(left + 1, right);
    }
}
//babad 
0 0  aba 
0 1
