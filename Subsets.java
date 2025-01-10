class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        // Initialize the list to store all subsets
        List<List<Integer>> list = new ArrayList<>();
        
        // Sort the input array to ensure subsets are generated in lexicographical order
        Arrays.sort(nums);
        
        // Call the recursive backtrack function to generate subsets
        backtrack(list, new ArrayList<>(), nums, 0);
        
        // Return the list of all subsets
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        // Add the current subset (tempList) to the list of subsets
        list.add(new ArrayList<>(tempList));
        
        // Iterate through the array starting from the current index `start`
        for (int i = start; i < nums.length; i++) {
            // Include nums[i] in the current subset
            tempList.add(nums[i]);
            
            // Recursively call backtrack to generate all subsets including nums[i]
            backtrack(list, tempList, nums, i + 1);
            
            // Remove the last element (backtrack step) to explore other subsets
            tempList.remove(tempList.size() - 1);
        }
    }
}
