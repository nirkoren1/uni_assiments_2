// 316443902 Nir Koren


/**
 * This class is calculating the factorial of a given integer in two ways: iterative and recursive.
 */
public class Factorial {
    /**
     * The function input is an integer and outputs the factorial using iterative algorithm.
     * @param n integer
     * @return out the factorial of n
     */
    public static long factorialIter(long n) {
        long out = 1;
        for (int i = 1; i <= n; i++) {
            out *= i;
        }
        return out;
    }
    /**
     * The function input is an integer and outputs the factorial using recursive algorithm.
     * @param n integer
     * @return out the factorial of n
     */
    public static long factorialRecursive(long n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorialRecursive(n - 1);
        }
    }
    /**
     * This main function outputs the factorial of a giving argument using to algorithms: iterative and recursive.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.println("recursive: " + factorialRecursive(n));
        System.out.println("iterative: " + factorialIter(n));
    }
}