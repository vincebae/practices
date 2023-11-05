import java.util.stream.IntStream;

public class EggDrop {

    private static boolean logEnabled = false;

    private static void log(String str) {
        if (logEnabled) {
            System.out.println(str);
        }
    }

    private static int findLeastDrops(int eggs, int floors) {
        if (eggs == 0) {
            log("No more eggs.");
            return 0;
        }
        if (floors <= 1) {
            log("Low floor: " + floors);
            return Math.max(floors, 0);
        }
        if (eggs == 1) {
            log("Only egg: " + floors);
            return floors;
        }

        int[][] lookup = new int[2][floors + 1];
        IntStream.range(1, floors + 1). forEach(i -> lookup[1][i] = i);
        for (int j = 2; j <= eggs; j++) {
            for (int i = 2; i <= floors; i++) {
                int minimum = Integer.MAX_VALUE;
                for (int k = 1; k <= i; k++) {
                    var localMax = Math.max(lookup[j % 2][k - 1], lookup[(j - 1) % 2][i - k]) + 1;
                    minimum = Math.min(minimum, localMax);
                }
                lookup[j % 2][i] = minimum;
            }
        }
        return lookup[eggs % 2][floors];
    }
    
    private static void test(int eggs, int floors, int expected) {
        int actual = findLeastDrops(eggs, floors);
        if (actual == expected) {
            System.out.println("Success");
        } else {
            System.out.println(String.format("eggs: %d, floors: %d, actual: %d, expected: %d",
                        eggs, floors, actual, expected));
        }
        
    }

    public static void main(String[] args) {
       test(6, 15, 4);
    }
}
