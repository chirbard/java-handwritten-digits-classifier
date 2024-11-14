import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Staatiline klass mille meetoditega saab failidest programmi töö jaoks
 * vajaliku info lugeda koodis kasutatavateks andmestruktuurideks.
 */
public class Lugemine {
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
	 * @throws FileNotFoundException
	 */
	public static short[][] loeCSV(String failiAsukoht) throws FileNotFoundException {
		java.io.File fail = new java.io.File(failiAsukoht);

		try (java.util.Scanner sc = new java.util.Scanner(fail, "UTF-8")) {
			sc.nextLine(); // Jäta pealkirjade rida vahele.
			int uhePildiPikkus = 784;
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

	/**
	 * Loeb json failist sisse mudeli kaalud ja hoiustab need Kaalud klassi.
	 * 
	 * https://stackoverflow.com/questions/2591098/how-to-parse-json-in-java
	 * https://stackoverflow.com/questions/10926353/how-to-read-json-file-into-java-with-simple-json-library
	 * https://github.com/stleary/JSON-java
	 * 
	 * @param failiAsukoht
	 * @return Kaalude klass, kus saab kõik vajalikud kaalud lihtsalt kätte
	 * @throws FileNotFoundException
	 */
	public static Kaalud loeJsonKaaludeks(String failiAsukoht) throws FileNotFoundException {

		String jsonStringina = loeJsonStringiks(failiAsukoht);

		JSONObject jsonObjekt = new JSONObject(jsonStringina);

		JSONArray w1JsonArray = jsonObjekt.getJSONArray("w1");
		double[][] w1 = JSONArrayDoubleMaatrikiks(w1JsonArray);
		JSONArray w2JsonArray = jsonObjekt.getJSONArray("w2");
		double[][] w2 = JSONArrayDoubleMaatrikiks(w2JsonArray);
		JSONArray b1JsonArray = jsonObjekt.getJSONArray("b1");
		double[] b1 = JSONArrayDoubleArrayks(b1JsonArray);
		JSONArray b2JsonArray = jsonObjekt.getJSONArray("b2");
		double[] b2 = JSONArrayDoubleArrayks(b2JsonArray);

		Kaalud kaalud = new Kaalud(w1, w2, b1, b2);

		return kaalud;
	}

	/**
	 * Loeb faili sisse ja tagastab selle ühe pika sõnena
	 * 
	 * @param failiAsukoht
	 * @return faili sisu sõnena
	 * @throws FileNotFoundException
	 */
	private static String loeJsonStringiks(String failiAsukoht) throws FileNotFoundException {
		java.io.File fail = new java.io.File(failiAsukoht);
		try (java.util.Scanner sc = new java.util.Scanner(fail, "UTF-8")) {
			StringBuilder sb = new StringBuilder();
			while (sc.hasNextLine()) {
				sb.append(sc.nextLine());
			}
			return sb.toString();
		}
	}

	/**
	 * Muudab JSONArray tüübi double[] andmetüübiks
	 * 
	 * @param jsonArray
	 * @return sisend parameeter double[] andmetüübina
	 */
	private static double[] JSONArrayDoubleArrayks(JSONArray jsonArray) {
		double[] array = new double[jsonArray.length()];
		for (int i = 0; i < jsonArray.length(); i++) {
			array[i] = jsonArray.getDouble(i);
		}
		return array;
	}

	/**
	 * Muudab JSONArray double[][] andmetüübiks
	 * 
	 * @param jsonArray
	 * @return sisend parameeter double[][] andmetüübina
	 */
	private static double[][] JSONArrayDoubleMaatrikiks(JSONArray jsonArray) {
		double[][] maatriks = new double[jsonArray.length()][];
		for (int i = 0; i < jsonArray.length(); i++) {
			maatriks[i] = JSONArrayDoubleArrayks(jsonArray.getJSONArray(i));
		}
		return maatriks;
	}
}
