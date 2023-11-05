public class ClosestNumber {

    private int id;
    private String name;

    private static int closestNumber(int[] arr, int target) {
        if (arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0];
        }

        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int middle = (start + end) / 2;
            if (arr[middle] == target) {
                return target;
            }
            if (arr[middle] > target) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }

        int pos1 = start;
        int pos2 = (start == arr.length - 1) ? start - 1 : start + 1;
        if (Math.abs(arr[pos1] - target) <= Math.abs(arr[pos2] - target)) {
            return arr[pos1];
        } else {
            return arr[pos2];
        }
    }

    private static void test(int[] arr, int target, int expected) {
        int result = closestNumber(arr, target);
        if (result == expected) {
            System.out.println("Success");
        } else {
            System.out.printf("Failure: actual = %d, expected = %d\n", result, expected);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 4, 5, 6, 6, 8, 9 };
        test(arr, 11, 9);
    }
}
