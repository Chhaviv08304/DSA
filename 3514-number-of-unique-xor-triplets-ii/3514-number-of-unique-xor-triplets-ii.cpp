class Solution {
public:
    int uniqueXorTriplets(vector<int>& nums) {
        const int MAXX = 2048;

        vector<bool> one(MAXX, false);
        vector<bool> two(MAXX, false);
        vector<bool> three(MAXX, false);

        for (int a : nums)
            one[a] = true;

        for (int x = 0; x < MAXX; x++) {
            if (!one[x]) continue;
            for (int a : nums)
                two[x ^ a] = true;
        }

        for (int x = 0; x < MAXX; x++) {
            if (!two[x]) continue;
            for (int a : nums)
                three[x ^ a] = true;
        }

        int ans = 0;
        for (bool x : three)
            if (x) ans++;

        return ans;
    }
};