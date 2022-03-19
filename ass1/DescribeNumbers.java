// 316443902 Nir Koren


/**
 * This class outputs the min, max and avg of a given arr through the args argument.
 */
public class DescribeNumbers {
    /**
     * The function gets strings arr and convert them to list of integers than returns it.
     * @param numbers strings arr
     * @return out the converted arr to integers
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] out = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            out[i] = Integer.parseInt(numbers[i]);
        }
        return out;
    }
    /**
     * The function gets int arr and returns the minimum of the arr.
     * @param numbers int arr
     * @return min the minimum of the given arr
     */
    public static int min(int[] numbers) {
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }
    /**
     * The function gets int arr and returns the maximum of the arr.
     * @param numbers int arr
     * @return max the maximum of the given arr
     */
    public static int max(int[] numbers) {
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }
    /**
     * The function gets int arr and returns the average of the arr.
     * @param numbers int arr
     * @return the of average the given arr
     */
    public static float avg(int[] numbers) {
        float sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return (sum / numbers.length);
    }
    /**
     * This main function outputs the min, max and avg of a given arr through the args argument.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        int[] numbers = stringsToInts(args);
        System.out.println("min: " + min(numbers));
        System.out.println("max: " + max(numbers));
        System.out.println("avg: " + avg(numbers));
    }
}
