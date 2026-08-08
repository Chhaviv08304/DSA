class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // suf[j] = earliest index in word1
        // where word2[j...] can be matched exactly
        int[] suf = new int[m];

        for (int i = 0; i < m; i++) {
            suf[i] = -1;
        }

        int p = n - 1;

        for (int j = m - 1; j >= 0; j--) {
            while (p >= 0 && word1.charAt(p) != word2.charAt(j)) {
                p--;
            }

            if (p < 0) {
                break;
            }

            suf[j] = p;
            p--;
        }

        int[] ans = new int[m];

        int j = 0;
        int count = 0;
        boolean usedMismatch = false;

        for (int i = 0; i < n && j < m; i++) {

            // Characters are equal
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[count++] = i;
                j++;
            }

            // Use the one allowed mismatch
            else if (!usedMismatch) {

                // If this is the last character,
                // we can directly use the mismatch.
                if (j == m - 1) {
                    ans[count++] = i;
                    usedMismatch = true;
                    j++;
                }

                // Otherwise, remaining characters must
                // be matched exactly after index i.
                else if (suf[j + 1] != -1 && suf[j + 1] > i) {
                    ans[count++] = i;
                    usedMismatch = true;
                    j++;
                }
            }
        }

        // Could not form a valid sequence
        if (j != m) {
            return new int[0];
        }

        return ans;
    }
}