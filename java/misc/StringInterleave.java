
public class StringInterleave {

    private static boolean isInterleaved(String str1, String str2, String interleaved) {
        if (str1.length() + str2.length() != interleaved.length()) {
            return false;
        }

        int pos1 = 0;
        int pos2 = 0;
        while (pos1 < str1.length() && pos2 < str2.length()) {
            if (str1.charAt(pos1) == interleaved.charAt(pos1 + pos2)) {
                pos1++;
            } else if (str2.charAt(pos2) == interleaved.charAt(pos1 + pos2)) {
                pos2++;
            } else {
                return false;
            }
        }
        for (var i = pos1; i < str1.length(); i++) {
            if (str1.charAt(i) != interleaved.charAt(i + pos2)) {
                return false;
            }
        }

        for (var i = pos2; i < str2.length(); i++) {
            if (str2.charAt(i) != interleaved.charAt(i + pos1)) {
                return false;
            }
        }
        return true;
    }

    private static void test(String str1, String str2, String interleaved, boolean expected) {
        boolean actual = isInterleaved(str1, str2, interleaved);
        if (actual == expected) {
            System.out.println("Success.");
        } else {
            System.out.println("Failure.");
        }
    }

    public static void main(String[] args) {
        test("abd", "cef", "abcdef", true);
    }
}
