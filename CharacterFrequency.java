import java.util.HashMap;

public class CharacterFrequency {
    public static void main(String[] args) {
        String str = "hello world";
        countCharacterFrequency(str);
    }

    public static void countCharacterFrequency(String str) {
        HashMap<Character, Integer> charCountMap = new HashMap<>();

        // Loop through each character in the string
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // Ignore spaces if you don't want them counted
            if (ch != ' ') {
                // Update the count for each character
                charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
            }
        }

        // Print the frequency of each character
        for (HashMap.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
