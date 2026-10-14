class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > num * 9 || sum == 0) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < num; i++) {
            if (sum >= 9) {
                ans.append('9');
                sum -= 9;
            } else if (sum > 0) {
                ans.append((char) ('0' + sum));
                sum = 0;
            } else {
                ans.append('0');
            }
        }

        return ans.toString();
    }
}