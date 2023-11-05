import java.util.HashSet;

public class LongestSubstringWithoutRepeating {

  public static int findLongestSubstring(String str) {

    HashSet<Character> charSet = new HashSet<>();
    int longest = 0;
    int front = 0;
    int back = 0;
    while (front < str.length()) {
      char charAtFront = str.charAt(front);
      if (charSet.contains(charAtFront)) {
        longest = Math.max(longest, front - back);
        while (true) {
          char charAtBack = str.charAt(back++);
          if (charAtBack == charAtFront) {
            break;
          } else {
            charSet.remove(charAtBack);
          }
        }
      } else {
        charSet.add(charAtFront);
      }
      front++;
    }
    return Math.max(longest, front - back);
  }

  private static void test(String str, int expected) {
    int actual = findLongestSubstring(str);
    if (actual == expected) {
      System.out.println("Success");
    } else {
      System.out.printf("Failure. actual = %d, expected = %d\n", actual, expected);
    }
  }

  public static void main(String[] args) {
    test("bbbbbb", 1);
    test("pwwkew", 3);
  }
}
