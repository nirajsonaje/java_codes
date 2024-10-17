// https://leetcode.com/problems/reverse-vowels-of-a-string/description/
class Solution {
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        String vowels = "aeiouAEIOU";

        while (left < right) {
            // Move the left pointer to the right until it points to a vowel.
            while (left < right && vowels.indexOf(chars[left]) == -1) {
                left++;
            }

            // Move the right pointer to the left until it points to a vowel.
            while (left < right && vowels.indexOf(chars[right]) == -1) {
                right--;
            }

            // Swap the vowels at left and right positions.
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;

            left++;
            right--;
        }

        return new String(chars);
    }
}
