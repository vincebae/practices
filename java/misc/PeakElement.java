public class PeakElement {

    // start, end: inclusive.
    private static int getPeakElementRecur(int[] array, int start, int end) {
        int middle = (end + start) / 2;
        if (array[middle] >= array[middle - 1] && array[middle] >= array[middle + 1]) {
            return array[middle];
        }
        if (array[middle - 1] > array[middle]) {
            return getPeakElementRecur(array, start, middle - 1);
        }
        if (array[middle + 1] > array[middle]) {
            return getPeakElementRecur(array, middle + 1, end);
        }
        // Should not reach here.
        return -1;
    }

    private static int getPeakElement(int[] array) {
        // Corner cases.
        int len = array.length;
        if (len == 0) {
            throw new IllegalArgumentException("No data.");
        }
        if (len == 1) {
            return array[0];
        }
        if (array[0] >= array[1]) {
            return array[0];
        }
        if (array[len - 2] <= array[len - 1]) {
            return array[len - 1];
        }
        return getPeakElementRecur(array, 1, len - 2);
    }

    public static void main(String[] args) {
        int[][] inputs = {
            {7, 11, 22, 13, 4, 0},
            {13, 27, 54, 11, 99, 1},
            {0, 1, 2, 3, 4, 5},
            {10, 9, 8, 7, 6}
        };
        for (int i = 0; i < inputs.length; i++) {
          System.out.println(getPeakElement(inputs[i]));
        }
    }
}
