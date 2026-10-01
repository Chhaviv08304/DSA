class Solution {

    private int nextIndex(int index, int[] nums) {
        int n = nums.length;
        return ((index + nums[index]) % n + n) % n;
    }

    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {

            if (nums[i] == 0) {
                continue;
            }

            boolean forward = nums[i] > 0;

            int slow = i;
            int fast = i;

            while (true) {
                int nextSlow = nextIndex(slow, nums);

                if (nums[nextSlow] == 0 ||
                    (nums[nextSlow] > 0) != forward) {
                    break;
                }

                int nextFast = nextIndex(fast, nums);

                if (nums[nextFast] == 0 ||
                    (nums[nextFast] > 0) != forward) {
                    break;
                }

                int nextFast2 = nextIndex(nextFast, nums);

                if (nums[nextFast2] == 0 ||
                    (nums[nextFast2] > 0) != forward) {
                    break;
                }

                slow = nextSlow;
                fast = nextFast2;

                if (slow == fast) {
                    if (slow == nextIndex(slow, nums)) {
                        break;
                    }
                    return true;
                }
            }

            int current = i;

            while (nums[current] != 0 &&
                   (nums[current] > 0) == forward) {

                int next = nextIndex(current, nums);
                nums[current] = 0;
                current = next;
            }
        }

        return false;
    }
}