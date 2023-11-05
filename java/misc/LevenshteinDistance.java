import java.util.stream.IntStream;
import java.util.Arrays;

public class LevenshteinDistance {

    public static int min(int... intArray) {
        return Arrays.stream(intArray).min().getAsInt();
    }

    private static int minEditDistance(String str1, String str2) {
        if (str1.length() == 0) {
            return str2.length();
        }
        if (str2.length() == 0) {
            return str1.length();
        }

        int[][] lookup = new int[str1.length() + 1][str2.length() + 1];
        IntStream.range(0, str1.length()).forEach(i -> lookup[i][0] = i);
        IntStream.range(0, str2.length()).forEach(i -> lookup[0][i] = i);

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    lookup[i][j] = lookup[i - 1][j - 1];
                } else {
                    lookup[i][j] = 1 + min(
                            lookup[i - 1][j - 1],
                            lookup[i][j - 1],
                            lookup[i - 1][j]);
                }
            }
        }
        return lookup[str1.length()][str2.length()];
    }

    private static void test(String str1, String str2, int expected) {
        int actual = minEditDistance(str1, str2);
        if (actual == expected) {
            System.out.println("Success.");
        } else {
            System.out.println(String.format("Failure. actual = %d, expected = %d", actual, expected));
        }
    }

    public static void main(String[] args) {
        test("Tuesday", "Thursday", 2);
        test("abcd1234", "efg1234h", 5);
        test("EdicativeIsFun", "AlgorithmsAreFun", 12);
    }
}
