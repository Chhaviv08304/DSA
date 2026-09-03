class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;

        // Find minimum element
        for (int num : nums1) {
            min = Math.min(min, num);
        }

        // If minimum is odd, answer is always true
        if (min % 2 == 1) {
            return true;
        }

        // If minimum is even, all elements must be even
        for (int num : nums1) {
            if (num % 2 != 0) {
                return false;
            }
        }

        return true;
    }
}