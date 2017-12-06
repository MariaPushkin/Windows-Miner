package ru.game.miner.gui;

import ru.game.miner.logics.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * gui реализация доски
 * Дата
 * TODO доделать
 */
public class GUIBoard extends JPanel implements Board {
    public static final int PADDING = 30;
    public Cell<Graphics2D>[][] cells;
    private BufferedImage image;

    public GUIBoard() {
        super();
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/blank.png"));;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent((Graphics2D)graphics);
        Graphics2D g2 = (Graphics2D) graphics;
        float thickness = 2;
        g2.setStroke(new BasicStroke(thickness));
        if(this.cells != null) {
            for(int x = 0; x != cells.length; x++) {
                for(int y = 0; y != cells[0].length; y++) {
                    //Rectangle2D.Double rect = new Rectangle2D.Double(x * PADDING, y * PADDING, PADDING, PADDING);
                    g2.setColor(Color.BLACK);
                    g2.drawRect(x * PADDING, y * PADDING , PADDING, PADDING);
                    g2.drawImage(image, x * GUIBoard.PADDING , y * GUIBoard.PADDING,
                            GUIBoard.PADDING, GUIBoard.PADDING, null);
                    //g2.draw(rect);
                    cells[x][y].draw(g2,true);
                }
            }
        }
    }

    public void drawBoard(Cell[][] cells){
        this.cells = cells;
        this.repaint();
    }

    public void drawCell(int x, int y) { this.repaint(); }

    public  void drawBang() { this.repaint(); }

    public void drawCongratulate() {}
}
