public class ModifiedPalindrome {

  private static boolean isPalindrome(String str, int start, int end) {
    int head = start;
    int tail = end;
    while (head <= tail) {
      if (str.charAt(head++) != str.charAt(tail--)) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean isPalindrome(String str) {
    int head = 0;
    int tail = str.length() - 1;
    while (head <= tail) {
      if (str.charAt(head) != str.charAt(tail)) {
        return isPalindrome(str, head + 1, tail)
          || isPalindrome(str, head, tail - 1);
      }
      head++;
      tail--;
    }
    return true;
  }

  private static void test(String str, boolean expected) {
    boolean actual = isPalindrome(str);
    if (actual == expected) {
      System.out.println("Success.");
    } else {
      System.out.println("Failure.");
    }
  }

  public static void main(String[] args) {
    test("ABCBA", true);
    test("ABCEBA", true);
    test("RACEACAT", false);
    test("DEEAD", true);
  }
}
