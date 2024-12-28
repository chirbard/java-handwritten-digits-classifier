import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Singleton klass, millega saab igas klassis lihtsalt teateid logida.
 * Sellest klassist on ainult üks instants, mis on seadistatud kindlasse faili
 * logisid salvestama.
 * 
 * Kasutatud Allikad:
 * <ul>
 * <li>https://www.geeksforgeeks.org/singleton-class-java/</li>
 * <li>https://www.geeksforgeeks.org/logging-in-java/</li>
 * <li>https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger</li>
 * <li>https://stackoverflow.com/questions/15571496/how-to-check-if-a-folder-exists</li>
 * </ul>
 */
public class LogimiseSingleton {
    /**
     * Logger objekt, mille konfigureerime kindlasse faili logisid salvestama.
     * Seda saab kasutada, et teateid logida. Näiteks info logi jaoks kasutame
     * `.info()` meetodit vastava teatega.
     */
    private static Logger logija = null;

    /**
     * Selle singleton klassi instants, mis initsialiseeritakse ainult üks kord.
     */
    private static LogimiseSingleton instants = null;

    /**
     * Konstruktor, mis initsialiseerib logija ja seadistab selle kindlasse faili
     * logisid salvestama.
     */
    private LogimiseSingleton() {
        logija = Logger.getLogger("RakenduseLogija");
        try {
            File logiKaust = new File("logid");
            if (!logiKaust.exists()) {
                logiKaust.mkdir();
            }

            File logiFail = new File(logiKaust, "logi.txt");
            if (!logiFail.exists()) {
                logiFail.createNewFile();
            }

            FileHandler fh = new FileHandler(logiFail.getAbsolutePath());
            logija.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            // Ära kirjuta logisid konsooli
            logija.setUseParentHandlers(false);
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tagastab selle singleton klassi instantsi. Kui instantsi pole veel
     * initsialiseeritud, siis initsialiseeritakse see.
     * 
     * @return Selle singleton klassi instants.
     */
    public static LogimiseSingleton getInstants() {
        if (instants == null) {
            instants = new LogimiseSingleton();
        }
        return instants;
    }

    /**
     * Tagastab logija objekti, mida saab kasutada logide salvestamiseks.
     * 
     * @return Logger objekt.
     */
    public Logger getLogija() {
        return logija;
    }
}