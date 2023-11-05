import java.util.stream.IntStream;

public class LargestProfit {
  
  public static int maxProfit(int[] arr) {
    if (arr.length <= 1) {
      return 0;
    }
    int front = 1;
    int back = 0;
    int max = 0;
    while (front < arr.length) {
      while (back < front && front < arr.length && arr[front] <= arr[back]) {
        back++;
        if (front == back) {
          front++;
        }
      }
      while (front < arr.length && arr[front] > arr[back]) {
        max = Math.max(max, arr[front] - arr[back]);
        front++;
      }
    }
    return max;
  }

  private static void test(int[] arr, int expected) {
    int actual = maxProfit(arr);
    if (actual == expected) {
      System.out.println("Success");
    } else {
      System.out.printf("Failure. actual = %d, expected = %d\n", actual, expected);
    }
  }


  public static void main(String[] args) {
    int[] arr = {7, 1, 5, 3, 6, 4};
    test(arr, 5);
    arr = IntStream.of(7, 6, 4, 3, 1).toArray();
    test(arr, 0);
  }
}
