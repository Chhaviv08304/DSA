class Solution {
public:
    vector<vector<string>> solveNQueens(int n) {
        vector<vector<string>> res;
        vector<string> board(n, string(n, '.'));
        
        vector<int> col(n, 0);
        vector<int> diag(2*n, 0);      // row - col + n
        vector<int> antiDiag(2*n, 0);  // row + col
        
        backtrack(0, n, board, col, diag, antiDiag, res);
        return res;
    }
    
    void backtrack(int row, int n, vector<string>& board,
                   vector<int>& col, vector<int>& diag,
                   vector<int>& antiDiag,
                   vector<vector<string>>& res) {
        
        if (row == n) {
            res.push_back(board);
            return;
        }
        
        for (int c = 0; c < n; c++) {
            if (col[c] || diag[row - c + n] || antiDiag[row + c])
                continue;
            
            board[row][c] = 'Q';
            col[c] = diag[row - c + n] = antiDiag[row + c] = 1;
            
            backtrack(row + 1, n, board, col, diag, antiDiag, res);
            
            board[row][c] = '.';
            col[c] = diag[row - c + n] = antiDiag[row + c] = 0;
        }
    }
};