
class Solution {
public:
    bool checkString(string s) {
        bool seenB = false;

        for (int i = 0; i < s.length(); i++) {
            if (s[i] == 'b') {
                seenB = true;
            }
            else if (s[i] == 'a' && seenB == true) {
                return false;
            }
        }

        return true;
    }
};
