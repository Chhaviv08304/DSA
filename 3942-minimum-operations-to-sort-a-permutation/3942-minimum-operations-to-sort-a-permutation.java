class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        // Case 1: Sort using rotations only
        int p = check(nums);

        if (p != -1) {
            ans = Math.min(p, p == 0 ? 0 : n - p + 2);
        }

        // Case 2: Reverse, then rotate
        int[] rev = new int[n];

        for (int i = 0; i < n; i++) {
            rev[i] = nums[n - 1 - i];
        }

        p = check(rev);

        if (p != -1) {
            ans = Math.min(ans, 1 + Math.min(p, n - p));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int check(int[] nums) {
        int n = nums.length;
        int pos = 0;

        while (nums[pos] != 0) {
            pos++;
        }

        for (int i = 0; i < n; i++) {
            if (nums[(pos + i) % n] != i) {
                return -1;
            }
        }

        return pos;
    }
}