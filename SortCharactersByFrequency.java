// HashMap to count frequencies

// PriorityQueue (Max Heap) to sort by frequency

// StringBuilder to construct the result
import java.util.*;

public class SortCharactersByFrequency {
    public static String frequencySort(String s) {
        // Step 1: Count frequency of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Add entries to max heap sorted by frequency
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
            new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        maxHeap.addAll(freqMap.entrySet());

        // Step 3: Build result string
        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            char ch = entry.getKey();
            int count = entry.getValue();
            // Append character 'count' times
            for (int i = 0; i < count; i++) {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        String input = "tree";
        System.out.println(frequencySort(input));  // Output: "eetr" or "eert"
    }
}
// 💡 Time and Space Complexity
// Time Complexity:

// Counting frequencies: O(n)

// Heap operations: O(n log n)

// Result building: O(n)
// → Overall: O(n log n)

// Space Complexity: O(n) for map + heap + result

