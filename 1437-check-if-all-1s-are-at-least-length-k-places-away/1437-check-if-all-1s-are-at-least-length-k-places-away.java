class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        
        int lastOne = -1;

        for (int i = 0; i < nums.length; i++) {
            
            if (nums[i] == 1) {
                
                // Agar pehle bhi koi 1 mila hai
                if (lastOne != -1) {
                    
                    // Dono 1 ke beech ke elements check karo
                    if (i - lastOne - 1 < k) {
                        return false;
                    }
                }

                // Current 1 ka index store karo
                lastOne = i;
            }
        }

        // Sabhi 1's ke beech distance k ya usse zyada hai
        return true;
    }
}