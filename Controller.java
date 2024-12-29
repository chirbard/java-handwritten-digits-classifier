import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

public class Controller {

    private static final int ruudustikuSuurus = 28; // 28x28 ruudustik
    private static final int ruuduSuurus = 20;
    private static final Color algVärv = Color.WHITE;
    private static final Color värvitud = Color.BLACK; 
    private static final Color piiriVärv = Color.LIGHTGRAY;

    private int[][] andmed = new int[ruudustikuSuurus][ruudustikuSuurus];

    @FXML
    private GridPane ruudustik; 

    public void initialize() {
        LogimiseSingleton.getInstants().getLogija().info("Ruudustiku loomine algas");

        if (ruudustik == null) {
            LogimiseSingleton.getInstants().getLogija().severe("Ruudustik pole initsialiseeritud");
            System.out.println("Ruudustik pole initsialiseeritud");
            return;
        }

        for (int rida = 0; rida < ruudustikuSuurus; rida++) {
            for (int tulp = 0; tulp < ruudustikuSuurus; tulp++) {

                Rectangle ruut = new Rectangle(ruuduSuurus, ruuduSuurus, algVärv);

                ruut.setStroke(piiriVärv);
                ruut.setOnMousePressed(e -> hiirVajutatud(e));
                ruut.setOnMouseDragEntered(e -> lohistamiseTuvastamine(e));
                ruut.setOnDragDetected(e -> lohistamine(e));

                // Lisame loodud ruudu ruudustikule
                ruudustik.add(ruut, tulp, rida);
            }
        }

        Button tühjendamisNupp = new Button("Tühjenda ruudustik");
        tühjendamisNupp.setOnAction(e -> ruudustikuPuhastamine());
        ruudustik.add(tühjendamisNupp, 7, ruudustikuSuurus + 1, ruudustikuSuurus, 1);

        Button tuvastusNupp = new Button("Tuvasta number");
        tuvastusNupp.setOnAction(e -> tuvastaNumber());
        ruudustik.add(tuvastusNupp, 15, ruudustikuSuurus + 1, ruudustikuSuurus, 1);

        LogimiseSingleton.getInstants().getLogija().info("Ruudustik loodud");

    }

    private void hiirVajutatud(MouseEvent e) {
        // Vajutatud ruut
        Rectangle ruut = (Rectangle) e.getSource();

        // Värvime ruudu mustaks
        ruut.setFill(värvitud);

        LogimiseSingleton.getInstants().getLogija()
                .info("Hiirevajutus koordinaatidel: " + ruudustik.getColumnIndex(ruut) + ", "
                        + ruudustik.getRowIndex(ruut));
    }

    private void lohistamiseTuvastamine(MouseEvent e) {
        // Ruudu väärtus
        Rectangle ruut = (Rectangle) e.getSource();

        // Värvime ruudu mustaks
        ruut.setFill(värvitud);

        LogimiseSingleton.getInstants().getLogija()
                .info("Hiire lohistamine koordinaatidel: " + ruudustik.getColumnIndex(ruut) + ", "
                        + ruudustik.getRowIndex(ruut));
    }

    private void lohistamine(MouseEvent e) {
        
        Rectangle ruut = (Rectangle) e.getSource();
        ruut.startFullDrag();

        LogimiseSingleton.getInstants().getLogija()
                .info("Lohistamine koordinaatidel: " + ruudustik.getColumnIndex(ruut) + ", "
                        + ruudustik.getRowIndex(ruut));
    }

    private void ruudustikuPuhastamine() {
        // Värvi kõik ruudud algvärvi
        ruudustik.getChildren().forEach(node -> {
            if (node instanceof Rectangle) {
                ((Rectangle) node).setFill(algVärv);
            }
        });
        LogimiseSingleton.getInstants().getLogija().info("Ruudustik tühjendatud");
    }

    private void andmeteKogumine() {

        ruudustik.getChildren().forEach(node -> {
            if (node instanceof Rectangle) {
                if (((Rectangle) node).getFill() == värvitud) {
                    andmed[ruudustik.getRowIndex(node)][ruudustik.getColumnIndex(node)] = 255;
                } else {
                    andmed[ruudustik.getRowIndex(node)][ruudustik.getColumnIndex(node)] = 0;
                }
            }
        });
        LogimiseSingleton.getInstants().getLogija().info("Ruudustiku andmed kogutud");

    }

    private void tuvastaNumber() {
        // Kogume andmed ruudustikult
        andmeteKogumine();

        // Tuvastame numbri
        Tuvastamine tuvastamine = new Tuvastamine();
        int tuvastatudNumber = tuvastamine.tuvasta(andmed);

        // Kuvame tuvastatud numbri teavitusena
        javafx.scene.control.Alert teade = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        teade.setTitle("Tuvastatud Number");
        teade.setHeaderText(null);
        teade.setContentText("Teie number on: " + tuvastatudNumber);
        teade.showAndWait();
        
        LogimiseSingleton.getInstants().getLogija().info("Tuvastatud number: " + tuvastatudNumber);
    }
}
