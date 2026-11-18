
class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        // Step 1: Precompute minimum changes
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {

            for (int i = 0; i + len <= n; i++) {

                int j = i + len - 1;
                int best = Integer.MAX_VALUE;

                // Try all valid divisors
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Check palindrome groups
                    for (int r = 0; r < d; r++) {

                        int left = i + r;
                        int right = i + r + ((len - 1) / d) * d;

                        while (left < right) {

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[i][j] = best;
            }
        }

        // Step 2: DP
        int INF = 1000000000;

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            java.util.Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {

            for (int i = 2 * p; i <= n; i++) {

                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    if (dp[j][p - 1] == INF) {
                        continue;
                    }

                    dp[i][p] = Math.min(
                        dp[i][p],
                        dp[j][p - 1] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
