import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

// Класс, хранящий матрицу из ячеек, где -1 -- пустая ячейка,
// 0 -- белые, 1 -- черные
class Field {

    private int[][] rocks;
    private Drawer drawer;
    private GameProcessor gameProcessor;

    // Первыми ходят черные
    private boolean isBlack = true;
    private boolean firstMove = true;
    static final int FIELD_SIZE = 525;

    Field(GraphicsContext graphicsContext) {
        drawer = new Drawer(graphicsContext);

        // Отрисовываем поле и заполняем матрицу -1
        drawer.drawField();
        rocks = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                rocks[i][j] = -1;
            }
        }
        gameProcessor = new GameProcessor(rocks);
    }

    void canvasListener(MouseEvent event) {
        if (Drawer.gameOver) {
            Drawer.gameOver = false;
            Main.newGame();
        }

        // Нормализуем позицию клика, чтобы иметь конкретное пересечение
        int x = (int) event.getX() / 35;
        int y = (int) event.getY() / 35;

        // По правилам, первый ход должен осуществляться из центра
        if (firstMove && (x > 9 || x < 7) && (y > 9 || y < 7)) return;

        // В случае, если совершен клик в не пустую клетку
        if (rocks[x][y] != -1) return;

        // Заполняем нужную клетку обозначением камня
        rocks[x][y] = isBlack ? 1 : 0;

        // Смена хода
        isBlack = !isBlack;
        firstMove = false;
        drawer.drawRock(isBlack, x, y);

        // Проверяем, не выиграл ли кто
        if (gameProcessor.checkWin()) drawer.drawWinWindow(gameProcessor.isWhiteWin());
    }
}