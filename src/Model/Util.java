package Model;

public class Util {
    public static int getMax(int[] array) {
        int max = 0;
        for (int val: array) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }
}
