class Solution {
private:
    bool check(vector<int>& nums, vector<int>& sorted,
               int k, vector<int>& freq) {
        int n = nums.size();

        for (int start = 0; start < n; start += k) {
            int end = start + k;
            bool valid = true;
            int drops = 0;

            // Check whether this block is a cyclically
            // sorted sequence.
            for (int i = start; i < end - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    drops++;
                }
            }

            if (k > 1 && nums[end - 1] > nums[start]) {
                drops++;
            }

            if (drops > 1) {
                valid = false;
            }

            // Compare the block's elements with the
            // corresponding segment of the sorted array.
            for (int i = start; i < end; i++) {
                freq[nums[i]]++;
                freq[sorted[i]]--;
            }

            for (int i = start; i < end; i++) {
                if (freq[nums[i]] != 0 ||
                    freq[sorted[i]] != 0) {
                    valid = false;
                }
            }

            // Reset frequencies for the next block.
            for (int i = start; i < end; i++) {
                freq[nums[i]] = 0;
                freq[sorted[i]] = 0;
            }

            if (!valid) {
                return false;
            }
        }

        return true;
    }

public:
    int sortableIntegers(vector<int>& nums) {
        int n = nums.size();

        vector<int> sorted = nums;
        sort(sorted.begin(), sorted.end());

        vector<int> freq(100001, 0);
        int ans = 0;

        // Enumerate divisors in pairs.
        for (int k = 1; k <= n / k; k++) {
            if (n % k != 0) {
                continue;
            }

            if (check(nums, sorted, k, freq)) {
                ans += k;
            }

            int other = n / k;

            if (other != k &&
                check(nums, sorted, other, freq)) {
                ans += other;
            }
        }

        return ans;
    }
};