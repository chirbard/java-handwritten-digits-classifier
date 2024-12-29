import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML layout and set the scene
        Parent root = FXMLLoader.load(getClass().getResource("fx.fxml"));
        primaryStage.setTitle("Numbri tuvastus");
        primaryStage.setScene(new Scene(root, 620, 620));  // Adjust size based on grid
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}