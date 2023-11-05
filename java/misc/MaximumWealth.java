import java.util.Collection;

public class MaximumWealth {

  public static int maximumWealth(Collection<Collection<Integer>> accounts) {
    return accounts.stream().map(acc -> acc.stream().sum()).max().get();
  }

  public static void main(String args[]) {

  }
}
