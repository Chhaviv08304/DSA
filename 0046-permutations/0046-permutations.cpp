class Solution {
public:
    vector<vector<int>> ans;

    void backtrack(vector<int>& nums, vector<int>& curr, vector<bool>& used) {
        if (curr.size() == nums.size()) {
            ans.push_back(curr);
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            if (used[i]) continue;

            curr.push_back(nums[i]);
            used[i] = true;

            backtrack(nums, curr, used);

            curr.pop_back();      // undo choice
            used[i] = false;
        }
    }

    vector<vector<int>> permute(vector<int>& nums) {
        vector<int> curr;
        vector<bool> used(nums.size(), false);
        backtrack(nums, curr, used);
        return ans;
    }
};