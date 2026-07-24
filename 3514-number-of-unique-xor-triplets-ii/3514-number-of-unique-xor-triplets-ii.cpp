class Solution {
public:
    int uniqueXorTriplets(vector<int>& nums) {
        const int MAXX = 2048;

        vector<int> values;
        vector<bool> seenVal(1501, false);

        for (int x : nums) {
            if (!seenVal[x]) {
                seenVal[x] = true;
                values.push_back(x);
            }
        }

        vector<bool> dp1(MAXX, false);
        vector<bool> dp2(MAXX, false);
        vector<bool> dp3(MAXX, false);

        for (int v : values)
            dp1[v] = true;

        for (int a = 0; a < MAXX; a++) {
            if (!dp1[a]) continue;
            for (int v : values)
                dp2[a ^ v] = true;
        }

        for (int a = 0; a < MAXX; a++) {
            if (!dp2[a]) continue;
            for (int v : values)
                dp3[a ^ v] = true;
        }

        int ans = 0;
        for (bool x : dp3)
            if (x) ans++;

        return ans;
    }
};