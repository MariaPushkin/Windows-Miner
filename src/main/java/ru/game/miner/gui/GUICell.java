package ru.game.miner.gui;

import ru.game.miner.logics.*;

import java.awt.*;

/**
 * gui ячейка
 * Дата
 * TODO доделать
 */
public class GUICell implements Cell<Graphics> {
    public boolean isBomb() { return false; }

    public boolean isSuggestBomb() { return false; }

    public boolean isSuggestEmpty() { return false; }

    public void suggestEmpty() {

    }

    public void suggestBomb() {

    }

    public void draw(Graphics paint, boolean real) {
    }
}
