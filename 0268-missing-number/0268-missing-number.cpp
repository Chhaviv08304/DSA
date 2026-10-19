class Solution {
public:
    int missingNumber(vector<int>& nums) {
        int n=nums.size();
        int actualSum=0;
        int expectedSum=n*(n+1)/2;
        int result=0;
        sort(nums.begin(),nums.end());
        for(int i=0;i<n;i++){
            actualSum+=nums[i];
        }
        result=expectedSum-actualSum;
        return result;
    }
};