import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Kasutatud Allikad:
 * <ul>
 * <li>https://www.geeksforgeeks.org/singleton-class-java/</li>
 * <li>https://www.geeksforgeeks.org/logging-in-java/</li>
 * <li>https://stackoverflow.com/questions/15758685/how-to-write-logs-in-text-file-when-using-java-util-logging-logger</li>
 * <li>https://stackoverflow.com/questions/15571496/how-to-check-if-a-folder-exists</li>
 * </ul>
 */
public class LogimiseSingleton {

    private static Logger logija = null;

    private static LogimiseSingleton instants = null;

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

    public static LogimiseSingleton getInstants() {
        if (instants == null) {
            instants = new LogimiseSingleton();
        }
        return instants;
    }

    public Logger getLogija() {
        return logija;
    }
}