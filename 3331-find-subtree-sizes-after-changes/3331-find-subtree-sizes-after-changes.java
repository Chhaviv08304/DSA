
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Build original tree
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        int[] newParent = parent.clone();

        // Stores the nearest ancestor for each character
        int[] last = new int[26];
        Arrays.fill(last, -1);

        // Find new parents using DFS
        dfs1(0, adj, s, last, newParent);

        // Build modified tree
        List<Integer>[] newAdj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            newAdj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newAdj[newParent[i]].add(i);
        }

        // Calculate subtree sizes
        int[] ans = new int[n];
        dfs2(0, newAdj, ans);

        return ans;
    }

    private void dfs1(int node, List<Integer>[] adj,
                      String s, int[] last, int[] newParent) {

        int ch = s.charAt(node) - 'a';

        int previous = last[ch];

        if (node != 0 && previous != -1) {
            newParent[node] = previous;
        }

        // Save current node as nearest ancestor
        last[ch] = node;

        for (int child : adj[node]) {
            dfs1(child, adj, s, last, newParent);
        }

        // Restore previous ancestor while backtracking
        last[ch] = previous;
    }

    private int dfs2(int node, List<Integer>[] adj, int[] ans) {
        ans[node] = 1;

        for (int child : adj[node]) {
            ans[node] += dfs2(child, adj, ans);
        }

        return ans[node];
    }
}
