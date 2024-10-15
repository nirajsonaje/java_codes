import java.util.HashMap;

public class FirstNonRepeatingCharacter {
    public static void main(String[] args) {
        String str = "aabbccdeeffg";
        char result = firstNonRepeatingChar(str);
        if (result != '\0') {
            System.out.println("First non-repeating character is: " + result);
        } else {
            System.out.println("No non-repeating character found.");
        }
    }

    public static char firstNonRepeatingChar(String str) {
        HashMap<Character, Integer> charCountMap = new HashMap<>();

        // Count occurrences of each character
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }

        // Find the first character with count 1
        for (int i = 0; i < str.length(); i++) {
            if (charCountMap.get(str.charAt(i)) == 1) {
                return str.charAt(i);
            }
        }

        // Return null character if no non-repeating character is found
        return '\0';
    }
}
