class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // Store only rows that have reserved seats
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Only seats 2 to 9 can affect the answer
            if (col >= 2 && col <= 9) {
                int mask = map.getOrDefault(row, 0);

                // Convert seat number to bit position
                mask |= (1 << (col - 2));

                map.put(row, mask);
            }
        }

        int answer = (n - map.size()) * 2;

        // Masks for the three possible blocks
        int left  = 0b00001111;  // seats 2,3,4,5
        int right = 0b11110000;  // seats 6,7,8,9
        int middle = 0b00111100; // seats 4,5,6,7

        for (int mask : map.values()) {

            boolean canLeft = (mask & left) == 0;
            boolean canRight = (mask & right) == 0;
            boolean canMiddle = (mask & middle) == 0;

            if (canLeft && canRight) {
                // Two groups can fit
                answer += 2;
            } else if (canLeft || canRight || canMiddle) {
                // At least one group can fit
                answer += 1;
            }
        }

        return answer;
    }
}