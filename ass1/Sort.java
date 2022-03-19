// 316443902 nir koren


/**
 * this class outputs the sorted arr in ascending or descending order, of a given arr through the args argument.
 */
public class Sort {
    /**
     * The function gets strings arr and convert them to list of integers than returns a slice of it.
     * @param numbers strings arr
     * @param startIndx the start index from where we start to convert
     * @param endIndx the end index where we stop convert
     * @return out the converted arr to integers
     */
    public static int[] stringsToIntsSlice(String[] numbers, int startIndx, int endIndx) {
        int[] out = new int[endIndx - startIndx + 1];
        for (int i = 0; i < endIndx - startIndx + 1; i++) {
            out[i] = Integer.parseInt(numbers[i + startIndx]);
        }
        return out;
    }
    /**
     * this main function outputs the sorted arr in ascending or descending order, of a given arr through the args
     * argument.
     * The algorithm used is Bubble Sort.
     * @param args string arr given from the cmd
     */
    public static void main(String[] args) {
        int[] arr = stringsToIntsSlice(args, 1, args.length - 1);
        for (int end = arr.length - 1; end > 0; end--) {
            for (int j = 0; j < end; j++) {
                if (arr[j] > arr[j + 1] && args[0].equals("asc")) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }
                if (arr[j] < arr[j + 1] && args[0].equals("desc")) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                System.out.print(arr[i] + " ");
                continue;
            }
            System.out.print(arr[i]);
        }
    }
}
