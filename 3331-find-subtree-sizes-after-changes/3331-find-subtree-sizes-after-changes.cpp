
class Solution {
public:
    vector<int> findSubtreeSizes(vector<int>& parent, string s) {
        int n = parent.size();

        vector<vector<int>> adj(n);

        // Build original tree
        for (int i = 1; i < n; i++) {
            adj[parent[i]].push_back(i);
        }

        vector<int> newParent = parent;
        vector<int> last(26, -1);

        // First DFS: Find nearest matching ancestor
        function<void(int)> dfs1 = [&](int node) {
            int ch = s[node] - 'a';

            int prev = last[ch];

            if (node != 0 && prev != -1) {
                newParent[node] = prev;
            }

            last[ch] = node;

            for (int child : adj[node]) {
                dfs1(child);
            }

            // Backtrack
            last[ch] = prev;
        };

        dfs1(0);

        // Build modified tree
        vector<vector<int>> newAdj(n);

        for (int i = 1; i < n; i++) {
            newAdj[newParent[i]].push_back(i);
        }

        vector<int> ans(n, 1);

        // Second DFS: Calculate subtree sizes
        function<int(int)> dfs2 = [&](int node) {
            int size = 1;

            for (int child : newAdj[node]) {
                size += dfs2(child);
            }

            ans[node] = size;
            return size;
        };

        dfs2(0);

        return ans;
    }
};
