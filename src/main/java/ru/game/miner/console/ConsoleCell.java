package ru.game.miner.console;

import ru.game.miner.logics.*;
import java.io.PrintStream;

/**
 * Класс консольная ячейка
 * Дата 2.12.17
 */
public class ConsoleCell implements Cell<PrintStream> {
    private boolean bomb;
    private boolean suggestBomb = false;
    private boolean suggestEmpty = false;

    public ConsoleCell(boolean bomb) { this.bomb = bomb; }

    @Override
    public void setBomb() {this.bomb = true;}

    @Override
    public void addBombNum(int num) {}

    @Override
    public boolean isBomb() { return this.bomb; }

    @Override
    public boolean isSuggestBomb() { return this.suggestBomb; }

    @Override
    public boolean isSuggestEmpty() { return this.suggestEmpty; }

    @Override
    public void suggestBomb() { this.suggestBomb = true; }

    @Override
    public void suggestEmpty() { this.suggestEmpty = true; }

    @Override
    public void draw(PrintStream paint, boolean real) {
        if(real) {
            if(this.isBomb()) {
                paint.print("[*] ");
            } else {
                paint.print("[ ] ");
            }
        } else {
            if(this.suggestBomb) {
                paint.print("[?] ");
            } else if(this.suggestEmpty) {
                paint.print("[ ] ");
            } else {
                paint.print("[x] ");
            }
        }
    }
}
