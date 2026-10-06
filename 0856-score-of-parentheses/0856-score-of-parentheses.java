import java.util.*;

class Solution {
    public int scoreOfParentheses(String s) {
        Deque<Integer> st = new ArrayDeque<>();
        st.push(0);

        for (char c : s.toCharArray()) {
            if (c == '(') {
                st.push(0);
            } else {
                int val = st.pop();
                int score = val == 0 ? 1 : 2 * val;
                st.push(st.pop() + score);
            }
        }

        return st.peek();
    }
}