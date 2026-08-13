class Solution {

    class Node {
        int len;

        int left;
        int right;
        int best;

        char leftChar;
        char rightChar;

        Node() {
        }

        Node(char c) {
            len = 1;
            left = 1;
            right = 1;
            best = 1;
            leftChar = c;
            rightChar = c;
        }
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(String s, String queryCharacters,
                                  int[] queryIndices) {

        this.s = s.toCharArray();

        int n = s.length();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char c = queryCharacters.charAt(i);

            this.s[index] = c;

            update(1, 0, n - 1, index, c);

            ans[i] = tree[1].best;
        }

        return ans;
    }

    // Build segment tree
    void build(int node, int start, int end) {

        if (start == end) {
            tree[node] = new Node(s[start]);
            return;
        }

        int mid = start + (end - start) / 2;

        build(node * 2, start, mid);
        build(node * 2 + 1, mid + 1, end);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Update one position
    void update(int node, int start, int end, int index, char c) {

        if (start == end) {
            tree[node] = new Node(c);
            return;
        }

        int mid = start + (end - start) / 2;

        if (index <= mid) {
            update(node * 2, start, mid, index, c);
        } else {
            update(node * 2 + 1, mid + 1, end, index, c);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two segments
    Node merge(Node a, Node b) {

        Node res = new Node();

        res.len = a.len + b.len;

        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        // Best answer from either side
        res.best = Math.max(a.best, b.best);

        // Prefix
        res.left = a.left;

        if (a.left == a.len && a.rightChar == b.leftChar) {
            res.left = a.len + b.left;
        }

        // Suffix
        res.right = b.right;

        if (b.right == b.len && a.rightChar == b.leftChar) {
            res.right = b.len + a.right;
        }

        // Join suffix of left + prefix of right
        if (a.rightChar == b.leftChar) {
            res.best = Math.max(
                res.best,
                a.right + b.left
            );
        }

        return res;
    }
}