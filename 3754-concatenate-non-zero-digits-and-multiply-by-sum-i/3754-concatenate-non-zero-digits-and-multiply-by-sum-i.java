class Solution {
    public long sumAndMultiply(int n) {
        if (n == 0) return 0;

        StringBuilder sb = new StringBuilder();

        // Store non-zero digits
        for (char ch : String.valueOf(n).toCharArray()) {
            if (ch != '0') {
                sb.append(ch);
            }
        }

        // If all digits were zero
        if (sb.length() == 0) return 0;

        long x = Long.parseLong(sb.toString());

        int sum = 0;
        for (int i = 0; i < sb.length(); i++) {
            sum += sb.charAt(i) - '0';
        }

        return x * sum;
    }
}