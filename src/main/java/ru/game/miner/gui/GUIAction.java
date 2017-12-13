package ru.game.miner.gui;

import ru.game.miner.logics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Класс событие
 * Дата
 */
public class GUIAction extends BaseAction implements ActionListener, MouseListener {
    private GUIBoard board;
    private SaperLogic logic;
    private int clicks = 0;
    private int bombsCounter = 0;
    private JLabel bombsCounterDisplay;

    public GUIAction(SaperLogic logic, GUIBoard board, GeneratorBoard generator, JLabel bombsCounterDisplay) {
        super(logic, board, generator);
        this.board = board;
        this.logic = logic;
        this.bombsCounterDisplay = bombsCounterDisplay;
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
                            this.logic.putBombs(this.board.getBombsAmount(),x,y);
                            this.logic.setDigits();
                        }
                        this.select(x,y,false);
                        this.board.openNearEmpty(x,y);
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        if(this.board.cells[x][y].isSuggestBomb()) this.bombsCounter--;
                        else this.bombsCounter++;
                        this.select(x,y, true);
                        this.bombsCounterDisplay.setText("Мины - " + this.bombsCounter + "/" + this.board.getBombsAmount());
                    }
                }
            }
        }
        if(this.board.isFinish()) {
            this.bombsCounterDisplay.setText("Мины - " + this.board.getBombsAmount() + "/" + this.board.getBombsAmount());
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
    public void mouseExited(MouseEvent mouseEvent) { }

    /**
     * Сброс всех значений в начальное пложение
     */
    public void reset() {
        this.clicks = 0;
        this.board.reset();
        this.bombsCounter = 0;
    }
}
