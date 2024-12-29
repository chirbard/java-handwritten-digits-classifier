import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

public class Controller {

    private static final int ruudustikuSuurus = 28; // 28x28 ruudustik
    private static final int ruuduSuurus = 20;
    private static final Color algVärv = Color.WHITE; // Default cell color
    private static final Color värvitud = Color.BLACK; // Highlighted cell color
    private static final Color piiriVärv = Color.LIGHTGRAY; // Border color

    private int[][] andmed = new int[ruudustikuSuurus][ruudustikuSuurus];

    @FXML
    private GridPane gridPane; // This field is linked to the GridPane in the FXML file

    public void initialize() {
        LogimiseSingleton.getInstants().getLogija().info("Ruudustiku loomine algas");

        if (gridPane == null) {
            LogimiseSingleton.getInstants().getLogija().severe("Ruudustik pole initsialiseeritud");
            System.out.println("Ruudustik pole initsialiseeritud");
            return;
        }

        // Dynamically create the 28x28 grid of rectangles
        for (int rida = 0; rida < ruudustikuSuurus; rida++) {
            for (int tulp = 0; tulp < ruudustikuSuurus; tulp++) {

                Rectangle ruut = new Rectangle(ruuduSuurus, ruuduSuurus, algVärv);
                ruut.setStroke(piiriVärv);

                // Handle mouse events for the rectangles
                ruut.setOnMousePressed(this::onMousePressed);
                ruut.setOnMouseDragEntered(this::onMouseDragEntered);
                ruut.setOnDragDetected(this::onDragDetected);

                // Add the rectangle to the GridPane at the specified row and column
                gridPane.add(ruut, tulp, rida);
            }
        }

        // Add a button to clear the grid
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearGrid());
        gridPane.add(clearButton, 0, ruudustikuSuurus + 1);
        Button ennustusButton = new Button("Ennusta");
        ennustusButton.setOnAction(e -> ennusta());
        gridPane.add(ennustusButton, 1, ruudustikuSuurus + 1);

        LogimiseSingleton.getInstants().getLogija().info("Ruudustik loodud");

    }

    private void onMousePressed(MouseEvent e) {
        Rectangle rect = (Rectangle) e.getSource();
        rect.setFill(värvitud);
        System.out.println("Mouse pressed at: " + GridPane.getColumnIndex(rect) + ", " + GridPane.getRowIndex(rect));
        LogimiseSingleton.getInstants().getLogija()
                .info("Hiirevajutus koordinaatidel: " + GridPane.getColumnIndex(rect) + ", "
                        + GridPane.getRowIndex(rect));
    }

    private void onMouseDragEntered(MouseEvent e) {
        Rectangle rect = (Rectangle) e.getSource();
        rect.setFill(värvitud);
        System.out
                .println("Mouse drag entered at: " + GridPane.getColumnIndex(rect) + ", " + GridPane.getRowIndex(rect));
        LogimiseSingleton.getInstants().getLogija()
                .info("Hiire lohistamine koordinaatidel: " + GridPane.getColumnIndex(rect) + ", "
                        + GridPane.getRowIndex(rect));
    }

    private void onDragDetected(MouseEvent e) {
        Rectangle rect = (Rectangle) e.getSource();
        rect.startFullDrag();
        System.out.println("Drag detected at: " + GridPane.getColumnIndex(rect) + ", " + GridPane.getRowIndex(rect));
        LogimiseSingleton.getInstants().getLogija()
                .info("Lohistamine koordinaatidel: " + GridPane.getColumnIndex(rect) + ", "
                        + GridPane.getRowIndex(rect));
    }

    private void clearGrid() {
        gridPane.getChildren().forEach(node -> {
            if (node instanceof Rectangle) {
                ((Rectangle) node).setFill(algVärv);
            }
        });
        LogimiseSingleton.getInstants().getLogija().info("Ruudustik tühjendatud");
    }

    private void getData() {
        // Get the data from the grid
        // Iterate through the gridPane and get the color of each rectangle
        // Store the data in a 2D array or other data structure

        gridPane.getChildren().forEach(node -> {
            if (node instanceof Rectangle) {
                if (((Rectangle) node).getFill() == värvitud) {
                    andmed[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = 255;
                } else {
                    andmed[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = 0;
                }
            }
        });
        LogimiseSingleton.getInstants().getLogija().info("Ruudustiku jaoks andmed kogutud");

    }

    private void ennusta() {
        // Get the data from the grid
        getData();

        Tuvastamine tuvastamine = new Tuvastamine();
        int ennustus = tuvastamine.tuvasta(andmed);

        System.out.println("Ennustus: " + ennustus);
        LogimiseSingleton.getInstants().getLogija().info("Ennustus: " + ennustus);
    }
}
