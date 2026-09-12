class Solution {

    class Node {
        long score;
        List<Integer> indices;

        Node(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    Node[][] dp;
    int[][] arr;
    int n;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        n = intervals.size();

        // 4 columns:
        // 0 = left
        // 1 = right
        // 2 = weight
        // 3 = original index
        arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by left
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        dp = new Node[n + 1][5];

        Node ans = solve(0, 4);

        int[] result = new int[ans.indices.size()];

        for (int i = 0; i < ans.indices.size(); i++) {
            result[i] = ans.indices.get(i);
        }

        Arrays.sort(result);

        return result;
    }

    private Node solve(int i, int k) {

        if (i == n || k == 0) {
            return new Node(0, new ArrayList<>());
        }

        if (dp[i][k] != null) {
            return dp[i][k];
        }

        // 1. Skip current interval
        Node skip = solve(i + 1, k);

        // 2. Take current interval
        int next = findNext(i);

        Node nextNode = solve(next, k - 1);

        List<Integer> takeIndices = new ArrayList<>();

        // Original index
        takeIndices.add(arr[i][3]);

        takeIndices.addAll(nextNode.indices);

        Node take = new Node(
            (long) arr[i][2] + nextNode.score,
            takeIndices
        );

        dp[i][k] = better(take, skip);

        return dp[i][k];
    }

    // First interval where left > current right
    private int findNext(int i) {

        int target = arr[i][1];

        int low = i + 1;
        int high = n;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr[mid][0] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private Node better(Node a, Node b) {

        // Higher score is better
        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        // Same score → lexicographically smaller indices
        List<Integer> x = new ArrayList<>(a.indices);
        List<Integer> y = new ArrayList<>(b.indices);

        Collections.sort(x);
        Collections.sort(y);

        for (int i = 0; i < Math.min(x.size(), y.size()); i++) {

            if (!x.get(i).equals(y.get(i))) {
                return x.get(i) < y.get(i) ? a : b;
            }
        }

        // If one is prefix of other
        return x.size() <= y.size() ? a : b;
    }
}