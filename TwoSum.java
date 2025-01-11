class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int res = target - nums[i];// 9-7
            if (hm.containsKey(res)) {
                int result[] = new int[2];
                result[0] = hm.get(res);
                result[1] = i;
                return result;
            }
            hm.put(nums[i], i);
        }
        return null;
    }
}
