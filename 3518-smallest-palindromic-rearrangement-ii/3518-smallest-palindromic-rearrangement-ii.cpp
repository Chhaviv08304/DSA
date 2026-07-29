class Solution {
public:
    const long long LIMIT = 1000000;

    // Count number of distinct permutations of multiset (capped at LIMIT)
    long long ways(vector<int>& cnt) {
        int total = 0;
        for (int x : cnt) total += x;

        long double ans = 1.0;
        int rem = total;

        for (int c : cnt) {
            if (c == 0) continue;
            for (int i = 1; i <= c; i++) {
                ans *= (rem - c + i);
                ans /= i;
                if (ans > LIMIT) return LIMIT;
            }
            rem -= c;
        }
        return min((long long)(ans + 0.5), LIMIT);
    }

    string smallestPalindrome(string s, int k) {
        vector<int> freq(26, 0);
        for (char ch : s) freq[ch - 'a']++;

        vector<int> half(26, 0);
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            if (freq[i] & 1) mid = char('a' + i);
        }

        if (ways(half) < k) return "";

        string left = "";
        int len = s.size() / 2;

        for (int pos = 0; pos < len; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;
                long long cnt = ways(half);

                if (cnt >= k) {
                    left.push_back(char('a' + c));
                    break;
                } else {
                    k -= cnt;
                    half[c]++;
                }
            }
        }

        string right = left;
        reverse(right.begin(), right.end());

        if (mid)
            return left + string(1, mid) + right;
        return left + right;
    }
};