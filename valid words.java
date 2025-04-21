//  Word is valid if:
// It contains at least 3 characters.

// It contains only alphanumeric characters.

// It has at least one vowel and one consonant.

public class ValidWordCounter {

    public static void main(String[] args) {
        String input = "Hello wor1d 123 App1e c@t too aei bcdfg ab1c";

        int validCount = countValidWords(input);
        System.out.println("Number of valid words: " + validCount);
    }

    public static int countValidWords(String input) {
        String[] words = input.split("\\s+");
        int count = 0;

        for (String word : words) {
            if (isValidWord(word)) {
                count++;
            }
        }

        return count;
    }

    public static boolean isValidWord(String word) {
        if (word.length() < 3) {
            return false;
        }

        // Check if word is alphanumeric
        if (!word.matches("[a-zA-Z0-9]+")) {
            return false;
        }

        int vowels = 0, consonants = 0;

        for (char ch : word.toCharArray()) {
            if (Character.isLetter(ch)) {
                if (isVowel(ch)) {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        return vowels >= 1 && consonants >= 1;
    }

    public static boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return "aeiou".indexOf(ch) != -1;
    }
}
