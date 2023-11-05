public class MinimumWindowSubsequence {

  public static String minWindow(String s, String t) {
    int posFront = 0;
    int posTarget = 0;
    String minSubstr = "";
    while (posFront < s.length()) {
      while (posFront < s.length() && s.charAt(posFront) != t.charAt(posTarget)) {
        posFront++;
      }
      if (posFront >= s.length()) {
        break;
      }

      int posBack = posFront;
      while (posBack < s.length() && posTarget < t.length()) {
        if (s.charAt(posBack) == t.charAt(posTarget)) {
          posBack++;
          posTarget++;
          if (posTarget == t.length()) {
            if (minSubstr.isEmpty()  || (posBack - posFront) < minSubstr.length()) {
              minSubstr = s.substring(posFront, posBack);
            }
            break;
          }
        } else {
          posBack++;
        }
      }
      posTarget = 0;
      posFront++;
    }
    return minSubstr;
  }

  public static void main(String[] args) {
    String str1 = "this is a test string";
    String str2 = "tis";

    System.out.println(minWindow(str1, str2));
    System.out.println(minWindow("abcdebdde" , "bde"));
    System.out.println(minWindow("abababa" , "cdcd"));
  }
}
