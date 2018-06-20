import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


// Класс, отрисовывающий все что нужно на Canvas
class Drawer {

    private GraphicsContext graphicsContext;

    //Диаметр камня
    private static final int ROCK_SIZE = 35;
    static boolean gameOver = false;

    Drawer(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    void drawField() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, Field.FIELD_SIZE, Field.FIELD_SIZE);
        graphicsContext.setLineWidth(1);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setStroke(Color.WHITE);

        // Отрисовываем вертикальные и горизонтальные линии смещая их вправо,
        // чтобы затем можно было спокойно позиционировать клик на пересечениях
        for (int x = 0; x < 16; x++) {
            int realX = x * ROCK_SIZE + 17;
            drawLine(realX, 0);
        }
        for (int y = 0; y < 16; y++) {
            int realY = y * ROCK_SIZE + 17;
            drawLine(0, realY);
        }
    }

    // Вспомогательный класс для отрисовки линий
    private void drawLine(int x, int y) {
        graphicsContext.moveTo(x, y);
        graphicsContext.lineTo(x == 0 ? Field.FIELD_SIZE : x, y == 0 ? Field.FIELD_SIZE : y);
        graphicsContext.stroke();
    }

    void drawRock(boolean isWhite, int x, int y) {
        Color checkerColor = isWhite ? Color.WHITE : Color.DARKGRAY;
        x *= ROCK_SIZE;
        y *= ROCK_SIZE;

        graphicsContext.setFill(checkerColor);
        graphicsContext.fillOval(x, y, ROCK_SIZE, ROCK_SIZE);
    }

    void drawWinWindow(boolean whiteWin) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(50, 100, 400, 200);
        graphicsContext.setStroke(Color.GRAY);
        graphicsContext.setLineWidth(5);
        graphicsContext.strokeRect(50, 100, 400, 200);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setFont(Font.font(20));
        graphicsContext.fillText((whiteWin ? "Белые" : "Черные") + " выиграли!\n" +
                "Нажмите в любом месте, \nчтобы начать новую игру", 250, 150);
        gameOver = true;
    }

}