package ru.game.miner.gui;

import ru.game.miner.logics.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * gui ячейка
 * Дата
 * TODO доделать
 */
public class GUICell implements Cell<Graphics2D> {
    /**
     * Цифра в ячейке, количество бомб в окрестности
     */
    int bombNum;
    private boolean bomb;
    private boolean suggestBomb = false;
    private boolean suggestEmpty = false;
    private BufferedImage bombImage;
    private BufferedImage cellImage;
    private BufferedImage explosionImage;
    private BufferedImage flagImage;
    private BufferedImage blankImage;
    private BufferedImage digitImage;
    private int posX;
    private int posY;

    public GUICell(boolean bomb, int x, int y) {
        this.bomb = bomb;
        try {
            bombImage = ImageIO.read(getClass().getResourceAsStream("/bomb.png"));
            cellImage = ImageIO.read(getClass().getResourceAsStream("/cell.png"));
            explosionImage = ImageIO.read(getClass().getResourceAsStream("/explosion.png"));
            flagImage = ImageIO.read(getClass().getResourceAsStream("/flag.png"));
            blankImage = ImageIO.read(getClass().getResourceAsStream("/blank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.posX = x;
        this.posY = y;
    }

    @Override
    public void setBomb() {this.bomb = true;}

    @Override
    public void addBombNum(int num) {
        this.bombNum = num;
        if(num > 0) {
            String img = "/" + num + ".png";
            try {
                digitImage = ImageIO.read(getClass().getResourceAsStream(img));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getBombNum() {return this.bombNum;}

    @Override
    public boolean isBomb() { return this.bomb; }

    @Override
    public boolean isSuggestBomb() { return this.suggestBomb; }

    @Override
    public boolean isSuggestEmpty() { return this.suggestEmpty; }

    @Override
    public void suggestEmpty() {
        this.suggestEmpty = true;
        this.suggestBomb = false;
    }

    @Override
    public void suggestBomb() {
        if(!this.suggestEmpty) {
            this.suggestBomb = !this.suggestBomb;
        }
    }

    @Override
    public void draw(Graphics2D paint, boolean real, boolean bang) {
        if(real) {
            if(this.isBomb()) {
                paint.drawImage(bombImage, this.posX * GUIBoard.PADDING + 3, this.posY * GUIBoard.PADDING,
                        GUIBoard.PADDING, GUIBoard.PADDING, null);
                if (bang) {
                    paint.drawImage(explosionImage, this.posX * GUIBoard.PADDING, this.posY * GUIBoard.PADDING,
                          GUIBoard.PADDING, GUIBoard.PADDING, null);
                }
            } else {
                if (this.bombNum == 0) {
                    paint.drawImage(blankImage, this.posX * GUIBoard.PADDING, this.posY * GUIBoard.PADDING,
                            GUIBoard.PADDING, GUIBoard.PADDING, null);
                }else {
                    paint.drawImage(digitImage, this.posX * GUIBoard.PADDING, this.posY * GUIBoard.PADDING,
                            GUIBoard.PADDING, GUIBoard.PADDING, null);
                }
            }
        } else {
            if(this.suggestBomb) {
                paint.drawImage(flagImage, this.posX * GUIBoard.PADDING, this.posY * GUIBoard.PADDING,
                        GUIBoard.PADDING, GUIBoard.PADDING, null);
            } else if(this.suggestEmpty) {
                if (this.bombNum == 0) {
                    paint.drawImage(blankImage, this.posX * GUIBoard.PADDING, this.posY * GUIBoard.PADDING,
                            GUIBoard.PADDING, GUIBoard.PADDING, null);
                }else {
                    paint.drawImage(digitImage, this.posX * GUIBoard.PADDING, this.posY * GUIBoard.PADDING,
                            GUIBoard.PADDING, GUIBoard.PADDING, null);
                }
            } else {
                paint.drawImage(cellImage, this.posX * GUIBoard.PADDING + 2, this.posY * GUIBoard.PADDING + 2,
                        GUIBoard.PADDING - 4, GUIBoard.PADDING - 4, null);
            }
        }
    }
}
