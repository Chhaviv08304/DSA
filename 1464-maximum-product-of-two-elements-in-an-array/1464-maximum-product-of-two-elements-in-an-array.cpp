class Solution {
public:
    int maxProduct(vector<int>& nums) {
        int j=nums.size()-1;
        int i=j-1;
        sort(nums.begin(),nums.end());
        return (nums[i]-1)*(nums[j]-1);
    }
};