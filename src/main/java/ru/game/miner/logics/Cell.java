package ru.game.miner.logics;

/**
 * Интерфейс - игровая йчейка
 * @param <T>
 * Дата 1.12.17
 */
public interface Cell<T> {
    boolean isBomb();

    /**
     * Пользователь предположил, что это бомба/пустая клетка
     * @return
     */
    boolean isSuggestBomb();
    boolean isSuggestEmpty();

    /**
     * Установка значений
     */
    void suggectEmpty();
    void suggestBomb();

    /**
     * Рисует клетку
     */
    void draw(T paint, boolean real);
}
