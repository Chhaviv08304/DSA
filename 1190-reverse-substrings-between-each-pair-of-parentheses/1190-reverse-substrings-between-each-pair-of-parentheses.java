class Solution {
    public String reverseParentheses(String s) {
        Stack<String> st = new Stack<>();
        st.push("");

        for (char c : s.toCharArray()) {
            if (c == '(') {
                st.push("");
            } else if (c == ')') {
                String str = st.pop();
                st.push(st.pop() + new StringBuilder(str).reverse());
            } else {
                st.push(st.pop() + c);
            }
        }

        return st.pop();
    }
}