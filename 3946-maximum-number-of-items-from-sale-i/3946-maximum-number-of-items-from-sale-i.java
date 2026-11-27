class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] bonus = new int[n];

        // Calculate free items for each item type
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    bonus[i]++;
                }
            }
        }

        int NEG = -1000000000;

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, NEG);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int extra = bonus[i];

            int[] ndp = dp.clone();

            // Process each remainder
            for (int r = 0; r < price && r <= budget; r++) {

                int best = NEG;

                for (int b = r; b <= budget; b += price) {

                    int s = (b - r) / price;

                    if (s >= 1) {
                        int prev = dp[b - price];

                        if (prev != NEG) {
                            best = Math.max(best, prev - (s - 1));
                        }
                    }

                    if (best != NEG) {
                        ndp[b] = Math.max(
                            ndp[b],
                            extra + s + best
                        );
                    }
                }
            }

            dp = ndp;
        }

        int ans = 0;

        for (int x : dp) {
            ans = Math.max(ans, x);
        }

        return ans;
    }
}