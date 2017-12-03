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
    private final GUIBoard board = new GUIBoard();

    public GameWindow() {
        super("САПЕР");
        this.setLayout(new BorderLayout());
        this.setSize(300,300);
        this.add(board, BorderLayout.CENTER);
        board.setBorder(new EmptyBorder(10,10,10,10));
        controlPanel.setLayout(new GridLayout(4,1,5,5));
        this.add(controlPanel, BorderLayout.EAST);
        final JButton easyLevel = new JButton("Тестовый");
        final JButton notSoEasyLevel = new JButton("Простой");
        final JButton normalLevel = new JButton("Нормальный");
        final JButton hardLevel = new JButton("Сложный");
        easyLevel.addActionListener(
                new GUIAction(
                        new Easy(), board,
                        new GeneratorBoard() {
                            @Override
                            public Cell[][] generate() {
                                return new Cell[][] {
                                        {new GUICell(true,0,0), new GUICell(false,1,0)},
                                        {new GUICell(true, 0,1), new GUICell(false,1,1)}
                                };
                            }
                        }
                )
        );

        easyLevel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                GameWindow.this.setSize(GUIBoard.PADDING * 2 + easyLevel.getSize().width + 20,GUIBoard.PADDING * 3 + 20);
                GameWindow.this.setResizable(false);
            }
        });
        controlPanel.add(easyLevel);
        controlPanel.add(notSoEasyLevel);
        controlPanel.add(normalLevel);
        controlPanel.add(hardLevel);
    }
}
