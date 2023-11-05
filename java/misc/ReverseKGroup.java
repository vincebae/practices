import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ReverseKGroup {

  private static class LinkedListNode {
    int data;
    LinkedListNode next;

    LinkedListNode(int data, LinkedListNode next) {
      this.data = data;
      this.next = next;
    }

    LinkedListNode(int data) {
      this(data, null);
    }

    public String toString() {
      return String.valueOf(data);
    }

    public boolean equals(LinkedListNode other) {
      return this.data == other.data;
    }
  }

  private static LinkedListNode createLinkedList(List<Integer> list) {
    // Iterator reversely.
    LinkedListNode head = null;
    ListIterator<Integer> it = list.listIterator(list.size());
    while (it.hasPrevious()) {
      head = new LinkedListNode(it.previous(), head);
    }
    return head;
  }

  private static void printLinkedList(LinkedListNode head) {
    if (head == null) {
      System.out.println("");
    } else {
      System.out.print(head + ", ");
      printLinkedList(head.next);
    }
  }

  private static boolean areListsEqual(LinkedListNode head1, LinkedListNode head2) {
    if (head1 == null && head2 == null) {
      return true;
    }
    if (head1 == null || head2 == null) {
      return false;
    }
    return head1.equals(head2) && areListsEqual(head1.next, head2.next);
  }

  private static LinkedListNode advance(LinkedListNode node, int k) {
    return (k == 0 || node == null) ? node : advance(node.next, k - 1);
  }

  private static void reverse(LinkedListNode start, LinkedListNode end) {
    if (start == null || start.next == null) {
      return;
    }
    LinkedListNode back = null;
    LinkedListNode curr = start;
    while (back != end) {
      LinkedListNode next = curr.next;
      curr.next = back;
      back = curr;
      curr = next;
    }
  }

  public static LinkedListNode reverseLinkedList(LinkedListNode head, int k) {
    LinkedListNode groupStart = head;
    LinkedListNode groupEnd = advance(head, k - 1);
    LinkedListNode newHead = groupEnd == null ? head : groupEnd;
    LinkedListNode prevGroupEnd = null;
    while (groupEnd != null) {
      LinkedListNode nextGroupStart = groupEnd.next;
      reverse(groupStart, groupEnd);
      if (prevGroupEnd != null) {
        prevGroupEnd.next = groupEnd;
      }
      prevGroupEnd = groupStart;
      groupStart.next = nextGroupStart;
      groupStart = nextGroupStart;
      groupEnd = advance(groupStart, k - 1);
    }
    return newHead;
  }

  private static void test(List<Integer> input, int k, List<Integer> expected) {
    LinkedListNode actual = reverseLinkedList(createLinkedList(input), k);
    if (areListsEqual(actual, createLinkedList(expected))) {
      System.out.println("Success");
    } else {
      System.out.println("Failure:");
      printLinkedList(actual);
    }
  }

  public static void main(String[] args) {
    test(Arrays.asList(6, 8, 7), 1, Arrays.asList(6, 8, 7));
    test(Arrays.asList(9, 0, 8, 2), 2, Arrays.asList(0, 9, 2, 8));
    test(Arrays.asList(1, 2, 3, 4, 5), 3, Arrays.asList(3, 2, 1, 4, 5));
  }
}
