import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MeetingRoom {

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
  }

  public static int minMeetingRooms(List<Interval> intervalsList) {
    // Sort intervals list by start time.
    intervalsList.sort(Comparator.comparingInt(Interval::getEnd));
    // Hash map to count the currently needed meeting rooms' end time.
    TreeMap<Integer, Integer> roomCounter = new TreeMap<>();
    for (Interval interval : intervalsList) {
      Integer endTime = roomCounter.floorKey(interval.getStart());
      if (endTime != null) {
        roomCounter.put(endTime, roomCounter.get(endTime) - 1);
      }
      roomCounter.put(interval.getEnd(), roomCounter.getOrDefault(interval.getEnd(), 0) + 1);
    }
    return roomCounter.values().stream().mapToInt(Integer::intValue).sum();
  }

  private static void test(List<Interval> intervalsList, int expected) {
    int actual = minMeetingRooms(intervalsList);
    System.out.println("Expected: " + expected);
    System.out.println("Actual: " + actual);
  }

  public static void main(String[] args) {
    List<Interval> intervalsList =
        Stream.of(new Interval(2, 8),
            new Interval(3, 4),
            new Interval(3, 9),
            new Interval(5, 11),
            new Interval(8, 20),
            new Interval(11, 15))
        .collect(Collectors.toList());
    test(intervalsList, 3);
    intervalsList =
      Stream.of(new Interval(1, 7),
          new Interval(2, 6),
          new Interval(3, 7),
          new Interval(4, 8),
          new Interval(5, 8),
          new Interval(2, 9),
          new Interval(1, 8))
        .collect(Collectors.toList());
    test(intervalsList, 7);
  }
}
