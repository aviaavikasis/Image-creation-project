package primitives;

public abstract class Util {
    private static final int ACCURACY = -40;

    public Util() {
    }

    private static int getExp(double num) {
        return (int)(Double.doubleToRawLongBits(num) >> 52 & 2047L) - 1023;
    }

    public static boolean isZero(double number) {
        return getExp(number) < -40;
    }

    public static double alignZero(double number) {
        return getExp(number) < -40 ? 0.0D : number;
    }
}
