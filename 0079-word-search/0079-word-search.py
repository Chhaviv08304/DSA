class Solution:
    def exist(self, board: list[list[str]], word: str) -> bool:
        ROWS, COLS = len(board), len(board[0])
        
        def dfs(r, c, index):
            # Base Case: found the whole word
            if index == len(word):
                return True
                
            # Check boundaries, character mismatches, or already visited cells
            if (r < 0 or c < 0 or 
                r >= ROWS or c >= COLS or 
                board[r][c] != word[index]):
                return False
            
            # Step 1: Mark the cell as visited
            temp = board[r][c]
            board[r][c] = "#"
            
            # Step 2: Explore all 4 neighbors (Up, Down, Left, Right)
            found = (dfs(r + 1, c, index + 1) or
                     dfs(r - 1, c, index + 1) or
                     dfs(r, c + 1, index + 1) or
                     dfs(r, c - 1, index + 1))
            
            # Step 3: Backtrack - restore the cell's original character
            board[r][c] = temp
            
            return found

        # Scan the entire grid for the starting letter
        for r in range(ROWS):
            for c in range(COLS):
                if board[r][c] == word[0]:
                    if dfs(r, c, 0):
                        return True
                        
        return False
