public class EuclideanAlgorithm {
    
    public static int GCD(int a, int b) {
        var min = Math.min(a, b);
        var max = Math.max(a, b);

        while (true) {
            int remainder = max % min;
            if (remainder == 0) {
                return min;
            }
            max = min;
            min = remainder;
        }
    }
}
