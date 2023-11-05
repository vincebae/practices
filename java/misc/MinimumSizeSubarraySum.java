public class MinimumSizeSubarraySum {
  
  public static int minSubArrayLen(int target, int[] arr) {
    int minSize = Integer.MAX_VALUE;
    int front = 0;
    int back = 0;
    int currentSum = 0;
    while (front < arr.length) {
      currentSum += arr[front++];
      if (currentSum >= target) {
        // Move back until sum become less than target.
        while (currentSum >= target) {
          currentSum -= arr[back++];
        }
        minSize = Math.min(minSize, front - back + 1);
      }
    }
    return minSize == Integer.MAX_VALUE ? 0 : minSize;
  }

  private static void test(int[] arr, int target, int expected) {
    int actual = minSubArrayLen(target, arr);
    if (actual == expected) {
      System.out.println("Success.");
    } else {
      System.out.printf("Failure. actual = %d, expected = %d\n", actual, expected);
    }
  }

  public static void main(String[] args) {
    int[] arr = {2, 3, 1, 2, 4, 3};
    test(arr, 7, 2);
  }
}
