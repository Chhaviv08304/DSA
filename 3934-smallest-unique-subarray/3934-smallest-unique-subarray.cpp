
class Solution {
public:
    int smallestUniqueSubarray(vector<int>& nums) {
        int n = nums.size();

        // Add a unique smallest sentinel
        vector<int> s = nums;
        s.push_back(0);

        int N = n + 1;
        vector<int> p(N), c(N);

        // Initial sorting
        int mx = max(N, 100001);
        vector<int> cnt(mx, 0);

        for (int x : s) cnt[x]++;

        for (int i = 1; i < mx; i++)
            cnt[i] += cnt[i - 1];

        for (int i = N - 1; i >= 0; i--)
            p[--cnt[s[i]]] = i;

        c[p[0]] = 0;
        int classes = 1;

        for (int i = 1; i < N; i++) {
            if (s[p[i]] != s[p[i - 1]])
                classes++;
            c[p[i]] = classes - 1;
        }

        // Build suffix array using doubling
        for (int k = 0; (1 << k) < N; k++) {
            int len = 1 << k;
            vector<int> pn(N);

            for (int i = 0; i < N; i++) {
                pn[i] = p[i] - len;
                if (pn[i] < 0) pn[i] += N;
            }

            fill(cnt.begin(), cnt.begin() + classes, 0);

            for (int x : pn)
                cnt[c[x]]++;

            for (int i = 1; i < classes; i++)
                cnt[i] += cnt[i - 1];

            for (int i = N - 1; i >= 0; i--)
                p[--cnt[c[pn[i]]]] = pn[i];

            vector<int> cn(N);
            cn[p[0]] = 0;
            int newClasses = 1;

            for (int i = 1; i < N; i++) {
                pair<int, int> cur = {
                    c[p[i]], c[(p[i] + len) % N]
                };

                pair<int, int> prev = {
                    c[p[i - 1]], c[(p[i - 1] + len) % N]
                };

                if (cur != prev)
                    newClasses++;

                cn[p[i]] = newClasses - 1;
            }

            c.swap(cn);
            classes = newClasses;
        }

        // Remove sentinel suffix
        vector<int> sa;

        for (int x : p) {
            if (x != n)
                sa.push_back(x);
        }

        // Build LCP array using Kasai algorithm
        vector<int> rank(n), lcp(n, 0);

        for (int i = 0; i < n; i++)
            rank[sa[i]] = i;

        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = rank[i];

            if (r == 0) continue;

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) h--;
        }

        // Find minimum unique subarray length
        int ans = n;

        for (int r = 0; r < n; r++) {
            int mxLCP = lcp[r];

            if (r + 1 < n)
                mxLCP = max(mxLCP, lcp[r + 1]);

            int required = mxLCP + 1;
            int remaining = n - sa[r];

            if (required <= remaining)
                ans = min(ans, required);
        }

        return ans;
    }
};
