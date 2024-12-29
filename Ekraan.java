import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Ekraan extends Application {

    private static final int GRID_SIZE = 28;  // 28x28 grid
    private static final int CELL_SIZE = 20;  // Size of each cell in the grid

    @Override
    public void start(Stage primaryStage) {
        // Create a GridPane to hold the rectangles (28x28)
        GridPane grid = new GridPane();
        
        // Create a 28x28 grid of rectangles (20px by 20px)
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE, Color.WHITE); // Start with white cells
                rect.setStroke(Color.BLACK); // Optional: add black borders around cells
                
                // Add the rectangle to the GridPane
                grid.add(rect, col, row);
                
                // Add mouse event listeners for coloring
                rect.setOnMousePressed(e -> onMouseEvent(e, rect));
                rect.setOnMouseDragged(e -> onMouseEvent(e, rect));
            }
        }

        // Set up the scene
        Scene scene = new Scene(grid, GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE);
        primaryStage.setTitle("28x28 Grid Coloring");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Handle mouse events to change the color of the cell
    private void onMouseEvent(MouseEvent e, Rectangle rect) {
        if (e.isPrimaryButtonDown()) {  // Only color if the left mouse button is pressed
            rect.setFill(Color.BLACK); // Change the color of the cell to black
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
