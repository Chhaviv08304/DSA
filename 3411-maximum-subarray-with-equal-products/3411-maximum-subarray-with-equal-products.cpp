
class Solution {
public:
    int maxLength(vector<int>& nums) {
        int n = nums.size();
        int ans = 1;

        for (int i = 0; i < n; i++) {
            long long product = 1;
            long long g = 0;
            long long l = 1;

            for (int j = i; j < n; j++) {

                // Prevent overflow
                if (product > LLONG_MAX / nums[j])
                    break;

                product *= nums[j];

                g = gcd(g, (long long)nums[j]);

                l = l / gcd(l, (long long)nums[j]) * nums[j];

                if (product == g * l) {
                    ans = max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }
};
