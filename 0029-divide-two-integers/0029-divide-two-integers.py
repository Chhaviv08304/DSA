class Solution:
    def divide(self, a: int, b: int) -> int:
        s = -1 if (a < 0) ^ (b < 0) else 1
        a, b, ans = abs(a), abs(b), 0
        for i in range(31, -1, -1):
            if a >= b << i:
                a -= b << i
                ans += 1 << i
        ans = ans if s > 0 else -ans
        return min(max(ans, -2**31), 2**31 - 1)
        