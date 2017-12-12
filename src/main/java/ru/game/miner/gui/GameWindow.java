package ru.game.miner.gui;

import ru.game.miner.logics.Cell;
import ru.game.miner.logics.GeneratorBoard;
import ru.game.miner.logics.levels.Easy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Игровое окно
 * Дата 3.12.17
 * TODO добавить счетчик бомб и время
 */
public class GameWindow extends JFrame {
    private final JPanel controlPanel = new JPanel();
    private final GUIBoard board = new GUIBoard(10);

    public GameWindow() {
        super("САПЕР");
        this.setLayout(new BorderLayout());
        this.setSize(GUIBoard.PADDING * 9 + 6,GUIBoard.PADDING * 9 + 70);
        this.add(board, BorderLayout.CENTER);
        board.setBorder(new EmptyBorder(10,10,10,10));
        controlPanel.setLayout(new FlowLayout());
        this.add(controlPanel, BorderLayout.PAGE_END);
        final JButton game = new JButton("Старт");
        JLabel bombsAmount = new JLabel("Мины - 0/0");

        game.addActionListener(
                new GUIAction(
                        new Easy(), board,
                        new GeneratorBoard() {
                            @Override
                            public Cell[][] generate() {
                                Cell[][] cells = new Cell[9][9];
                                for (int x = 0; x < 9; x++) {
                                    for (int y = 0; y < 9; y++) {
                                        cells[x][y] = new GUICell(false,x,y);
                                    }
                                }
                                return cells;
                            }
                        }, bombsAmount
                ) {
                    public void actionPerformed(ActionEvent e)
                    {
                        game.setText("Рестрат");
                        this.initGame();
                        this.reset();
                        bombsAmount.setText("Мины - " + 0 + "/" + 10);
                    }
                }
        );
        controlPanel.add(bombsAmount);
        controlPanel.add(game);
        GameWindow.this.setResizable(false);
    }
}
