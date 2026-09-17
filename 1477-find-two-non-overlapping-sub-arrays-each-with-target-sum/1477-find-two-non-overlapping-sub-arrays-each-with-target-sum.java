class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
        int n = arr.length;

        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left++];
            }

            best[right] = right > 0 ? best[right - 1] : Integer.MAX_VALUE;

            if (sum == target) {
                int len = right - left + 1;

                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, len + best[left - 1]);
                }

                best[right] = Math.min(best[right], len);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}