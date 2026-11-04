
import java.util.*;

class Solution {

    class Fenwick {
        int n;
        int[] count;
        long[] sum;

        Fenwick(int n) {
            this.n = n;
            count = new int[n + 1];
            sum = new long[n + 1];
        }

        void add(int i, int c, long s) {
            while (i <= n) {
                count[i] += c;
                sum[i] += s;
                i += i & -i;
            }
        }

        int queryCount(int i) {
            int ans = 0;
            while (i > 0) {
                ans += count[i];
                i -= i & -i;
            }
            return ans;
        }

        long querySum(int i) {
            long ans = 0;
            while (i > 0) {
                ans += sum[i];
                i -= i & -i;
            }
            return ans;
        }

        int kth(int k) {
            int idx = 0;

            for (int step = Integer.highestOneBit(n);
                 step > 0; step >>= 1) {

                int next = idx + step;

                if (next <= n && count[idx + step] < 0) {
                    // Never used; see the standard implementation below.
                }
            }

            int pos = 0;
            int accumulated = 0;

            for (int step = Integer.highestOneBit(n);
                 step > 0; step >>= 1) {

                int next = pos + step;

                if (next <= n &&
                    accumulated + count[next] < k) {

                    pos = next;
                    accumulated += count[next];
                }
            }

            return pos + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;

        for (int v : sorted) {
            if (m == 0 || sorted[m - 1] != v) {
                sorted[m++] = v;
            }
        }

        int[] values = Arrays.copyOf(sorted, m);

        int[] index = new int[n];

        for (int i = 0; i < n; i++) {
            index[i] = Arrays.binarySearch(values, nums[i]) + 1;
        }

        // Calculate cost of every window
        Fenwick bit = new Fenwick(m);

        long[] cost = new long[n - x + 1];

        long windowSum = 0;

        for (int i = 0; i < x; i++) {
            bit.add(index[i], 1, nums[i]);
            windowSum += nums[i];
        }

        for (int i = 0; i <= n - x; i++) {

            // Find median
            int mid = bit.kth((x + 1) / 2);
            long median = values[mid - 1];

            int leftCount = bit.queryCount(mid);
            long leftSum = bit.querySum(mid);

            int rightCount = x - leftCount;
            long rightSum = windowSum - leftSum;

            // Minimum operations for this window
            cost[i] =
                median * leftCount - leftSum
                + rightSum - median * rightCount;

            // Slide window
            if (i < n - x) {

                bit.add(index[i], -1, -((long) nums[i]));
                windowSum -= nums[i];

                bit.add(index[i + x], 1, nums[i + x]);
                windowSum += nums[i + x];
            }
        }

        // Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Skip current element
                curr[i] = curr[i - 1];

                // Select a window of size x
                if (i >= x && prev[i - x] != INF) {

                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
