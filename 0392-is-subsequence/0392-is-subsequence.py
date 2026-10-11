class Solution:
    def isSubsequence(self, s: str, t: str) -> bool:
        t_iterator = iter(t)
        return all(c in t_iterator for c in s)