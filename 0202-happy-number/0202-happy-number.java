import java.util.HashSet;

class Solution {

    private int nextNum(int n) {
        int sum = 0;

        while (n > 0) {
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }

        return sum;
    }

    public boolean isHappy(int n) {
        HashSet<Integer> seen = new HashSet<>();

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = nextNum(n);
        }

        return n == 1;
    }
}