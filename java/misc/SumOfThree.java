import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class SumOfThree {

  public static boolean findSumOfThree(int[] nums, int target) {
    int min = Arrays.stream(nums).min().getAsInt();
    int[] sorted = Arrays.stream(nums).map(n -> n - min).sorted().toArray();
    HashSet<Integer> numSet =
        Arrays.stream(sorted).boxed().collect(Collectors.toCollection(HashSet::new));

    int newTarget = target - min * 3;

    int head = 0;
    int tail = nums.length - 1;
    while (head < tail) {
      int num1 = sorted[head];
      int num2 = sorted[tail];
      int twoSum = num1 + num2;
      if (twoSum == newTarget) {
        if (num1 != 0 && num2 != 0) {
          return true;
        }
        tail--;
      } else if (twoSum < newTarget) {
        int missing = newTarget - twoSum;
        if (missing != num1 && missing != num2 && numSet.contains(missing)) {
          return true;
        }
        head++;
      } else {
        tail--;
      }
    }
    return false;
  }

  private static void test(int[] arr, int target, boolean expected) {
    boolean actual = findSumOfThree(arr, target);
    if (actual == expected) {
      System.out.println("Success");
    } else {
      System.out.println("Failure");
    }
  }

  public static void main(String[] args) {
    int[] arr = {3, 7, 1, 2, 8, 4, 5};
    test(arr, 20, true);
  }
}
