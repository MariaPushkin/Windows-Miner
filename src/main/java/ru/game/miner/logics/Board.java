package ru.game.miner.logics;

/**
 * Интерфейс- игровое поле
 * Дата 01.12.17
 */
public interface Board {

    /**
     * Рисует доску по входному массиву ячеек
     * @param cells
     */
    void drawBoard(Cell[][] cells);

    /**
     * Рисует ячейку
     * @param x позиция по горизонтали
     * @param y позиция по вертикали
     */
    void drawCell(int x, int y);

    /**
     * Рисует взрыв всех бомб
     */
    void drawBang();

    /**
     * Рисует поздравление при выигрыше игры
     */
    void drawCongratulate();
}
