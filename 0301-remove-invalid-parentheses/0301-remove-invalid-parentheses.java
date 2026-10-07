class Solution {
    Set<String> ans = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        int open = 0;
        int leftRemove = 0;
        int rightRemove = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                open++;
            } else if (ch == ')') {
                if (open > 0) {
                    open--;
                } else {
                    rightRemove++;
                }
            }
        }

        leftRemove = open;

        solve(s, 0, 0, leftRemove, rightRemove, new StringBuilder());

        return new ArrayList<>(ans);
    }

    private void solve(String s, int index, int open,
                       int leftRemove, int rightRemove,
                       StringBuilder current) {

        if (index == s.length()) {
            if (open == 0 && leftRemove == 0 && rightRemove == 0) {
                ans.add(current.toString());
            }
            return;
        }

        char ch = s.charAt(index);

        if (ch == '(') {

            if (leftRemove > 0) {
                solve(s, index + 1, open,
                      leftRemove - 1, rightRemove, current);
            }

            current.append(ch);

            solve(s, index + 1, open + 1,
                  leftRemove, rightRemove, current);

            current.deleteCharAt(current.length() - 1);

        } else if (ch == ')') {

            if (rightRemove > 0) {
                solve(s, index + 1, open,
                      leftRemove, rightRemove - 1, current);
            }

            if (open > 0) {
                current.append(ch);

                solve(s, index + 1, open - 1,
                      leftRemove, rightRemove, current);

                current.deleteCharAt(current.length() - 1);
            }

        } else {

            current.append(ch);

            solve(s, index + 1, open,
                  leftRemove, rightRemove, current);

            current.deleteCharAt(current.length() - 1);
        }
    }
}