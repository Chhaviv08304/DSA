
class Solution {
    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // right[i][j]: destination after entering mirror (i,j)
        // while moving right.
        // down[i][j]: destination after entering mirror (i,j)
        // while moving down.
        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        // -1 means invalid / out of bounds
        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(right[i], -1);
            java.util.Arrays.fill(down[i], -1);
        }

        // Precompute mirror destinations.
        // Reverse order ensures dependent states are already computed.
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if (grid[i][j] == 0) continue;

                // Enter mirror while moving RIGHT.
                // Reflection sends robot DOWN.
                if (i + 1 < m) {
                    if (grid[i + 1][j] == 0) {
                        right[i][j] = (i + 1) * n + j;
                    } else {
                        right[i][j] = down[i + 1][j];
                    }
                }

                // Enter mirror while moving DOWN.
                // Reflection sends robot RIGHT.
                if (j + 1 < n) {
                    if (grid[i][j + 1] == 0) {
                        down[i][j] = i * n + (j + 1);
                    } else {
                        down[i][j] = right[i][j + 1];
                    }
                }
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        // Count paths through empty cells only.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1 || dp[i][j] == 0) {
                    continue;
                }

                long ways = dp[i][j];

                // Move RIGHT
                if (j + 1 < n) {
                    int dest;

                    if (grid[i][j + 1] == 0) {
                        dest = i * n + (j + 1);
                    } else {
                        dest = right[i][j + 1];
                    }

                    if (dest != -1) {
                        int x = dest / n;
                        int y = dest % n;

                        dp[x][y] = (dp[x][y] + ways) % MOD;
                    }
                }

                // Move DOWN
                if (i + 1 < m) {
                    int dest;

                    if (grid[i + 1][j] == 0) {
                        dest = (i + 1) * n + j;
                    } else {
                        dest = down[i + 1][j];
                    }

                    if (dest != -1) {
                        int x = dest / n;
                        int y = dest % n;

                        dp[x][y] = (dp[x][y] + ways) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}
