public class KonsooliPilt {
    public static void intensiivsusPilt(short[] maatriks) {
        char[] heledusTugevus = { ' ', '.', ':', '-', '=', '+', '*', '#', '%', '@' };

        int laius = 28;
        int kõrgus = 28;

        for (int i = 0; i < kõrgus; i++) {
            for (int j = 0; j < laius; j++) {
                int indeks = i * laius + j;
                int heledusIndeks = Math.min(maatriks[indeks] * heledusTugevus.length / 256, heledusTugevus.length - 1);
                System.out.print(heledusTugevus[heledusIndeks]);
            }
            System.out.println();
        }
    }
}
