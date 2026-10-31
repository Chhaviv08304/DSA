
class Solution {
    int LOG;
    vector<vector<int>> up, adj;
    vector<int> depth, tin, tout, prefixMask, bit;

    void updateBIT(int idx, int val, int n) {
        for (idx++; idx <= n; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    int queryBIT(int idx) {
        int ans = 0;
        for (idx++; idx > 0; idx -= idx & -idx) {
            ans ^= bit[idx];
        }
        return ans;
    }

    void rangeUpdate(int l, int r, int val, int n) {
        updateBIT(l, val, n);
        if (r + 1 < n) {
            updateBIT(r + 1, val, n);
        }
    }

    int getMask(int u) {
        return prefixMask[u] ^ queryBIT(tin[u]);
    }

    int LCA(int u, int v) {
        if (depth[u] < depth[v]) swap(u, v);

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if (diff & (1 << j)) {
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

public:
    vector<bool> palindromePath(
        int n,
        vector<vector<int>>& edges,
        string s,
        vector<string>& queries
    ) {
        adj.assign(n, {});

        for (auto &e : edges) {
            adj[e[0]].push_back(e[1]);
            adj[e[1]].push_back(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up.assign(LOG, vector<int>(n, 0));
        depth.assign(n, 0);
        tin.assign(n, 0);
        tout.assign(n, 0);
        prefixMask.assign(n, 0);
        bit.assign(n + 1, 0);

        vector<int> parent(n, -1), order;
        stack<int> st;

        st.push(0);
        parent[0] = 0;

        int timer = 0;

        while (!st.empty()) {
            int u = st.top();
            st.pop();

            tin[u] = timer++;
            order.push_back(u);

            up[0][u] = parent[u];

            for (int j = 1; j < LOG; j++) {
                up[j][u] = up[j - 1][up[j - 1][u]];
            }

            prefixMask[u] =
                (1 << (s[u] - 'a')) ^
                (u == 0 ? 0 : prefixMask[parent[u]]);

            for (int v : adj[u]) {
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                st.push(v);
            }
        }

        vector<int> sz(n, 1);

        for (int i = n - 1; i > 0; i--) {
            sz[parent[order[i]]] += sz[order[i]];
        }

        for (int u = 0; u < n; u++) {
            tout[u] = tin[u] + sz[u] - 1;
        }

        vector<bool> ans;

        for (string &q : queries) {
            string type;
            stringstream ss(q);

            ss >> type;

            if (type == "update") {
                int u;
                char c;

                ss >> u >> c;

                int delta =
                    (1 << (s[u] - 'a')) ^
                    (1 << (c - 'a'));

                rangeUpdate(tin[u], tout[u], delta, n);

                s[u] = c;
            }
            else {
                int u, v;
                ss >> u >> v;

                int l = LCA(u, v);

                int mask =
                    getMask(u) ^
                    getMask(v) ^
                    (1 << (s[l] - 'a'));

                ans.push_back((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }
};
