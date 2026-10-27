class Solution {
public:
    int countIslands(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<bool>> visited(m, vector<bool>(n, false));

        int count = 0;

        function<long long(int, int)> dfs = [&](int r, int c) -> long long {
            if (r < 0 || r >= m || c < 0 || c >= n ||
                grid[r][c] == 0 || visited[r][c]) {
                return 0LL;
            }

            visited[r][c] = true;
            long long sum = grid[r][c];

            sum += dfs(r + 1, c);
            sum += dfs(r - 1, c);
            sum += dfs(r, c + 1);
            sum += dfs(r, c - 1);

            return sum;
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && !visited[i][j]) {
                    long long sum = dfs(i, j);

                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
};