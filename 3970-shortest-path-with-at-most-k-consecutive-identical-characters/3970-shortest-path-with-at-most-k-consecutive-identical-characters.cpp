
class Solution {
public:
    int shortestPath(int n, vector<vector<int>>& edges, string labels, int k) {

        // Build adjacency list
        vector<vector<pair<int, int>>> adj(n);

        for (auto &e : edges) {
            adj[e[0]].push_back({e[1], e[2]});
        }

        // dist[node][count]
        const long long INF = 1e18;

        vector<vector<long long>> dist(n, vector<long long>(k + 1, INF));

        // {cost, node, consecutive count}
        priority_queue<
            tuple<long long, int, int>,
            vector<tuple<long long, int, int>>,
            greater<tuple<long long, int, int>>
        > pq;

        dist[0][1] = 0;
        pq.push({0, 0, 1});

        while (!pq.empty()) {

            auto [cost, node, count] = pq.top();
            pq.pop();

            // Skip outdated states
            if (cost != dist[node][count]) {
                continue;
            }

            // Destination reached
            if (node == n - 1) {
                return (int)cost;
            }

            for (auto [next, weight] : adj[node]) {

                int nextCount;

                if (labels[node] == labels[next]) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                // Check consecutive character limit
                if (nextCount > k) {
                    continue;
                }

                long long newCost = cost + weight;

                if (newCost < dist[next][nextCount]) {

                    dist[next][nextCount] = newCost;

                    pq.push({
                        newCost,
                        next,
                        nextCount
                    });
                }
            }
        }

        return -1;
    }
};
