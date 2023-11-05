import java.util.HashMap;

public class MinimumWindowSubstring {

  private static void incCount(HashMap<Character, Integer> counter, char ch) {
    counter.put(ch, counter.getOrDefault(ch, 0) + 1);
  }

  private static void decCount(HashMap<Character, Integer> counter, char ch) {
    counter.put(ch, Math.max(0, counter.getOrDefault(ch, 0) - 1));
  }

  public static String minWindow(String str, String target) {
    HashMap<Character, Integer> targetMap = new HashMap<>();
    HashMap<Character, Integer> window = new HashMap<>();

    // build targetMap.
    target.chars().forEach(ch -> incCount(targetMap, (char) ch));
    int required = targetMap.keySet().size();
    int fulfilled = 0;

    // Move front until found all characters in target with enough counts.
    String found = "";
    int front = 0;
    int back = 0;
    while (front < str.length()) {
      while (front < str.length()) {
        char ch = str.charAt(front++);
        if (targetMap.containsKey(ch)) {
          incCount(window, ch);
          if (targetMap.get(ch) == window.get(ch)) {
            fulfilled++;
            if (fulfilled == required) {
              break;
            }
          }
        }
      }

      if (front > str.length()) {
        break;
      }

      // Move back until fulfilled become less than required.
      while (true) {
        char ch = str.charAt(back++);
        if (targetMap.containsKey(ch)) {
          decCount(window, ch);
          if (targetMap.get(ch) > window.get(ch)) {
            fulfilled--;
            break;
          }
        }
      }

      if (found.isEmpty() || (front - back + 1) < found.length()) {
        found = str.substring(back - 1, front);
      }
    }
    return found;
  }

  private static void test(String str, String target, String expected) {
    String actual = minWindow(str, target);
    if (actual.equals(expected)) {
      System.out.println("Success");
    } else {
      System.out.printf("Failure. actual: %s, expected: %s\n", actual, expected);
    }
  }

  public static void main(String[] args) {
    test("cabwefgewcwaefgcf", "cae", "cwae");
  }
}
