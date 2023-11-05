import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.Comparator;

public class CoinChange {

  public static int countChangeRecur(int denoms[], int amount) {
    if (denoms.length <= 1) {
      return 1;
    }

    int maxDenomCount = amount / denoms[0];
    int[] newDenoms = Arrays.stream(denoms).skip(1).toArray();
    int count = 0;
    for (int i = 0; i <= maxDenomCount; i++) {
      count += countChangeRecur(newDenoms, amount - denoms[0] * i);
    }
    return count;

  }

  public static int countChange(int denoms[], int amount) {
    int[] sorted = Arrays.stream(denoms)
        .filter(n -> n <= amount)
        .boxed()
        .sorted(Comparator.reverseOrder())
        .mapToInt(Integer::valueOf)
        .toArray();

    return countChangeRecur(sorted, amount);
  }

  public static void test(int denoms[], int amount, int expected) {
    int result = countChange(denoms, amount);
    System.out.println("Result: " + result);
    if (result == expected) {
      System.out.println("Success");
    } else {
      System.out.println("Failure");
    }
  }

  public static void main(String[] args) {
    int[] denoms = { 25, 10, 5, 1 };
    test(denoms, 10, 4);
    test(denoms, 15, 6);
    test(denoms, 20, 9);
  }
}
