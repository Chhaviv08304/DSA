
class Solution {
public:
    static const int MOD = 1e9 + 7;

    int uniquePaths(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();

        vector<vector<int>> right(m, vector<int>(n, -1));
        vector<vector<int>> down(m, vector<int>(n, -1));

        // Precompute mirror destinations
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if (grid[i][j] == 0) continue;

                // Entered while moving right -> reflect down
                if (i + 1 < m) {
                    if (grid[i + 1][j] == 0) {
                        right[i][j] = (i + 1) * n + j;
                    } else {
                        right[i][j] = down[i + 1][j];
                    }
                }

                // Entered while moving down -> reflect right
                if (j + 1 < n) {
                    if (grid[i][j + 1] == 0) {
                        down[i][j] = i * n + j + 1;
                    } else {
                        down[i][j] = right[i][j + 1];
                    }
                }
            }
        }

        vector<vector<long long>> dp(
            m, vector<long long>(n, 0)
        );

        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1 || dp[i][j] == 0)
                    continue;

                long long ways = dp[i][j];

                // Move right
                if (j + 1 < n) {
                    int dest;

                    if (grid[i][j + 1] == 0)
                        dest = i * n + j + 1;
                    else
                        dest = right[i][j + 1];

                    if (dest != -1) {
                        int x = dest / n;
                        int y = dest % n;

                        dp[x][y] = (dp[x][y] + ways) % MOD;
                    }
                }

                // Move down
                if (i + 1 < m) {
                    int dest;

                    if (grid[i + 1][j] == 0)
                        dest = (i + 1) * n + j;
                    else
                        dest = down[i + 1][j];

                    if (dest != -1) {
                        int x = dest / n;
                        int y = dest % n;

                        dp[x][y] = (dp[x][y] + ways) % MOD;
                    }
                }
            }
        }

        return dp[m - 1][n - 1];
    }
};
