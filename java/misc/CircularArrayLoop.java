import java.util.Arrays;

public class CircularArrayLoop {

  private static int getNextIndex(int[] nums, int start) {
    int next = (nums[start] + start) % nums.length;
    return next < 0 ? next +  nums.length : next;
  }

  public static boolean circularArrayLoop(int[] nums, int start) {
    int sign = Integer.signum(nums[start]);
    int len = nums.length;
    int fast = start;
    int slow = start;
    while (true) {
      if (Integer.signum(nums[fast]) != sign) {
        return false;
      }
      fast = getNextIndex(nums, fast);
      if (Integer.signum(nums[fast]) != sign) {
        return false;
      }
      fast = getNextIndex(nums, fast);
      slow = getNextIndex(nums, slow);
      if (fast == slow) {
        return true;
      }
    }
    // Not reachable.
  }

  private static void test(int[] arr, int start, boolean expected) {
    boolean actual = circularArrayLoop(arr, start);
    System.out.println(actual == expected ? "Success" : "Failure");
  }
  
  public static void main(String[] args) {
    int[] arr = { 1, 3, -2, -4, 1 };
    test(arr, 0, true);
    arr = new int[] {2, 1, -1, -2, 2 };
    test(arr, 0, false);
    test(arr, 4, false);
  }
}
