import java.io.FileNotFoundException;

public class Tuvastamine {
    public static void main(String[] args) throws FileNotFoundException {
        short[][] pildid = Lugemine.loeCSV("data/test.csv");
        short[] üksPilt = pildid[21];

        double[] pilt = new double[784];

        int index = 0;
        for (short number : üksPilt) {
            pilt[index] = (double) number;
            index++;
        }

        Kaalud kaalud = Lugemine.loeJsonKaaludeks("data/kaalud.json");

        KonsooliPilt.intensiivsusPilt(üksPilt);
        tuvastaNumber(pilt, kaalud.getB1(), kaalud.getW1(), kaalud.getB2(), kaalud.getW2());
    }

    private static void tuvastaNumber(double[] numbriMaatriks, double[] halve1, double[][] kaal1, double[] halve2,
            double[][] kaal2) {

        double[] Z1 = Arvutused.maatriksiteLiitmine(Arvutused.maatriksiteKorrutamine(numbriMaatriks, kaal1), halve1);

        for (int i = 0; i < Z1.length; i++) {
            Z1[i] = Z1[i] / 1000;
        }

        double[] A1 = Arvutused.ReLU(Z1);

        double[] Z2 = Arvutused.maatriksiteLiitmine(Arvutused.maatriksiteKorrutamine(A1, kaal2), halve2);

        double[] A2 = Arvutused.softmax(Z2);

        System.out.println("Mudeli hinnang: " + Arvutused.maxElement(A2));
        for (int i = 0; i < A2.length; i++) {
            System.out.println("Numbri " + i + " tõenäosus on: " + A2[i]);
        }
    }
}
