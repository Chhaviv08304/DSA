class Solution {
    public boolean checkIfPangram(String sentence) {
        
        boolean[] seen = new boolean[26];

        // Har character ko check karo
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            int index = ch - 'a';
            seen[index] = true;
        }

        // Check karo kya saare 26 letters mile hain
        for (int i = 0; i < 26; i++) {
            if (seen[i] == false) {
                return false;
            }
        }

        return true;
    }
}