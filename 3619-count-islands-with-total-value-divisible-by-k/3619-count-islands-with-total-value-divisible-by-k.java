
class Solution {
    private int m, n;
    private boolean[][] visited;

    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && !visited[i][j]) {
                    long sum = dfs(grid, i, j);

                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n
                || grid[r][c] == 0 || visited[r][c]) {
            return 0;
        }

        visited[r][c] = true;

        long sum = grid[r][c];

        sum += dfs(grid, r + 1, c);
        sum += dfs(grid, r - 1, c);
        sum += dfs(grid, r, c + 1);
        sum += dfs(grid, r, c - 1);

        return sum;
    }
}
