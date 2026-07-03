class Solution {
public:
    string reorganizeString(string s) {
        unordered_map<char, int> freq;

        // Count frequency of each character
        for (char ch : s)
            freq[ch]++;

        int n = s.size();

        // Check if reorganization is possible
        int maxFreq = 0;
        for (auto &it : freq)
            maxFreq = max(maxFreq, it.second);

        if (maxFreq > (n + 1) / 2)
            return "";

        // Max Heap -> {frequency, character}
        priority_queue<pair<int, char>> pq;

        for (auto &it : freq)
            pq.push({it.second, it.first});

        string ans = "";

        while (pq.size() >= 2) {
            auto first = pq.top();
            pq.pop();

            auto second = pq.top();
            pq.pop();

            ans += first.second;
            ans += second.second;

            first.first--;
            second.first--;

            if (first.first > 0)
                pq.push(first);

            if (second.first > 0)
                pq.push(second);
        }

        // Add the last remaining character (if any)
        if (!pq.empty())
            ans += pq.top().second;

        return ans;
    }
};