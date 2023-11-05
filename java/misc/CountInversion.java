import java.util.Arrays;
import java.util.stream.IntStream;

/** CountInversion */
public class CountInversion {

  private static void printArray(int[] arr) {
    Arrays.stream(arr).forEach(n -> System.out.print(String.format("%d, ", n)));
    System.out.println("");
  }

  private static void printArray(int[] arr, int start, int end) {
    IntStream.range(start, end + 1).forEach(i -> System.out.print(String.format("%d, ", arr[i])));
    System.out.println("");
  }

  private static void swap(int[] arr, int pos1, int pos2) {
    int temp = arr[pos1];
    arr[pos1] = arr[pos2];
    arr[pos2] = temp;
  }

  // start, end = inclusive.
  public static int countInversion(int[] arr, int start, int end) {
    int len = end - start + 1;
    if (len <= 1) {
      // Nothing to do. just return 0.
      return 0;
    }
    if (len == 2) {
      if (arr[start] > arr[end]) {
        swap(arr, start, end);
        return 1;
      } else {
        return 0;
      }
    }

    int midIndex = (end + start) / 2;
    int count = countInversion(arr, start, midIndex - 1) + countInversion(arr, midIndex, end);
    // arr [start, mid - 1] and [mid + 1, end] are already arr.
    // Merge them and count the inversion between front half and back half.
    int[] front = IntStream.range(start, midIndex).map(i -> arr[i]).toArray();
    int[] back = IntStream.range(midIndex, end + 1).map(i -> arr[i]).toArray();
    int frontPos = 0;
    int backPos = 0;
    while (frontPos < front.length && backPos < back.length) {
      if (front[frontPos] < back[backPos]) {
        arr[frontPos + backPos + start] = front[frontPos];
        frontPos++;
      } else {
        arr[frontPos + backPos + start] = back[backPos];
        backPos++;
        count += (front.length - frontPos);
      }
    }
    for (int i = frontPos; i < front.length; i++) {
      arr[i + backPos + start] = front[i];
    }
    for (int i = backPos; i < back.length; i++) {
      arr[i + frontPos + start] = back[i];
    }
    return count;
  }

  public static int countInversion(int[] arr) {
    printArray(arr);
    int[] sorted = Arrays.stream(arr).toArray();
    int result = countInversion(sorted, 0, sorted.length - 1);
    return result;
  }

  private static void test(int[] arr, int expected) {
    int actual = countInversion(arr);
    if (actual == expected) {
      System.out.println("Success");
    } else {
      System.out.printf("Failure: actual = %d, expected = %d\n", actual, expected);
    }
  }

  public static void main(String[] args) {
    int[] array = {9, 5, 6, 11, 8, 10};
    test(array, 5);
    array = IntStream.of(10, 9, 8, 7, 6).toArray();
    test(array, 10);
    array = IntStream.of(3, 1, 2, 4, 5).toArray();
    test(array, 2);
  }
}
