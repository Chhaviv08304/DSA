import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        int litterCount = 0;

        // Assign a bit number to every litter cell
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // No litter to collect
        if (litterCount == 0) {
            return 0;
        }

        int fullMask = (1 << litterCount) - 1;

        /*
         * best[r][c][mask] = maximum energy remaining
         * when reaching (r, c) after collecting 'mask'
         */
        int[][][] best = new int[m][n][1 << litterCount];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(best[i][j], -1);
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        // {row, col, collectedMask, remainingEnergy, moves}
        queue.offer(new int[]{sr, sc, 0, energy, 0});
        best[sr][sc][0] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            int r = curr[0];
            int c = curr[1];
            int mask = curr[2];
            int remainingEnergy = curr[3];
            int moves = curr[4];

            // Already collected all litter
            if (mask == fullMask) {
                return moves;
            }

            // Cannot make another move with 0 energy
            if (remainingEnergy == 0) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // Boundary check
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                // Obstacle check
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                int newEnergy = remainingEnergy - 1;
                int newMask = mask;
                char cell = classroom[nr].charAt(nc);

                // Collect litter
                if (cell == 'L') {
                    newMask |= (1 << litterId[nr][nc]);
                }

                // Reset energy
                if (cell == 'R') {
                    newEnergy = energy;
                }

                /*
                 * If we have already reached the same state
                 * with equal or more energy, skip it.
                 */
                if (best[nr][nc][newMask] >= newEnergy) {
                    continue;
                }

                best[nr][nc][newMask] = newEnergy;
                queue.offer(new int[]{
                    nr, nc, newMask, newEnergy, moves + 1
                });
            }
        }

        return -1;
    }
}