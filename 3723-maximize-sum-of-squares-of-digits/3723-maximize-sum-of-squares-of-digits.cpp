class Solution {
public:
    string maxSumOfSquares(int num, int sum) {
        if (sum > num * 9 || sum == 0) {
            return "";
        }

        string ans = "";

        for (int i = 0; i < num; i++) {
            if (sum >= 9) {
                ans += '9';
                sum -= 9;
            }
            else if (sum > 0) {
                ans += char('0' + sum);
                sum = 0;
            }
            else {
                ans += '0';
            }
        }

        return ans;
    }
};