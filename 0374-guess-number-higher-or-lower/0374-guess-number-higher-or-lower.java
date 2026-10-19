
/**
 * Forward declaration of guess API.
 * @param num your guess
 * @return -1 if num is higher than the picked number
 *          1 if num is lower than the picked number
 *          0 if correct
 */
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        long left = 1;
        long right = n;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            int result = guess((int) mid);

            if (result == 0) {
                return (int) mid;
            } else if (result == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
