class Solution {
    public int countDigits(int num) {
        int temp = num;// 1248
        int count = 0;
        while (num > 0) {
            int rem = num % 10;// 8
            num /= 10;
            if (temp % rem == 0)
                count++;// 1248%8==0 count++
        }
        return count;
    }
}
