import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// Точка входа в приложение
public class Main extends Application {

    private static Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) {
        newGame();
    }

    static void newGame() {
        Drawer.gameOver = false;

        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, Field.FIELD_SIZE, Field.FIELD_SIZE);

        Canvas canvas = new Canvas(Field.FIELD_SIZE, Field.FIELD_SIZE);

        Field field = new Field(canvas.getGraphicsContext2D());

        canvas.setOnMouseClicked(field::canvasListener);
        grid.add(canvas, 0, 0);

        // В случае начала новой игры
        if (stage.isShowing()) stage.close();
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}