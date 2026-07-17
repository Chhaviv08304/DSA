class Solution {
public:
    int findCheapestPrice(int n, vector<vector<int>>& flights, int src, int dst, int k) {

        vector<pair<int,int>> adj[n];

        for(auto &f : flights){
            adj[f[0]].push_back({f[1], f[2]});
        }

        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;

        // {cost, node, stops}
        pq.push({0, src, 0});

        vector<int> stops(n, INT_MAX);

        while(!pq.empty()){
            auto cur = pq.top();
            pq.pop();

            int cost = cur[0];
            int node = cur[1];
            int step = cur[2];

            if(node == dst)
                return cost;

            if(step > k || step > stops[node])
                continue;

            stops[node] = step;

            for(auto &it : adj[node]){
                int next = it.first;
                int price = it.second;
                pq.push({cost + price, next, step + 1});
            }
        }

        return -1;
    }
};