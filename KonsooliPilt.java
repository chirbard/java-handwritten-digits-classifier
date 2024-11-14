/**
 * Staatiline klass, mille meetodiga saab konsooli printida heleduse järgi pildi
 */
public class KonsooliPilt {
    /**
     * Kuvab terminali aknas ASCII trükimärkidest tehtud pildi
     * <p>
     * Kasutatud Allikad:
     * <ul>
     * <li>https://medium.com/@shubham0473/unleash-your-inner-artist-a-step-by-step-guide-to-converting-images-to-ascii-art-using-java-97860464f19a</li>
     * <li>https://paulbourke.net/dataformats/asciiart/</li>
     * </ul>
     * <p>
     * 
     * @param pikkus   - pildi pikkus
     * @param laius    - pildi laius
     * @param maatriks - pildi pikslite heleduse maatriks
     */
    public static void intensiivsusPilt(int pikkus, int laius, short[] maatriks) {
        char[] heledusTugevus = { ' ', '.', ':', '-', '=', '+', '*', '#', '%', '@' };

        for (int i = 0; i < pikkus; i++) {
            for (int j = 0; j < laius; j++) {
                int indeks = i * laius + j;
                int heledusIndeks = Math.min(maatriks[indeks] * heledusTugevus.length / 256, heledusTugevus.length - 1);
                System.out.print(heledusTugevus[heledusIndeks]);
            }
            System.out.println();
        }
    }
}
