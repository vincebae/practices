import java.util.Arrays;

public class ShuffleIntegers {

  public static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  // start: inclusive
  // end: exclusive
  private static void ShuffleArray(int[] arr, int start, int end) {
      int len = end - start;
      if (len <= 2) {
          return;
      }

      // First phase: move the first half of the latter half to the correct position.
      int latterHalfStart = start + len / 2;
      int pos1 = start + 1;
      int pos2 = latterHalfStart;
      while (pos1 < latterHalfStart) {
          swap(arr, pos1, pos2);
          pos1 += 2;
          pos2 += 1;
      }

      // Second phase: move the first half of the first half back to the correct position.
      int restorePos = start + 2;
      while (restorePos < latterHalfStart) {
        pos1 = restorePos;
        pos2 = latterHalfStart;
        while (pos1 < latterHalfStart) {
            swap(arr, pos1, pos2);
            pos1 += 2;
            pos2 += 1;
        }
        restorePos += 2;
      }

      // Third phase: shuffle latter half.
      ShuffleArray(arr, latterHalfStart, end);
  }

  private static void ShuffleArray(int[] arr) {
      ShuffleArray(arr, 0, arr.length);
  }

  private static void test(int[] arr, int[] expected) {
    ShuffleArray(arr);
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != expected[i]) {
        System.out.println("Failure.");
        Arrays.stream(arr).forEach(System.out::println);
        return;
      }
    }
    System.out.println("Success.");
  }

  public static void main(String[] args) {
    int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
    int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
    test(arr, expected);
  }
}
