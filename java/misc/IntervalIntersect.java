import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntervalIntersect {

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
      return String.format("[%d, %d]", start, end);
    }

    public boolean equals(Interval other) {
      return this.start == other.start && this.end == other.end && this.closed == other.closed;
    }
  }

  private static Optional<Interval> getIntersect(Interval interval1, Interval interval2) {
    int start = Math.max(interval1.getStart(), interval2.getStart());
    int end = Math.min(interval1.getEnd(), interval2.getEnd());
    return (start <= end) ? Optional.of(new Interval(start, end)) : Optional.empty();
  }

  private static List<Interval> intervalsIntersection(
      List<Interval> intervals1, List<Interval> intervals2) {
    ArrayList<Interval> result = new ArrayList<>();
    int pos1 = 0;
    int pos2 = 0;
    while (pos1 < intervals1.size() && pos2 < intervals2.size()) {
      Interval interval1 = intervals1.get(pos1);
      Interval interval2 = intervals2.get(pos2);
      getIntersect(interval1, interval2).ifPresent(result::add);
      if (interval1.getEnd() < interval2.getEnd()) {
        pos1++;
      } else {
        pos2++;
      }
    }
    return result;
  }

  private static void test(
      List<Interval> intervals1, List<Interval> intervals2, List<Interval> expected) {
    List<Interval> actual = intervalsIntersection(intervals1, intervals2);
    System.out.println("Expected: " + expected.toString());
    System.out.println("Actual: " + actual.toString());
  }

  public static void main(String[] args) {
    List<Interval> input1 =
        Stream.of(new Interval(1, 4), new Interval(5, 6), new Interval(7, 9))
            .collect(Collectors.toList());
    List<Interval> input2 =
        Stream.of(new Interval(3, 5), new Interval(6, 7), new Interval(8, 9))
            .collect(Collectors.toList());
    List<Interval> expected =
        Stream.of(
                new Interval(3, 4),
                new Interval(5, 5),
                new Interval(6, 6),
                new Interval(7, 7),
                new Interval(8, 9))
            .collect(Collectors.toList());
    test(input1, input2, expected);
  }
}
