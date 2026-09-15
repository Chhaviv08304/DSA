class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[] ans = new int[n + 1];

        for (int i = n - 1; i >= 0; i--)
            for (int j = i; j < n; j++)
                dp[i][j] = s.charAt(i) == s.charAt(j) &&
                           (j - i < 2 || dp[i + 1][j - 1]);

        for (int i = 0; i < n; i++) {
            ans[i + 1] = Math.max(ans[i + 1], ans[i]);
            for (int j = i + k - 1; j < n; j++)
                if (dp[i][j])
                    ans[j + 1] = Math.max(ans[j + 1], ans[i] + 1);
        }

        return ans[n];
    }
}