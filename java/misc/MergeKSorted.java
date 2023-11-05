import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class MergeKSorted {

  private static class Cursor {
    private int[] array;
    private int position;
    private int value;

    Cursor(int[] array) {
      this.array = array;
      this.position = 0;
      this.value = array[position];
    }

    boolean fetchNext() {
      position++;
      if (position >= array.length) {
        return false;
      }
      value = array[position];
      return true;
    }

    int getValue() {
      return value;
    }
  }

  public static void mergeSortedArrays(int[][] arr, int k, int[] result) {
    PriorityQueue<Cursor> priorityQueue =
        Arrays.stream(arr)
            .map(a -> new Cursor(a))
            .collect(
                Collectors.toCollection(
                    () -> new PriorityQueue<>(Comparator.comparingInt(Cursor::getValue))));

    int totalCount = arr.length * arr[0].length;
    int resultPos = 0;
    while (resultPos < totalCount) {
      Cursor cursor = priorityQueue.poll();
      result[resultPos] = cursor.getValue();
      resultPos++;
      if (cursor.fetchNext()) {
        priorityQueue.add(cursor);
      }
    }
  }

  private static void test(int[][] arr, int[] expected) {
    int[] result = new int[arr.length * arr[0].length];
    mergeSortedArrays(arr, arr.length, result);

    for (int i = 0; i < result.length; i++) {
      if (result[i] != expected[i]) {
        System.out.println("Failed");
        Arrays.stream(result).forEach(System.out::println);
        return;
      }
    }
    System.out.println("Success");
  }

  public static void main(String[] args) {
    int[][] arr = {{16, 25, 36}, {2, 9, 15}, {22, 55, 67}, {38, 79, 99}};
    int[] expected = {2, 9, 15, 16, 22, 25, 36, 38, 55, 67, 79, 99};
    test(arr, expected);
  }
}
