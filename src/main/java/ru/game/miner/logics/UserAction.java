package ru.game.miner.logics;

/**
 * Интерфейс - действия пользователя
 * Дата 1.12.17
 */
public interface UserAction {
    /**
     * Запустить игру
     */
    void initGame();

    /**
     * Выбор ячейки
     * @param x
     * @param y
     * @param bomb предположение, что в ячейке
     */
    void select(int x, int y, boolean bomb);
}
