import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RepeatedDnaSequences {

  private static final Map<Character, Long> CHAR_MAP = createCharMap();
  private static final Map<Long, Character> REVERSE_MAP = createReverseMap();

  private static Map<Character, Long> createCharMap() {
    HashMap<Character, Long> map = new HashMap<>();
    map.put('A', 1L);
    map.put('C', 2L);
    map.put('G', 3L);
    map.put('T', 4L);
    return map;
  }

  private static Map<Long, Character> createReverseMap() {
    HashMap<Long, Character> map = new HashMap<>();
    map.put(1L, 'A');
    map.put(2L, 'C');
    map.put(3L, 'G');
    map.put(4L, 'T');
    return map;
  }

  // start: inclusive, end: exclusive.
  private static long getSequence(String str, int start, int end) {
    return IntStream.range(start, end)
        .mapToLong(i -> CHAR_MAP.get(str.charAt(i)))
        .reduce((a, b) -> a * 10 + b)
        .orElse(0L);
  }

  private static void increaseCount(Map<Long, Integer> counter, long key) {
    counter.put(key, counter.getOrDefault(key, 0) + 1);
  }

  private static String sequenceString(long sequence) {
    StringBuilder builder = new StringBuilder();
    while (sequence > 0) {
      builder.append(REVERSE_MAP.get(sequence % 10L));
      sequence /= 10L;
    }
    return builder.reverse().toString();
  }

  public static List<String> findRepeatedSequences(String str, int k) {
    if (str.length() < k) {
      return new ArrayList<>();
    }

    HashMap<Long, Integer> counter = new HashMap<>();
    long sequence = getSequence(str, 0, k);
    increaseCount(counter, sequence);
    for (int i = k; i < str.length(); i++) {
      char toBeRemoved = str.charAt(i - k);
      char toBeAppended = str.charAt(i);
      sequence -= Math.pow(10L, k - 1) * CHAR_MAP.get(toBeRemoved);
      sequence = sequence * 10 + CHAR_MAP.get(toBeAppended);
      increaseCount(counter, sequence);
    }

    counter.entrySet().stream()
        .forEach(
            entry -> System.out.printf("Key: %d, count: %d\n", entry.getKey(), entry.getValue()));

    return counter.entrySet().stream()
        .filter(entry -> entry.getValue() > 1)
        .map(entry -> sequenceString(entry.getKey()))
        .toList();
  }

  private static void test(String input, int length, List<String> expected) {
    List<String> actual = findRepeatedSequences(input, length);
    if (actual.containsAll(expected) && expected.containsAll(actual)) {
      System.out.println("Success");
    } else {
      System.out.println("Failure");
      actual.stream().forEach(s -> System.out.println(s + ", "));
      System.out.println("\n");
    }
  }

  public static void main(String[] args) {
    List<String> expected = Stream.of("ATAT", "TATG").toList();
    test("CCATATGTATGGATAT", 4, expected);
  }
}
