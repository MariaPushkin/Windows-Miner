package ru.game.miner.gui;

import ru.game.miner.logics.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Класс событие
 * Дата
 * TODO доделать, ОБНУЛЕНИЕ МЧЕТЧИКА ПРИ РЕСТАРТЕ
 */
public class GUIAction extends BaseAction implements ActionListener, MouseListener {
    private GUIBoard board;
    private SaperLogic logic;
    private int clicks = 0;

    public GUIAction(SaperLogic logic, GUIBoard board, GeneratorBoard generator) {
        super(logic, board, generator);
        this.board = board;
        this.logic = logic;
        //this.logic.loadBoard(this.board.cells);
        this.board.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) { /*this.initGame();*/ }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int x = 0; x != this.board.cells.length; x++) {
            for (int y = 0; y != this.board.cells[0].length; y++) {
                if (e.getX() >= x * GUIBoard.PADDING && e.getX() <= x * GUIBoard.PADDING + GUIBoard.PADDING &&
                        e.getY() >= y * GUIBoard.PADDING && e.getY() <= y * GUIBoard.PADDING + GUIBoard.PADDING) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        this.clicks++;
                        if(this.clicks == 1) {
                            this.logic.putBombs(10);
                            this.logic.setDigits();
                        }
                        this.logic.suggest(x, y, false);
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        this.logic.suggest(x, y, true);
                    }
                    x = this.board.cells.length - 1;
                    y = this.board.cells[0].length - 1;
                }
            }
        }
    this.board.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
