class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>(); // This will store all the combinations.
        List<Integer> comb = new ArrayList<>();      // Temporary list to hold the current combination.

        // Start backtracking from 1, with the input constraints n and k.
        backtrack(1, comb, res, n, k);
        return res; // Return the final result containing all valid combinations.
    }

    private void backtrack(int start, List<Integer> comb, List<List<Integer>> res, int n, int k) {
        // If the current combination reaches size k, add it to the result list.
        if (comb.size() == k) {
            res.add(new ArrayList<>(comb)); // Make a copy of comb and add it to res.
            return;
        }

        // Loop from the current start number up to n.
        for (int num = start; num <= n; num++) {
            comb.add(num);                    // Add the current number to the combination.
            backtrack(num + 1, comb, res, n, k); // Recursively build combinations starting from the next number.
            comb.remove(comb.size() - 1);     // Remove the last added number to backtrack and explore other options.
        }
    } 
}
