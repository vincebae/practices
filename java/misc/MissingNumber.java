import java.util.stream.IntStream;

public class MissingNumber {

    // start, end: inclusive.
    private static int findMissingNumberRecur(int[] arr, int start, int end) {
        if (start == end) {
            int expected = arr[0] + start;
            if (arr[start] == expected) {
                return -1;
            } else {
                return arr[start] - 1;
            }

        }

        int middle = (start + end) / 2;
        int expected = arr[0] + middle;
        if (arr[middle] == expected) {
            // Missing number is after the middle.
            return findMissingNumberRecur(arr, middle + 1, end);
        } else if (arr[middle] != arr[middle - 1] + 1) {
            // Missing number is right before middle.
            return arr[middle] - 1;
        } else {
            // Missing number if before middle.
            return findMissingNumberRecur(arr, start, middle - 1);
        }
    }


    private static int findMissingNumber(int[] arr, int size) {
        return findMissingNumberRecur(arr, 0, size - 1);
    }

    private static void test(int[] input, int expected) {
        int result = findMissingNumber(input, input.length);
        if (result == expected) {
            System.out.println("Success");
        } else {
            System.out.println(String.format("Failure. actual: %d, expected: %d", result, expected));
        }
    }

    public static void main(String[] args) {
        int[] input = {3, 4, 5, 7, 8, 9, 10};
        test(input, 6);

        input = IntStream.of(1, 2, 3, 4, 5, 6).toArray();
        test(input, -1);
    }
}
