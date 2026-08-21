class Solution:
    def getHint(self, secret: str, guess: str) -> str:
        bulls = 0
        cows = 0
        # Tracks the balance of digits 0-9
        digit_counts = [0] * 10
        
        for s, g in zip(secret, guess):
            if s == g:
                bulls += 1
            else:
                # If secret digit was previously seen in guess, it's a cow
                if digit_counts[int(s)] < 0:
                    cows += 1
                # If guess digit was previously seen in secret, it's a cow
                if digit_counts[int(g)] > 0:
                    cows += 1
                
                # Update frequencies
                digit_counts[int(s)] += 1
                digit_counts[int(g)] -= 1
                
        return f"{bulls}A{cows}B"
