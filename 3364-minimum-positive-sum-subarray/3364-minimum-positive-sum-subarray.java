
class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();

        int[] prefix = new int[n + 1];

        // Build prefix sum
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        int ans = Integer.MAX_VALUE;

        // Check all subarrays of length l to r
        for (int i = 0; i < n; i++) {
            for (int j = i + l; j <= Math.min(n, i + r); j++) {

                int sum = prefix[j] - prefix[i];

                if (sum > 0) {
                    ans = Math.min(ans, sum);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
