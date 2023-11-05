import java.util.HashSet;

public class HappyNumber {

  public static boolean isHappyNumber(int num) {
    HashSet<Integer> visited = new HashSet<>();

    int current = num;
    while (current != 1) {
      int newValue = 0;
      while (current > 0) {
        newValue += (current % 10) * (current % 10);
        current /= 10;
      }

      if (visited.contains(newValue)) {
        return false;
      }
      visited.add(newValue);
      current = newValue;
    }
    return true;
  }

  private static void test(int num, boolean expected) {
    boolean actual = isHappyNumber(num);
    System.out.println((actual == expected) ? "Success" : "Failure");
  }

  public static void main(String[] args) {
    test(23, true);
    test(2, false);
  }
}
