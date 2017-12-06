package ru.game.miner.gui;

import ru.game.miner.logics.Cell;
import ru.game.miner.logics.GeneratorBoard;
import ru.game.miner.logics.levels.Easy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Главный класс
 */
public class Main {


    public static void main(String[] arg) {
        //выполнение строго после всех действий пользователя с интерфейсом
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameWindow wnd = new GameWindow();
                centre(wnd);
                wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                /*wnd.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        closePerfom(wnd);
                    }
                });*/
                wnd.setVisible(true);
            }
        });
    }

    public static void centre(Window w) {
        Dimension us = w.getSize();
        Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
        int newX = (them.width - us.width)/2;
        int newY = (them.height - us.height)/2;
        w.setLocation(newX, newY);
    }

    public static void closePerfom(JFrame frame) {
        frame.setVisible(false);
        frame.dispose();
        System.exit(0);
    }

}
