class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] charCount = new int[26]; // Assuming lowercase English letters only

        // Count the characters in magazine
        for (char c : magazine.toCharArray()) {
            charCount[c - 'a']++;
        }

        // Check if it's possible to construct ransomNote
        for (char c : ransomNote.toCharArray()) {
            if (charCount[c - 'a'] > 0) {
                charCount[c - 'a']--;
            } else {
                return false;
            }
        }

        return true;
        // aab
    }
}
