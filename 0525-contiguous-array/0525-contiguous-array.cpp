class Solution {
public:
    int findMaxLength(std::vector<int>& nums) {
        int running_sum = 0;
        int max_length = 0;
        
        std::unordered_map<int, int> sum_indices;
        sum_indices[0] = -1; 
        
        for (int i = 0; i < nums.size(); ++i) {
            
            running_sum += (nums[i] == 1) ? 1 : -1;
            if (sum_indices.find(running_sum) != sum_indices.end()) {
                int current_length = i - sum_indices[running_sum];
                max_length = std::max(max_length, current_length);
            } else {
                sum_indices[running_sum] = i;
            }
        }
        
        return max_length;
    }
};