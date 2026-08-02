class Solution:
    def numDistinct(self, s: str, t: str) -> int:
        n = len(t)
        # dp[j] stores the number of subsequences for t[0...j-1]
        dp = [1] + [0] * n
        
        for char_s in s:
            # Iterate backwards to use values from the previous iteration
            for j in range(n, 0, -1):
                if char_s == t[j - 1]:
                    dp[j] += dp[j - 1]
                    
        return dp[n]

        