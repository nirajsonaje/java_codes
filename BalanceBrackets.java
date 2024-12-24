class Solution {
    public boolean isBalanced(String num) {
        long odd = 0, even = 0;
        for (int i = 0; i < num.length(); i++) {
            if (i % 2 == 0) {
                even += num.charAt(i) - '0';
            } else {
                odd += num.charAt(i) - '0';
            }
        }
        return odd == even;
    }
}
