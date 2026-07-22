#include <iostream>
#include <unordered_set>
#include <string>
using namespace std;

class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        unordered_set<char> seen;
        int left = 0, right = 0;
        int maxLength = 0;

        while (right < s.size()) {
            if (seen.find(s[right]) == seen.end()) {
                seen.insert(s[right]);
                maxLength = max(maxLength, right - left + 1);
                right++;
            } else {
                // Remove the leftmost character and shrink the window
                seen.erase(s[left]);
                left++;
            }
        }

        return maxLength;
    }
};
