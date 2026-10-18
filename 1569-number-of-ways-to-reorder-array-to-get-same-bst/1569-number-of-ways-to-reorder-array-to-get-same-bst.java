
class Solution {
    static final int MOD = 1_000_000_007;
    long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        comb = new long[n + 1][n + 1];

        // Build Pascal's Triangle
        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;

            for (int j = 1; j < i; j++) {
                comb[i][j] =
                    (comb[i - 1][j - 1] +
                     comb[i - 1][j]) % MOD;
            }
        }

        return (int) ((solve(nums) - 1 + MOD) % MOD);
    }

    private long solve(int[] nums) {
        int n = nums.length;

        if (n <= 2) {
            return 1;
        }

        int root = nums[0];

        int[] leftTemp = new int[n];
        int[] rightTemp = new int[n];

        int leftSize = 0;
        int rightSize = 0;

        // Divide elements into left and right subtrees
        for (int i = 1; i < n; i++) {
            if (nums[i] < root) {
                leftTemp[leftSize++] = nums[i];
            } else {
                rightTemp[rightSize++] = nums[i];
            }
        }

        int[] left = new int[leftSize];
        int[] right = new int[rightSize];

        System.arraycopy(leftTemp, 0, left, 0, leftSize);
        System.arraycopy(rightTemp, 0, right, 0, rightSize);

        long leftWays = solve(left);
        long rightWays = solve(right);

        // Interleave left and right subtree elements
        long ways = comb[n - 1][leftSize];

        ways = ways * leftWays % MOD;
        ways = ways * rightWays % MOD;

        return ways;
    }
}
