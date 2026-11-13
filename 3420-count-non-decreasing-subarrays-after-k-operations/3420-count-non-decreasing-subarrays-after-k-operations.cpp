class Solution {
public:
    long long countNonDecreasingSubarrays(vector<int>& nums, int k) {
        int n = nums.size();

        deque<pair<int, int>> dq;
        long long cost = 0;
        long long ans = 0;

        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            int count = 1;

            // Merge smaller elements into the current element
            while (!dq.empty() && dq.back().first < num) {
                auto [nextNum, nextCount] = dq.back();
                dq.pop_back();

                count += nextCount;

                cost += 1LL * (num - nextNum) * nextCount;
            }

            dq.push_back({num, count});

            // Shrink window if operations exceed k
            while (cost > k) {
                auto [rightNum, rightCount] = dq.front();
                dq.pop_front();

                cost -= 1LL * (rightNum - nums[j]);
                j--;

                if (rightCount > 1) {
                    dq.push_front({rightNum, rightCount - 1});
                }
            }

            ans += j - i + 1;
        }

        return ans;
    }
};