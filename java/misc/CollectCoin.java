import java.util.Arrays;
import java.util.stream.IntStream;

public class CollectCoin {

  // start, end: Inclusive
  private static int minimumStepsHelper(int[] height, int start, int end) {
    int numStacks = end - start + 1;
    if (numStacks <= 0) {
      return 0;
    }
    if (numStacks == 1) {
      return height[start] == 0 ? 0 : 1;
    }

    int max = IntStream.range(start, end + 1).map(i -> height[i]).max().getAsInt();
    if (max == 0) {
      return 0;
    }

    int min = IntStream.range(start, end + 1).map(i -> height[i]).min().getAsInt();
    int minStacks = (int) IntStream.range(start, end + 1).filter(i -> height[i] == min).count();

    // horizontal first = min + (numStacks - minStacks)
    // vertical only = numStacks
    if (min > 0 && min >= minStacks) {
      return numStacks;
    }

    // Remove horizontal lines for min times.
    for (int i = start; i <= end; i++) {
      height[i] -= min;
    }

    // Recursively count each separate stacks.
    int count = min;
    int pos1 = start;
    while (pos1 <= end) {
      while (pos1 <= end && height[pos1] == 0) {
        pos1++;
      }
      if (pos1 > end) {
        break;
      }
      int pos2 = pos1 + 1;
      while (pos2 <= end && height[pos2] > 0) {
        pos2++;
      }
      count += minimumStepsHelper(height, pos1, pos2 - 1);
      pos1 = pos2 + 1;
    }
    return count;
  }

  public static int minimumSteps(int[] height, int N) {
    return minimumStepsHelper(height, 0, height.length - 1);
  }

  private static void test(int[] height, int expected) {
    int total = Arrays.stream(height).sum();
    int actual = minimumSteps(height, total);
    if (actual == expected) {
      System.out.println("Success.");
    } else {
      System.out.printf("Failure: actual = %d, expected = %d\n", actual, expected);
    }
  }

  public static void main(String[] args) {
    int[] height = {3, 1, 1, 5, 1};
    test(height, 3);

    height = IntStream.of(2, 5, 1, 2, 3, 1).toArray();
    test(height, 5);

    height = IntStream.of(2, 5, 0, 0, 3, 1).toArray();
    test(height, 4);
  }
}
