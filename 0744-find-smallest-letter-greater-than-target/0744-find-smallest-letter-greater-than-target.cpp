class Solution {
public:
    char nextGreatestLetter(vector<char>& letters, char target) {
        long long left = 0;
        long long right = letters.size()- 1;
        while(left <= right){
            long long mid = left + (right - left) / 2;
            if(letters[mid] <= target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return letters[left % letters.size()];
    }
};