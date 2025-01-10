class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        // Start recursion with an empty string, 0 open and close parentheses
        backtrack(result, "", 0, 0, n);

        return result;
    }

    // Recursive helper method to perform backtracking
    private static void backtrack(List<String> result, String current, int open, int close, int max) {
        // Base case: If the current string's length equals 2 * n
        if (current.length() == max * 2) {
            result.add(current); // Add the valid combination to the result list
            return; // Terminate this recursive path
        }

        // Recursive case:
        // Add '(' if the count of open parentheses is less than max
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }

        // Add ')' if the count of close parentheses is less than open parentheses
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }

}
