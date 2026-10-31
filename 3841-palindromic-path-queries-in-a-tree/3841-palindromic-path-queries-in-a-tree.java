
import java.util.*;

class Solution {

    int LOG;
    int[][] up;
    int[] depth, tin, tout, prefixMask, bit;
    List<Integer>[] adj;

    // Fenwick Tree XOR update
    void updateBIT(int idx, int val, int n) {
        for (idx++; idx <= n; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    // Fenwick Tree prefix XOR
    int queryBIT(int idx) {
        int ans = 0;

        for (idx++; idx > 0; idx -= idx & -idx) {
            ans ^= bit[idx];
        }

        return ans;
    }

    // Range XOR update
    void rangeUpdate(int l, int r, int val, int n) {
        updateBIT(l, val, n);

        if (r + 1 < n) {
            updateBIT(r + 1, val, n);
        }
    }

    // Current root-to-node mask
    int getMask(int u) {
        return prefixMask[u] ^ queryBIT(tin[u]);
    }

    // Lowest Common Ancestor
    int LCA(int u, int v) {

        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }

    public List<Boolean> palindromePath(
        int n,
        int[][] edges,
        String s,
        String[] queries
    ) {

        // Build adjacency list
        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        // Binary lifting initialization
        LOG = 1;

        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        prefixMask = new int[n];
        bit = new int[n + 1];

        // Iterative DFS
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int[] order = new int[n];
        int[] stack = new int[n];

        int top = 0;
        int timer = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {

            int u = stack[--top];

            tin[u] = timer++;
            order[timer - 1] = u;

            up[0][u] = parent[u];

            for (int j = 1; j < LOG; j++) {
                up[j][u] = up[j - 1][up[j - 1][u]];
            }

            prefixMask[u] =
                (1 << (s.charAt(u) - 'a')) ^
                (u == 0 ? 0 : prefixMask[parent[u]]);

            for (int v : adj[u]) {

                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;

                stack[top++] = v;
            }
        }

        // Calculate subtree sizes
        int[] sz = new int[n];
        Arrays.fill(sz, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            sz[parent[u]] += sz[u];
        }

        // Calculate subtree boundaries
        for (int u = 0; u < n; u++) {
            tout[u] = tin[u] + sz[u] - 1;
        }

        // Process queries
        List<Boolean> ans = new ArrayList<>();
        char[] chars = s.toCharArray();

        for (String q : queries) {

            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {

                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int delta =
                    (1 << (chars[u] - 'a')) ^
                    (1 << (c - 'a'));

                rangeUpdate(tin[u], tout[u], delta, n);

                chars[u] = c;
            }

            else {

                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int l = LCA(u, v);

                int mask =
                    getMask(u) ^
                    getMask(v) ^
                    (1 << (chars[l] - 'a'));

                boolean valid =
                    (mask & (mask - 1)) == 0;

                ans.add(valid);
            }
        }

        return ans;
    }
}
