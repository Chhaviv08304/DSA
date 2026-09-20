class Solution {
    public int reverseDegree(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans += (27 - (s.charAt(i) - 'a' + 1)) * (i + 1);
        }
        return ans;
    }
}