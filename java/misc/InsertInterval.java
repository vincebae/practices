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

  private static void addInterval(List<Interval> intervals, Interval newInterval) {
    if (intervals.isEmpty()) {
      intervals.add(newInterval);
    } else {
      Interval lastInResult = intervals.get(intervals.size() - 1);
      if (lastInResult.getEnd() >= newInterval.getStart()) {
        lastInResult.setEnd(Math.max(lastInResult.getEnd(), newInterval.getEnd()));
      } else {
        intervals.add(newInterval);
      }
    }
  }

  public static List<Interval> insertInterval(List<Interval> existing, Interval newInterval) {
    List<Interval> result = new ArrayList<>();

    // Advance until newInterval.start <= existing.start.
    int curr = 0;
    while (curr < existing.size() && newInterval.getStart() > existing.get(curr).getStart()) {
      result.add(existing.get(curr));
      curr++;
    }

    // Add newInterval.
    addInterval(result, newInterval);

    // Add rest.
    while (curr < existing.size()) {
      addInterval(result, existing.get(curr));
      curr++;
    }
    return result;
  }

  private static void test(List<Interval> existing, Interval newInterval, List<Interval> expected) {
    List<Interval> actual = insertInterval(existing, newInterval);
    System.out.println("Expected: " + expected.toString());
    System.out.println("Actual: " + actual.toString());
  }

  public static void main(String[] args) {
    List<Interval> existing =
        Stream.of(new Interval(1, 2), new Interval(3, 4), new Interval(5, 8), new Interval(9, 15))
            .collect(Collectors.toList());
    Interval newInterval = new Interval(2, 5);
    List<Interval> expected =
        Stream.of(new Interval(1, 8), new Interval(9, 15)).collect(Collectors.toList());
    test(existing, newInterval, expected);

    existing =
        Stream.of(new Interval(1, 2), new Interval(3, 4), new Interval(5, 8), new Interval(9, 15))
            .collect(Collectors.toList());
    newInterval = new Interval(16, 17);
    expected =
        Stream.of(
                new Interval(1, 2),
                new Interval(3, 4),
                new Interval(5, 8),
                new Interval(9, 15),
                new Interval(16, 17))
            .collect(Collectors.toList());
    test(existing, newInterval, expected);
  }
}
