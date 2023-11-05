import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class ReverseBetween {

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
    return (k <= 0 || node == null) ? node : advance(node.next, k - 1);
  }

  public static LinkedListNode reverseBetween(LinkedListNode head, int left, int right) {
    LinkedListNode prevNode = left <= 1 ? null : advance(head, left - 2);
    LinkedListNode leftNode = left == 1 ? head : prevNode.next;
    LinkedListNode rightNode = advance(leftNode, right - left);
    if (leftNode == null || rightNode == null) {
      return head;
    }

    LinkedListNode back = rightNode.next;
    LinkedListNode curr = leftNode;
    while (back != rightNode) {
      LinkedListNode next = curr.next;
      curr.next = back;
      back = curr;
      curr = next;
    }
    if (prevNode == null) {
      return rightNode;
    } else {
      prevNode.next = rightNode;
      return head;
    }
  }

  private static void test(List<Integer> input, int left, int right, List<Integer> expected) {
    LinkedListNode actual = reverseBetween(createLinkedList(input), left, right);
    if (areListsEqual(actual, createLinkedList(expected))) {
      System.out.println("Success");
    } else {
      System.out.println("Failure:");
      printLinkedList(actual);
    }
  }

  public static void main(String[] args) {
    test(Arrays.asList(6, 8, 7), 1, 2, Arrays.asList(8, 6, 7));
    test(Arrays.asList(9, 0, 8, 2), 2, 4, Arrays.asList(9, 2, 8, 0));
    test(Arrays.asList(1, 2, 3, 4, 5), 1, 5, Arrays.asList(5, 4, 3, 2, 1));
  }
}
