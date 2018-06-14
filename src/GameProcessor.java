import java.util.Arrays;

// Класс, занимающийся проверкой на победителя
class GameProcessor {

    private int[][] rocks;
    private boolean whiteWin;

    GameProcessor(int[][] rocks) {
        this.rocks = rocks;
    }

    boolean checkWin() {
        // Проходим все вертикали
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 11; j++) {
                // Если пять камней подряд одинаковые, то смотрим,
                // кто выиграл и говорим, что кто-то в принципе выиграл
                if (equalsNums(rocks[i][j], rocks[i][j + 1], rocks[i][j + 2], rocks[i][j + 3], rocks[i][j + 4]) &&
                        rocks[i][j] != -1) {
                    whiteWin = rocks[i][j] != 1;
                    return true;
                }
            }
        }
        // Проходим все горизонтали
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 15; j++) {
                if (equalsNums(rocks[i][j], rocks[i + 1][j], rocks[i + 2][j], rocks[i + 3][j], rocks[i + 4][j]) &&
                        rocks[i][j] != -1) {
                    whiteWin = rocks[i][j] != 1;
                    return true;
                }
            }
        }

        // Проходим все возможные левые и правые диагонали
        // Главная диагональ -- [x][y], побочная -- [x][len - y - 1], где len -- ширина/длина матрицы
        for (int dx = -7; dx < 8; dx++) {
            for (int x = 0; x < 11; x++) {
                for (int y = 0; y < 11; y++) {
                    int imageX = x + dx;
                    if (x == y && imageX > 0 && imageX < 11) {
                        if (equalsNums(rocks[imageX][y], rocks[imageX + 1][y + 1], rocks[imageX + 2][y + 2],
                                rocks[imageX + 3][y + 3], rocks[imageX + 4][y + 4])
                                && rocks[imageX][y] != -1) {
                            whiteWin = rocks[imageX][y] != 1;
                            return true;
                        }
                    }
                    if (x == (15 - y - 1) && imageX > 4 && imageX < 11) {
                        if (equalsNums(rocks[imageX][15 - y - 1], rocks[imageX - 1][15 - y],
                                rocks[imageX - 2][15 - y + 1], rocks[imageX - 3][15 - y + 2],
                                rocks[imageX - 4][15 - y + 3])
                                && rocks[imageX][15 - y - 1] != -1) {
                            whiteWin = rocks[imageX][15 - y - 1] != 1;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Дополнительный метод для проверки одинаковости подряд идущих камней
    // Принимает на вход int... (аналог int[])
    private boolean equalsNums(int... nums) {
        // Сравниваем все элементы массива на соответствие первому элементу
        return Arrays.stream(nums).noneMatch(num -> num != nums[0]);
    }

    boolean isWhiteWin() {
        return whiteWin;
    }
}
