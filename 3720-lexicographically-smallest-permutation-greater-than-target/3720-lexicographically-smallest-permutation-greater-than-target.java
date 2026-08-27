class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder ans = new StringBuilder();
        int n = s.length();

        // Match target prefix as much as possible
        for (int i = 0; i < n; i++) {
            int ch = target.charAt(i) - 'a';

            if (freq[ch] > 0) {
                freq[ch]--;
                ans.append(target.charAt(i));
            } else {
                break;
            }
        }

        // Backtrack and find smallest greater character
        while (true) {
            int i = ans.length();

            if (i < n) {
                int targetChar = target.charAt(i) - 'a';

                // Smallest available character > target[i]
                for (int c = targetChar + 1; c < 26; c++) {
                    if (freq[c] > 0) {
                        freq[c]--;

                        StringBuilder result = new StringBuilder(ans);
                        result.append((char) ('a' + c));

                        // Add remaining characters in sorted order
                        for (int j = 0; j < 26; j++) {
                            while (freq[j] > 0) {
                                result.append((char) ('a' + j));
                                freq[j]--;
                            }
                        }

                        return result.toString();
                    }
                }
            }

            // No answer possible at this position
            // Backtrack
            if (ans.length() == 0) {
                return "";
            }

            char last = ans.charAt(ans.length() - 1);
            ans.deleteCharAt(ans.length() - 1);
            freq[last - 'a']++;
        }
    }
}