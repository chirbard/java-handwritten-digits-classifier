import java.util.ArrayList;

public class Lugemine {
  public static void main(String[] args) throws java.io.FileNotFoundException {
    short[][] pildid = loeCSV("data/test.csv");

    System.out.printf("Pilte kokku: %d%n", pildid.length);
  }

  /**
   * Loeb CSV failist pildid sisse.
   * <p>
   * Kasutatud Allikad:
   * <ul>
   * <li>https://www.javatpoint.com/how-to-read-csv-file-in-java</li>
   * <li>https://www.geeksforgeeks.org/how-to-convert-a-string-value-to-short-value-in-java-with-examples/</li>
   * </ul>
   * <p>
   * 
   * @param failiAsukoht - CSV faili asukoht
   * @return massiv, kus iga element on massiiv pildi pikslite väärtustega (0-255)
   * @throws java.io.FileNotFoundException
   */
  private static short[][] loeCSV(String failiAsukoht) throws java.io.FileNotFoundException {
    java.io.File fail = new java.io.File(failiAsukoht);

    try (java.util.Scanner sc = new java.util.Scanner(fail, "UTF-8")) {
      sc.nextLine(); // Jäta pealkirjade rida vahele.
      int uhePildiPikkus = 784;
      // int uhePildiPikkus = 4; // Väikese datasetiga testimiseks.
      ArrayList<short[]> pildid = new ArrayList<>();
      while (sc.hasNextLine()) {
        short[] pilt = new short[uhePildiPikkus];

        String rida = sc.nextLine();
        String[] pikslid = rida.split(",");
        for (int i = 0; i < uhePildiPikkus; i++) {
          pilt[i] = Short.parseShort(pikslid[i]);
        }
        pildid.add(pilt);
      }

      return pildid.toArray(new short[0][]);
    }
  }
}
