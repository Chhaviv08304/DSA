class Solution {
public:
    unordered_set<string> ans;

    void solve(string &s, int index, int open, int leftRemove, int rightRemove, string current) {
        if (index == s.size()) {
            if (open == 0 && leftRemove == 0 && rightRemove == 0) {
                ans.insert(current);
            }
            return;
        }

        char ch = s[index];

        if (ch == '(') {
            if (leftRemove > 0) {
                solve(s, index + 1, open, leftRemove - 1, rightRemove, current);
            }

            solve(s, index + 1, open + 1, leftRemove, rightRemove, current + ch);
        }
        else if (ch == ')') {
            if (rightRemove > 0) {
                solve(s, index + 1, open, leftRemove, rightRemove - 1, current);
            }

            if (open > 0) {
                solve(s, index + 1, open - 1, leftRemove, rightRemove, current + ch);
            }
        }
        else {
            solve(s, index + 1, open, leftRemove, rightRemove, current + ch);
        }
    }

    vector<string> removeInvalidParentheses(string s) {
        int open = 0;
        int leftRemove = 0;
        int rightRemove = 0;

        for (char ch : s) {
            if (ch == '(') {
                open++;
            }
            else if (ch == ')') {
                if (open > 0) {
                    open--;
                }
                else {
                    rightRemove++;
                }
            }
        }

        leftRemove = open;

        solve(s, 0, 0, leftRemove, rightRemove, "");

        return vector<string>(ans.begin(), ans.end());
    }
};