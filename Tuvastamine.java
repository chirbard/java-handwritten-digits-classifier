import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Klass, mis kasutab Lugemine, Arvutused ja Konsoolipilt klasse , et lugeda,
 * arvutada mudeli vastust ja kuvada numbrit konsooli.
 */
public class Tuvastamine {

    private static double[] vabaliikmed1 = null;
    private static double[][] kaal1 = null;
    private static double[] vabaliikmed2 = null;
    private static double[][] kaal2 = null;

    public Tuvastamine() {
        try {
            Kaalud kaalud = Lugemine.loeJsonKaaludeks("data/kaalud.json");
            vabaliikmed1 = kaalud.getB1();
            kaal1 = kaalud.getW1();
            vabaliikmed2 = kaalud.getB2();
            kaal2 = kaalud.getW2();
        } catch (FileNotFoundException e) {
            LogimiseSingleton.getInstants().getLogija().severe("Faili kaalud.json ei leitud");
            System.out.println("Faili kaalud.json ei leitud");
        }
        
    }


    
    


    public static void main(String[] args) {
        // Kirjutame logi faili, et programmi töö algas
        LogimiseSingleton.getInstants().getLogija().info("Rakenduse töö algas");

        try {

            // Kõik test.csv failis asuvad pildid
            short[][] pildid;
            try {
                pildid = Lugemine.loeCSV("data/test.csv");
            } catch (FileNotFoundException e) {
                LogimiseSingleton.getInstants().getLogija().severe("Faili test.csv ei leitud");
                System.out.println("Faili test.csv ei leitud");
                throw e;
            }

            // Initsialiseerime skänneri
            java.util.Scanner sc = new java.util.Scanner(System.in);

            while (true) {
                System.out.println("Mitmendat pilti soovid testida (1-" + pildid.length + "): ");

                int pildiIndeks = 0;

                try {
                    pildiIndeks = sc.nextInt();
                    sc.nextLine();
                } catch (java.util.InputMismatchException e) {
                    LogimiseSingleton.getInstants().getLogija()
                            .warning("Kasutaja sisestas: " + sc.next() + " mis pole number");
                    System.out.println("Palun sisesta korrektne number!\n");
                    sc.nextLine();
                    continue;
                }

                if (pildiIndeks < 1 || pildiIndeks > pildid.length) {
                    LogimiseSingleton.getInstants().getLogija().warning("Kasutaja sisestas numbri: " + pildiIndeks
                            + " mis pole vahemikus 1-" + pildid.length);
                    System.out.println("Sellise indeksiga pilti pole, proovi uuesti!\n");
                    continue;
                }

                LogimiseSingleton.getInstants().getLogija().info("Kasutaja valis pildi indeksiga " + pildiIndeks);

                // Vähendame indeksit, sest massiiv on nullist indekseeritud
                pildiIndeks--;

                double[] pilt = Arvutused.shortMassiivDoubleks(pildid[pildiIndeks]);

                KonsooliPilt.intensiivsusPilt(28, 28, pildid[pildiIndeks]);

                int mudeliHinnang = tuvastaNumber(pilt, vabaliikmed1, kaal1, vabaliikmed2, kaal2);

                LogimiseSingleton.getInstants().getLogija().info("Mudeli hinnang: " + mudeliHinnang);

                System.out.println("Mudeli hinnang: "
                        + mudeliHinnang
                        + System.lineSeparator());

                // Kui kasutaja soovib veel testida, siis jätkame programmi tööga
                if (kasJätkata(sc, "jah", "ei")) {
                    continue;
                } else {
                    break;
                }
            }

            // Sulgeme skänneri
            sc.close();

        } catch (Exception e) {
            // kui programmi töö jooksul tekib viga, siis logime selle ja kuvame konsoolis
            LogimiseSingleton.getInstants().getLogija().severe("Midagi läks valesti: " + e.getMessage());
            System.out.println("Midagi läks valesti: " + e.getMessage());
        } finally {
            // Kirjutame logi faili, et programmi töö lõppes
            LogimiseSingleton.getInstants().getLogija().info("Rakenduse töö lõppes");
        }
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

    /**
     * Kasutajalt ühe või teise valiku küsimine konsoolis.
     * 
     * @param skanner - sisendi skanner objekti, mida kasutatakse kasutaja
     *                sisendi lugemiseks
     * @param valik1  - esimene valik (näiteks "jah")
     * @param valik2  - teine valik (näiteks "ei")
     * @return true - kasutaja valib esimese valiku, false - kasutaja valib
     *         teise valiku
     */
    public static boolean kasJätkata(Scanner skanner, String valik1, String valik2) {
        boolean jätka = false;

        System.out.println("Kas soovid veel testida? (" + valik1 + "/" + valik2 + ")");
        String vastus = skanner.nextLine();

        if (vastus.equalsIgnoreCase(valik1)) {
            LogimiseSingleton.getInstants().getLogija().info("Kasutaja soovib veel testida");
            jätka = true;
        } else if (vastus.equalsIgnoreCase(valik2)) {
            LogimiseSingleton.getInstants().getLogija().info("Kasutaja ei soovi rohkem pilte testida");
            jätka = false;
        } else {
            LogimiseSingleton.getInstants().getLogija().warning("Kasutaja sisestas: " + vastus
                    + " mis pole valikute hulgas: '" + valik1 + "' või '" + valik2 + "'");
            System.out.println("Palun sisesta kas '" + valik1 + "' või '" + valik2 + "'!\n");
            jätka = kasJätkata(skanner, valik1, valik2);
        }
        System.out.println();
        return jätka;
    }


    public static int tuvasta(int[][] andmed) {
        double[] massiiv = Arvutused.intMaatriksDoubleks(andmed);
        return tuvastaNumber(massiiv, vabaliikmed1, kaal1, vabaliikmed2, kaal2);
    }
}
