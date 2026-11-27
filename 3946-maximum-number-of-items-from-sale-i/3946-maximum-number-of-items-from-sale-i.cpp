class Solution {
public:
    int maximumSaleItems(vector<vector<int>>& items, int budget) {
        int n = items.size();

        vector<int> bonus(n, 0);

        // Calculate free items for every item
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    bonus[i]++;
                }
            }
        }

        const int NEG = -1000000000;

        vector<int> dp(budget + 1, NEG);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int p = items[i][1];
            int extra = bonus[i];

            vector<int> ndp = dp;

            // Process each remainder modulo price
            for (int r = 0; r < p && r <= budget; r++) {

                int best = NEG;

                for (int b = r; b <= budget; b += p) {

                    int s = (b - r) / p;

                    // Add previous state for buying
                    // at least one copy of this item
                    if (s >= 1) {
                        int prev = dp[b - p];

                        if (prev != NEG) {
                            best = max(best, prev - (s - 1));
                        }
                    }

                    if (best != NEG) {
                        ndp[b] = max(
                            ndp[b],
                            extra + s + best
                        );
                    }
                }
            }

            dp.swap(ndp);
        }

        return *max_element(dp.begin(), dp.end());
    }
};