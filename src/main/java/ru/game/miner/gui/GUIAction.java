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
 * TODO доделать
 */
public class GUIAction extends BaseAction implements ActionListener, MouseListener {
    private GUIBoard board;

    public GUIAction(SaperLogic logic, GUIBoard board, GeneratorBoard generator) {
        super(logic, board, generator);
        this.board = board;
        this.board.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) { this.initGame(); }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getX() >= 0 && e.getX() <= 49) {
            this.board.cells[0][0].suggestBomb();
        }
        board.repaint();
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
