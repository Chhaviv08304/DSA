
class Solution {
public:
    int minimumChanges(string s, int k) {
        int n = s.size();

        // Step 1: Precompute costs
        vector<vector<int>> cost(n, vector<int>(n, 0));

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i + l <= n; i++) {

                int j = i + l - 1;
                int best = INT_MAX;

                // Try all valid divisors
                for (int d = 1; d < l; d++) {

                    if (l % d != 0)
                        continue;

                    int changes = 0;

                    // Check each group
                    for (int r = 0; r < d; r++) {

                        int left = i + r;
                        int right = i + r + ((l - 1) / d) * d;

                        while (left < right) {

                            if (s[left] != s[right])
                                changes++;

                            left += d;
                            right -= d;
                        }
                    }

                    best = min(best, changes);
                }

                cost[i][j] = best;
            }
        }

        // Step 2: Dynamic Programming
        const int INF = 1e9;

        vector<vector<int>> dp(
            n + 1, vector<int>(k + 1, INF)
        );

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {

            for (int i = 2 * p; i <= n; i++) {

                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    if (dp[j][p - 1] == INF)
                        continue;

                    dp[i][p] = min(
                        dp[i][p],
                        dp[j][p - 1] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
};
