class Solution {
public:
    vector<int> findMissingElements(vector<int>& nums) {
        int left = *min_element(nums.begin(), nums.end());
        int right = *max_element(nums.begin(), nums.end());

        unordered_set<int> st(nums.begin(), nums.end());
        vector<int> ans;

        for (int i = left + 1; i < right; i++) {
            if (st.find(i) == st.end()) {
                ans.push_back(i);
            }
        }

        return ans;
    }
};