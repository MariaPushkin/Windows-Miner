package ru.game.miner.console;

import ru.game.miner.logics.*;

/**
 * Поле игры, консольная реализация
 * TODO: сделать
 * Дата
 */
public class ConsoleBoard implements Board {
    private Cell[][] cells;

    public void drawBoard(Cell[][] cells){
        this.cells = cells;
        this.redraw(false);
    }

    public void drawCell(int x, int y) {

    }

    public void drawBang() {

    }

    public void drawCongratulate() {

    }

    private void redraw(boolean real) {
        
    }
}
