
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] sa = new int[n];
        int[] rank = new int[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = nums[i];
        }

        for (int k = 1; k < n; k *= 2) {
            final int len = k;
            final int[] r = rank;

            Integer[] idx = new Integer[n];

            for (int i = 0; i < n; i++)
                idx[i] = i;

            Arrays.sort(idx, (a, b) -> {
                if (r[a] != r[b])
                    return Integer.compare(r[a], r[b]);

                int ra = a + len < n ? r[a + len] : -1;
                int rb = b + len < n ? r[b + len] : -1;

                return Integer.compare(ra, rb);
            });

            for (int i = 0; i < n; i++)
                sa[i] = idx[i];

            temp[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                int a2 = a + len < n ? rank[a + len] : -1;
                int b2 = b + len < n ? rank[b + len] : -1;

                temp[b] = temp[a] +
                    ((rank[a] != rank[b] || a2 != b2) ? 1 : 0);
            }

            int[] swap = rank;
            rank = temp;
            temp = swap;

            if (rank[sa[n - 1]] == n - 1)
                break;
        }

        int[] pos = new int[n];

        for (int i = 0; i < n; i++)
            pos[sa[i]] = i;

        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            if (r == 0)
                continue;

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h])
                h++;

            lcp[r] = h;

            if (h > 0)
                h--;
        }

        int ans = n;

        for (int i = 0; i < n; i++) {
            int maxLcp = lcp[i];

            if (i + 1 < n)
                maxLcp = Math.max(maxLcp, lcp[i + 1]);

            int required = maxLcp + 1;

            if (required <= n - sa[i])
                ans = Math.min(ans, required);
        }

        return ans;
    }
}
