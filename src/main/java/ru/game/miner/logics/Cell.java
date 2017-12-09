package ru.game.miner.logics;

/**
 * Интерфейс - игровая йчейка
 * @param <T>
 * Дата 1.12.17
 */
public interface Cell<T> {
    boolean isBomb();

    void setBomb();

    void addBombNum(int num);

    int getBombNum();

    /**
     * Пользователь предположил, что это бомба/пустая клетка
     * @return
     */
    boolean isSuggestBomb();
    boolean isSuggestEmpty();

    /**
     * Установка значений
     */
    void suggestEmpty();
    void suggestBomb();

    /**
     * Рисует клетку
     */
    void draw(T paint, boolean real, boolean bang);
}
