import java.io.FileNotFoundException;

public class Tuvastamine {
    public static void main(String[] args) throws FileNotFoundException {
        // Kõik test.csv failis asuvad pildid
        short[][] pildid = Lugemine.loeCSV("data/test.csv");

        // Mitmendat pilti testida tahame: n - 1
        int pildiIndeks = 9;

        double[] pilt = new double[784];

        for (int i = 0; i < pildid[pildiIndeks].length; i++) {
            pilt[i] = (double) pildid[pildiIndeks][i] / 1000;
        }

        // Mudeli tööks vajalikud kaalud ja vabaliikmed
        Kaalud kaalud = Lugemine.loeJsonKaaludeks("data/kaalud.json");

        // Kuvame pildi terminali aknas
        KonsooliPilt.intensiivsusPilt(28, 28, pildid[pildiIndeks]);

        System.out.println("Mudeli hinnang: "
                + tuvastaNumber(pilt, kaalud.getB1(), kaalud.getW1(), kaalud.getB2(), kaalud.getW2()));
    }

    /**
     * Tuvastab sisend maatriksi väärtuste põhjal numbri 0 kuni 9.
     * <p>
     * Kasutatud Allikad:
     * <ul>
     * <li>https://www.kaggle.com/code/wwsalmon/simple-mnist-nn-from-scratch-numpy-no-tf-keras</li>
     * <li>https://www.singlestore.com/blog/a-guide-to-softmax-activation-function/</li>
     * </ul>
     * <p>
     * 
     * @param massiiv      - pildi maatriks massiivina
     * @param vabaliikmed1 - esimese neuron kihi vabaliikmed
     * @param kaal1        - esimese neuron kihi kaalud
     * @param vabaliikmed2 - teise neuron kihi vabaliikmed
     * @param kaal2        - teise neuron kihi kaalud
     * @return number vahemikus 0-9, mudeli arvamus
     */
    public static int tuvastaNumber(double[] massiiv, double[] vabaliikmed1, double[][] kaal1, double[] vabaliikmed2,
            double[][] kaal2) {

        double[] Z1 = Arvutused.massiivideLiitmine(Arvutused.maatriksiKorrutamineVektoriga(massiiv, kaal1),
                vabaliikmed1);

        double[] A1 = Arvutused.mittenegatiivneLineaarfunktsioon(Z1);

        double[] Z2 = Arvutused.massiivideLiitmine(Arvutused.maatriksiKorrutamineVektoriga(A1, kaal2), vabaliikmed2);

        double[] A2 = Arvutused.softmax(Z2);

        return Arvutused.suurimaVäärtuseIndeksMassiivis(A2);
    }
}
