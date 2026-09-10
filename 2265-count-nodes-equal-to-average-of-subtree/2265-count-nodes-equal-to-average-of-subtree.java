class Solution {
    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    public int[] dfs(TreeNode root) {

        // Base case
        if (root == null) {
            return new int[]{0, 0};
        }

        // Left subtree
        int[] left = dfs(root.left);

        // Right subtree
        int[] right = dfs(root.right);

        // Sum of current subtree
        int sum = root.val + left[0] + right[0];

        // Number of nodes in current subtree
        int count = 1 + left[1] + right[1];

        // Check average
        if (root.val == sum / count) {
            ans++;
        }

        // Return sum and count
        return new int[]{sum, count};
    }
}