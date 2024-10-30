// create interface Kaalud
public class Kaalud {
    private double[][] w1;
    private double[][] w2;
    private double[] b1;
    private double[] b2;

    public Kaalud(double[][] w1, double[][] w2, double[] b1, double[] b2) {
        this.w1 = w1;
        this.w2 = w2;
        this.b1 = b1;
        this.b2 = b2;
    }

    public double[][] getW1() {
        return w1;
    }

    public double[][] getW2() {
        return w2;
    }

    public double[] getB1() {
        return b1;
    }

    public double[] getB2() {
        return b2;
    }
}