package ru.game.miner.logics;

/**
 * Логика игры
 * Дата 1.12.17
 */
public interface SaperLogic {
    /**
     * Загрузка поля
     * @param cells
     */
    void loadBoard(Cell[][] cells);

    /**
     * Проверка необходимости взрыва
     * Взрыв на 4 раз (?)
     * @param x
     * @param y
     * @return true - если пользователь попал на бомбу, false - пустая клетка
     */
    boolean shouldBang(int x, int y);

    /**
     * Проверка окончена ли игра
     * @return
     */
    boolean finish();

    /**
     * Отметить поле предположения пользователя
     * @param x
     * @param y
     * @param bomb
     */
    void suggest(int x, int y, boolean bomb);
}
