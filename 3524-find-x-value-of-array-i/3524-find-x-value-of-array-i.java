class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] next = new long[k];
            int r = num % k;

            next[r]++;

            for (int j = 0; j < k; j++) {
                next[(j * r) % k] += dp[j];
            }

            for (int j = 0; j < k; j++) {
                ans[j] += next[j];
            }

            dp = next;
        }

        return ans;
    }
}