class Solution {
    public int maxDepth(String s) {
        int depth = 0;
        int maxDepth = 0;

        for (char val :  s.toCharArray()) {
            if (val == '(') {
                depth++;
                maxDepth = Math.max(maxDepth, depth);
            } else if (val == ')') {
                depth--;
            }
        }

        return maxDepth;
    }
}