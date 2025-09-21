public class Solution {
    /** calculating n-th fibonacci number.
     * @param n long.
     * @return result.
     */
    public static long fibonacci(long n) {
        if (n < 0) {
            return -1;
        }

        if (n <= 1) {
            return n;
        }

        long fst = 0L;
        long sec = 1L;

        for (long i = 2L; i <= n; ++i) {
            long tmp = sec;

            if (sec >= Long.MAX_VALUE - fst) {
                return Long.MAX_VALUE;
            }

            sec += fst;
            fst = tmp;
        }

        return sec;
    }

    public static void main(String[] args) {
        System.out.println(Solution.fibonacci(1));
    }
}