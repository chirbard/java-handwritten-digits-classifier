import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // laeme FXML-i paigutuse ja seadistame stseeni
            Parent root = FXMLLoader.load(getClass().getResource("fx.fxml"));
            primaryStage.setTitle("Numbri tuvastus");
            primaryStage.setScene(new Scene(root, 588, 615)); // 588x615 suurune aken, et kõik elemendid mahuksid ekraanile
            primaryStage.show();
        } catch (Exception e) {
            LogimiseSingleton.getInstants().getLogija().severe("FXML-i laadimine ebaõnnestus");
            System.out.println("FXML-i laadimine ebaõnnestus");
        }
    }

    public static void main(String[] args) {
        // Kirjutame logi faili, et programmi töö algas
        LogimiseSingleton.getInstants().getLogija().info("Rakenduse töö algas");

        launch(args);

        // Kirjutame logi faili, et programmi töö lõppes
        LogimiseSingleton.getInstants().getLogija().info("Rakenduse töö lõppes");
    }
}
