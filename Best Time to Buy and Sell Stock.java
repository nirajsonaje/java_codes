class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minprice = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < minprice) {
                minprice = price;
            } else {
                int profit = price - minprice;
                maxProfit = Math.max(maxProfit, profit);
            }
        }
        return maxProfit;
    }
}
