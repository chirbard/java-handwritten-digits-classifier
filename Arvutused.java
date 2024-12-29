/**
 * Staatiline klass, kus on mudeli kasutamiseks vajalikud arvutus meetodid.
 */
public class Arvutused {
    /**
     * Maatriksi korrutamine vektoriga.
     * <p>
     * Kasutatud Allikad:
     * <ul>
     * <li>https://www.varsitytutors.com/hotmath/hotmath_help/topics/multiplying-vector-by-a-matrix</li>
     * </ul>
     * <p>
     * 
     * @param massiiv
     * @param maatriks
     * @return tulemuste massiiv
     * @throws IllegalArgumentException
     */
    public static double[] maatriksiKorrutamineVektoriga(double[] vektor, double[][] maatriks) {

        if (maatriks[0].length != vektor.length) {
            throw new IllegalArgumentException("Antud maatriksit ei saa antud massiiviga korrutada.");
        }

        double[] tulemus = new double[maatriks.length];

        for (int i = 0; i < maatriks.length; i++) {
            double summa = 0;
            for (int j = 0; j < vektor.length; j++) {
                summa += vektor[j] * maatriks[i][j];
            }
            tulemus[i] = summa;
        }
        return tulemus;
    }

    /**
     * Massiivi liitmine teise massiiviga.
     * <p>
     * Kasutatud Allikad:
     * <ul>
     * <li>https://www.khanacademy.org/math/linear-algebra/vectors-and-spaces/vectors/v/adding-vectors#:~:text=To%20add%20the%20vectors%20(x%E2%82%81,%E2%80%8B%E2%80%8B.</li>
     * </ul>
     * <p>
     * 
     * @param massiiv
     * @param massiiv
     * @return tulemuste massiiv
     * @throws IllegalArgumentException
     */
    public static double[] massiivideLiitmine(double[] massiiv1, double[] massiiv2) {

        if (massiiv1.length != massiiv1.length) {
            throw new IllegalArgumentException("Antud kahte massiivi ei saa omavahel liita.");
        }

        for (int i = 0; i < massiiv1.length; i++) {
            massiiv1[i] += massiiv2[i];
        }
        return massiiv1;
    }

    /**
     * mittenegatiivne lineaar funktsioon (ingl. k. ReLU), negatiivne element
     * massiivis muudetakse 0-ks.
     * <p>
     * Kasutatud Allikad:
     * <ul>
     * <li>https://builtin.com/machine-learning/relu-activation-function</li>
     * </ul>
     * <p>
     * 
     * @param massiiv
     * @return tulemuste massiiv
     */
    public static double[] mittenegatiivneLineaarfunktsioon(double[] massiiv) {
        for (int i = 0; i < massiiv.length; i++) {
            if (massiiv[i] < 0) {
                massiiv[i] = 0;
            }
        }
        return massiiv;
    }

    /**
     * Massiivi e astmes väärtuste summa
     * 
     * @param massiiv
     * @return summa
     */
    private static double euleriEksponentFunktsiooniSumma(double[] massiiv) {
        double summa = 0;
        for (double i : massiiv) {
            summa += Math.exp(i);
        }
        return summa;
    }

    /**
     * Massiivi väärtuste suhe massiivi summaga võrreldes
     * 
     * @param massiiv
     * @param summa
     * @return suurima väärtuse indeks
     */
    private static double[] elementideMassiiviSuhe(double[] massiiv, double summa) {
        for (int i = 0; i < massiiv.length; i++) {
            massiiv[i] = Math.exp(massiiv[i]) / summa;
        }
        return massiiv;
    }

    /**
     * Massiivi väärtused on vahemikus 0-1, väärtuste summa on 1.
     * <p>
     * Kasutatud Allikad:
     * <ul>
     * <li>https://deepai.org/machine-learning-glossary-and-terms/softmax-layer#:~:text=The%20softmax%20function%20is%20a,can%20be%20interpreted%20as%20probabilities.</li>
     * </ul>
     * <p>
     * 
     * @param massiiv
     * @return tulemuste massiiv
     */
    public static double[] softmax(double[] massiiv) {
        return elementideMassiiviSuhe(massiiv, euleriEksponentFunktsiooniSumma(massiiv));
    }

    /**
     * Esimese suurima väärtuse indeks
     * 
     * @param massiiv
     * @return suurima väärtuse indeks
     */
    public static int suurimaVäärtuseIndeksMassiivis(double[] massiiv) {
        int indeks = 0;

        for (int i = 0; i < massiiv.length; i++) {
            if (massiiv[i] > massiiv[indeks]) {
                indeks = i;
            }
        }
        return indeks;
    }

    /**
     * Teisendab sisendmassivi short tüübist double tüübiks,
     * normaliseerides väärtused vahemikku [0, 1].
     * 
     * @param massiiv - short[] massiiv, mida soovime teisendada
     * @return double[] massiiv, kus iga väärtus on vahemikus [0, 1]
     */
    public static double[] shortMassiivDoubleks(short[] massiiv) {
        double[] doubleMassiiv = new double[massiiv.length];
        for (int i = 0; i < massiiv.length; i++) {
            doubleMassiiv[i] = (double) massiiv[i] / 1000;
        }
        return doubleMassiiv;
    }

     /**
     * Teisendab sisendmaatriksi int tüübist double tüüpi massiiviks,
     * normaliseerides väärtused vahemikku [0, 1].
     * 
     * @param maatriks - int[][] maatriks, mida soovime teisendada
     * @return double[] massiiv, kus iga väärtus on vahemikus [0, 1]
     */
    public static double[] intMaatriksDoubleks(int[][] maatriks) {
        double[] doubleMassiiv = new double[maatriks.length * maatriks[0].length];
        int k = 0;
        for (int[] massiiv : maatriks) {
            for (int i = 0; i < maatriks[0].length; i++) {
                doubleMassiiv[k] = (double) massiiv[i] / 255;
                k++;
            }
        }
        return doubleMassiiv;
    }


}