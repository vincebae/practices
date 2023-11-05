import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MaximumInSlidingWindow {

  public static List<Integer> findMaxSlidingWindow(List<Integer> nums, int windowSize) {
    PriorityQueue<Integer> window =
        nums.stream()
            .limit(windowSize)
            .collect(Collectors.toCollection(
                  () -> new PriorityQueue<>(Comparator.comparingInt(Integer::intValue).reversed())));
    List<Integer> result = new ArrayList<Integer>();
    result.add(window.peek());
    for (int i = windowSize; i < nums.size(); i++) {
      window.remove(nums.get(i - windowSize));
      window.add(nums.get(i));
      result.add(window.peek());
    }
    return result;
  }

  public static void main(String[] args) {
    List<Integer> input = IntStream.of(1, 4, 2, 3, 6, 7).boxed().toList();
    List<Integer> result = findMaxSlidingWindow(input, 3);
  }
}
