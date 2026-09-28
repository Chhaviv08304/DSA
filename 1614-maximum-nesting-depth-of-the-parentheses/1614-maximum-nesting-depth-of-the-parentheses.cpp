class Solution {
public:
    int maxDepth(string s) {
        int depth = 0;
        int maxDepth = 0;
        for (char val:s){
        if (val=='('){
            depth++;
            maxDepth = max(maxDepth, depth);
        }else if(val==')'){
            depth--;
        }
    }
    return maxDepth;
}
};