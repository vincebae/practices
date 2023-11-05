import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FreeTime {

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

  static class IntervalListIterator {
    private int curr;
    private List<Interval> list;

    public IntervalListIterator(List<Interval> list) {
      this.list = list;
      this.curr = 0;
    }

    public boolean hasNext() {
      return curr < list.size() - 1;
    }

    public void moveNext() {
      if (!hasNext()) {
        throw new IllegalStateException();
      }
      curr++;
    }

    public Interval getCurr() {
      return list.get(curr);
    }

    public int getCurrentStart() {
      return getCurr().getStart();
    }

    public int getCurrentEnd() {
      return getCurr().getEnd();
    }
  }

  private static List<Interval> employeeFreeTime2(List<List<Interval>> intervalsList) {
    ArrayList<Interval> merged = new ArrayList<>();
    PriorityQueue<IntervalListIterator> minHeap =
      intervalsList.stream()
      .filter(list -> !list.isEmpty())
      .map(IntervalListIterator::new)
      .collect(Collectors.toCollection(
            () -> new PriorityQueue<>(Comparator.comparingInt(IntervalListIterator::getCurrentStart))));
    if (minHeap.isEmpty()) {
      return merged;
    }
    // Add first one.
    IntervalListIterator first = minHeap.poll();
    merged.add(first.getCurr());
    if (first.hasNext()) {
      first.moveNext();
      minHeap.add(first);
    }

    // Merge rest intervals.
    while (!minHeap.isEmpty()) {
      IntervalListIterator it = minHeap.poll();
      Interval last = merged.get(merged.size() - 1);
      if (it.getCurrentStart() <= last.getEnd()) {
        last.setEnd(Math.max(last.getEnd(), it.getCurrentEnd()));
      } else {
        merged.add(it.getCurr());
      }
      if (it.hasNext()) {
        it.moveNext();
        minHeap.add(it);
      }
    }

    ArrayList<Interval> result = new ArrayList<>();
    for (int i = 1; i < merged.size(); i++) {
      result.add(new Interval(merged.get(i - 1).getEnd(), merged.get(i).getStart()));
    }
    return result;
  }

  public static List<Interval> employeeFreeTime(List<List<Interval>> intervalsList) {
    List<Interval> allIntervals =
      intervalsList.stream()
      .flatMap(List::stream)
      .sorted(Comparator.comparingInt(Interval::getStart))
      .collect(Collectors.toList());
    ArrayList<Interval> result = new ArrayList<>();
    int start = 0;
    int end = 0;
    for (int i = 1; i < allIntervals.size(); i++) {
      end = Math.max(end, allIntervals.get(i - 1).getEnd());
      start = allIntervals.get(i).getStart();
      if (end < start) {
        result.add(new Interval(end, start));
      }
    }
    return result;
  }

  private static void test(List<List<Interval>> intervalsList, List<Interval> expected) {
    List<Interval> actual = employeeFreeTime(intervalsList);
    System.out.println("Expected: " + expected.toString());
    System.out.println("Actual: " + actual.toString());
  }

  public static void main(String[] args) {
    ArrayList<List<Interval>> intervalsList = new ArrayList<>();
    intervalsList.add(
        Stream.of(new Interval(1, 2), new Interval(5, 6)).collect(Collectors.toList()));
    intervalsList.add(Stream.of(new Interval(1, 3)).collect(Collectors.toList()));
    intervalsList.add(Stream.of(new Interval(4, 10)).collect(Collectors.toList()));
    List<Interval> expected = Stream.of(new Interval(3, 4)).collect(Collectors.toList());
    test(intervalsList, expected);
  }
}
