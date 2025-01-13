class Solution {
    public char findTheDifference(String s, String t) {
        int n = s.length();
        int sum = t.charAt(n);
        for(int i=0;i<n;i++){
            sum+=t.charAt(i)-s.charAt(i);
        }
        return (char)sum;
    }
}
