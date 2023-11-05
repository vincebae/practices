import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeIntervals {

  static class Interval {
    int start;
    int end;
    boolean closed;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
      this.closed = true; // by default, the interval is closed
    }

    public int getStart() {
      return start;
    }

    public int getEnd() {
      return end;
    }

    public void setEnd(int end) {
      this.end = end;
    }

    // set the flag for closed/open
    public void setClosed(boolean closed) {
      this.closed = closed;
    }

    public String toString() {
      return String.format("Start: %d, End: %d", start, end);
    }

    public boolean equals(Interval other) {
      return this.start == other.start && this.end == other.end && this.closed == other.closed;
    }
  }

  public static List<Interval> mergeIntervals(List<Interval> input) {
    List<Interval> result = new ArrayList<>();
    int curr = 0;
    while (curr < input.size()) {
      int start = input.get(curr).getStart();
      int end = input.get(curr).getEnd();
      curr++;
      while (curr < input.size()) {
        if (input.get(curr).getStart() <= end) {
          end = Math.max(end, input.get(curr).getEnd());
          curr++;
        } else {
          break;
        }
      }
      result.add(new Interval(start, end));
    }
    return result;
  }

  private static void test(List<Interval> input, List<Interval> expected) {
    List<Interval> actual = mergeIntervals(input);
    if (actual.equals(expected)) {
      System.out.println("Success.");
    } else {
      System.out.println("Failure.");
      System.out.println(actual.toString());
    }
  }

  public static void main(String[] args) {
    List<Interval> input =
        Stream.of(new Interval(1, 5), new Interval(3, 7), new Interval(4, 6))
            .collect(Collectors.toList());
    List<Interval> expected = Stream.of(new Interval(1, 7)).collect(Collectors.toList());
    test(input, expected);
  }
}
