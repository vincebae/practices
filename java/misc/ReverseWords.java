public class ReverseWords {

  public static String reverseWords(String sentence) {
    StringBuilder stringBuilder = new StringBuilder();

    int scanPosHead = sentence.length() - 1;
    int scanPosTail = scanPosHead;
    while (scanPosHead >= 0) {
      char ch = sentence.charAt(scanPosHead);
      if (ch == ' ') {
        stringBuilder.append(sentence, scanPosHead + 1, scanPosTail + 1);
        stringBuilder.append(' ');
        while (scanPosHead >=0 && sentence.charAt(scanPosHead) == ' ') {
          scanPosHead--;
        }
        scanPosTail = scanPosHead;
      } else {
        scanPosHead--;
      }
    }

    if (scanPosTail >= 0) {
      stringBuilder.append(sentence, 0, scanPosTail + 1);
    }
    return stringBuilder.toString();
  }

  private static void test(String input, String expected) {
    String actual = reverseWords(input);
    if (actual.equals(expected)) {
      System.out.println("Success.");
    } else {
      System.out.println("Failure: " + actual);
    }
  }

  public static void main(String[] args) {
    test("Hello World", "World Hello");
    test("Hello    World", "World Hello");
  }
}
