public class Arvutused {

    public static double[] maatriksiteKorrutamine(double[] pilt, double[][] kaal) {

        double[] result = new double[kaal.length];

        for (int j = 0; j < kaal.length; j++) {
            double sum = 0;
            for (int k = 0; k < pilt.length; k++) {
                sum += pilt[k] * kaal[j][k];
            }
            result[j] = sum;
        }
        return result;
    }

    public static double[] maatriksiteLiitmine(double[] A, double[] B) {
        for (int i = 0; i < A.length; i++) {
            A[i] += B[i];
        }
        return A;
    }

    public static double[] ReLU(double[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0) {
                A[i] = 0;
            }
        }
        return A;
    }

    public static double[] softmax(double[] A) {
        double[] osa = new double[A.length];

        double expSumma = 0;
        for (int i = 0; i < A.length; i++) {
            expSumma += Math.exp(A[i]);
        }

        for (int i = 0; i < A.length; i++) {
            osa[i] = Math.exp(A[i]) / expSumma;
        }
        return osa;
    }

    public static int maxElement(double[] A) {
        int indeks = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] > A[indeks]) {
                indeks = i;
            }
        }
        return indeks;
    }
}