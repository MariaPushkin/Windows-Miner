package ru.game.miner.gui;

import ru.game.miner.logics.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * gui реализация доски
 */
public class GUIBoard extends JPanel implements Board {
    public static final int PADDING = 30;
    public Cell<Graphics2D>[][] cells;
    private BufferedImage image;
    private boolean bang = false;
    private boolean finish = false;
    //количество бомб
    private int bombs;

    public GUIBoard(int bombs) {
        super();
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/blank.png"));;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bombs = bombs;
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
                    g2.setColor(Color.BLACK);
                    g2.drawRect(x * PADDING, y * PADDING , PADDING, PADDING);
                    g2.drawImage(image, x * GUIBoard.PADDING , y * GUIBoard.PADDING,
                            GUIBoard.PADDING, GUIBoard.PADDING, null);
                    if(this.bang) {
                        cells[x][y].draw(g2, true, true);
                    } else if (this.finish) {
                        cells[x][y].draw(g2, true, false);
                    }
                    else {
                        cells[x][y].draw(g2, false, false);
                    }
                }
            }
        }
    }

    public void drawBoard(Cell[][] cells){
        this.cells = cells;
        this.repaint();
    }

    public void drawCell(int x, int y) { this.repaint(); }

    /**
     * Выводит диалоговое окно с сообщением о проигрыше
     */
    public  void drawBang() {
        this.bang = true;
        JOptionPane.showMessageDialog(null,
                "ВЫ ПРОИГРАЛИ!!!",
                "САПЕР",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Выводит диалоговое окно с сообщением о выигрыше
     */
    public void drawCongratulate() {
        this.finish = true;
        JOptionPane.showMessageDialog(null,
                "ПОБЕДА!!!",
                "САПЕР",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Возвращает все флаги в начальное состояние
     */
    public void reset() {
        this.finish = false;
        this.bang = false;
    }

    /**
     * Открывает все пустые ячейки, расположенные рядом с ячейкой, которую открыл пользватель, если она пустая
     * Создается массив temp, содержащий ячейку и ее позицию на доске.
     * Для каждой ячейке в массиве рассматриваются ее ближайшие соседи. Открываются все пустые из них
     * (по диагонале только если есть цифра, если ячейка помечена пользователем как бомба, она не открывется).
     * Польностью пустые ячейки (не содаржащие бомб и цифр добавляются в массив.
     * @param x координата x выбранной пользователем ячейки
     * @param y координата y выбранной пользователем ячейки
     */
    public void openNearEmpty( int x, int y) {
        if(this.cells[x][y].getBombNum() == 0) {
            class CellXY {
                public Cell<Graphics2D> cell;
                public int x;
                public int y;

                CellXY(Cell<Graphics2D> cell, int x, int y) {
                    this.cell = cell;
                    this.x = x;
                    this.y = y;
                }

                @Override
                public boolean equals(Object o) {
                    if (this == o) return true;
                    if (o == null || getClass() != o.getClass()) return false;

                    CellXY cellXY = (CellXY) o;

                    if (x != cellXY.x) return false;
                    return y == cellXY.y;
                }

                @Override
                public int hashCode() {
                    int result = x;
                    result = 31 * result + y;
                    return result;
                }
            }
            ArrayList<CellXY> temp = new ArrayList<CellXY>();
            CellXY t = new CellXY(this.cells[x][y], x, y);
            temp.add(t);
            int i = 0;
            while (i < temp.size()) {
                int tx = temp.get(i).x;
                int ty = temp.get(i).y;
                    if (tx > 0) {
                        if (!this.cells[tx - 1][ty].isBomb() && !this.cells[tx - 1][ty].isSuggestBomb()) {
                            this.cells[tx - 1][ty].suggestEmpty();
                            if (this.cells[tx - 1][ty].getBombNum() == 0) {
                                CellXY t2 = new CellXY(this.cells[tx - 1][ty], tx - 1, ty);
                                if (!temp.contains(t2)) {
                                    temp.add(t2);
                                }
                            }
                        }
                        if (ty > 0) {
                            if (!this.cells[tx - 1][ty - 1].isBomb() && !this.cells[tx - 1][ty - 1].isSuggestBomb()
                                    && this.cells[tx - 1][ty - 1].getBombNum() > 0) {
                                this.cells[tx - 1][ty - 1].suggestEmpty();
                            }
                        }
                        if (ty < this.cells[0].length - 1) {
                            if (!this.cells[tx - 1][ty + 1].isBomb() && !this.cells[tx - 1][ty + 1].isSuggestBomb()
                                    && this.cells[tx - 1][ty + 1].getBombNum() > 0) {
                                this.cells[tx - 1][ty + 1].suggestEmpty();
                            }
                        }
                    }
                    if (tx < this.cells.length - 1) {
                        if (!this.cells[tx + 1][ty].isBomb() && !this.cells[tx + 1][ty].isSuggestBomb()) {
                            this.cells[tx + 1][ty].suggestEmpty();
                            if (this.cells[tx + 1][ty].getBombNum() == 0) {
                                CellXY t2 = new CellXY(this.cells[tx + 1][ty], tx + 1, ty);
                                if (!temp.contains(t2)) {
                                    temp.add(t2);
                                }
                            }
                        }
                        if (ty > 0) {
                            if (!this.cells[tx + 1][ty - 1].isBomb() && !this.cells[tx + 1][ty - 1].isSuggestBomb()
                                    && this.cells[tx + 1][ty - 1].getBombNum() > 0) {
                                this.cells[tx + 1][ty - 1].suggestEmpty();
                            }
                        }
                        if (ty < this.cells[0].length - 1) {
                            if (!this.cells[tx + 1][ty + 1].isBomb() && !this.cells[tx + 1][ty + 1].isSuggestBomb()
                                    && this.cells[tx + 1][ty + 1].getBombNum() > 0) {
                                this.cells[tx + 1][ty + 1].suggestEmpty();
                            }
                        }
                    }
                    if (ty > 0) {
                        if (!this.cells[tx][ty - 1].isBomb() && !this.cells[tx][ty - 1].isSuggestBomb()) {
                            this.cells[tx][ty - 1].suggestEmpty();
                            if (this.cells[tx][ty - 1].getBombNum() == 0) {
                                CellXY t2 = new CellXY(this.cells[tx][ty - 1], tx, ty - 1);
                                if (!temp.contains(t2)) {
                                    temp.add(t2);
                                }
                            }
                        }
                    }
                    if (ty < this.cells[0].length - 1) {
                        if (!this.cells[tx][ty + 1].isBomb() && !this.cells[tx][ty + 1].isSuggestBomb()) {
                            this.cells[tx][ty + 1].suggestEmpty();
                            if (this.cells[tx][ty + 1].getBombNum() == 0) {
                                CellXY t2 = new CellXY(this.cells[tx][ty + 1], tx, ty + 1);
                                if (!temp.contains(t2)) {
                                    temp.add(t2);
                                }
                            }
                        }
                    }
                i++;
                }
        }
    }

    @Override
    public int getBombsAmount() { return this.bombs; }

    public boolean isFinish() { return this.finish; }
}
