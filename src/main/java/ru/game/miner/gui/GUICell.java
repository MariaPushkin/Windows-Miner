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
    private boolean bomb;
    private boolean suggestBomb = false;
    private boolean suggestEmpty = false;
    private BufferedImage bombImage;
    private BufferedImage cellImage;
    private BufferedImage explosionImage;
    private BufferedImage flagImage;
    private int posX;
    private int posY;

    public GUICell(boolean bomb, int x, int y) {
        this.bomb = bomb;
        try {
            bombImage = ImageIO.read(getClass().getResourceAsStream("/bomb.png"));
            cellImage = ImageIO.read(getClass().getResourceAsStream("/cell.png"));
            explosionImage = ImageIO.read(getClass().getResourceAsStream("/explosion.png"));
            flagImage = ImageIO.read(getClass().getResourceAsStream("/flag.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.posX = x;
        this.posY = y;
    }

    public boolean isBomb() { return this.bomb; }

    public boolean isSuggestBomb() { return this.suggestBomb; }

    public boolean isSuggestEmpty() { return this.suggestEmpty; }

    public void suggestEmpty() {
        this.suggestEmpty = true;
        this.suggestBomb = false;
    }

    public void suggestBomb() {
        this.suggestBomb = true;
        this.suggestEmpty = false;
    }

    public void draw(Graphics2D paint, boolean real) {
        if(real) {
            if(this.isBomb()) {
                paint.drawImage(bombImage, this.posX * GUIBoard.PADDING + 2, this.posY * GUIBoard.PADDING + 2,
                        GUIBoard.PADDING, GUIBoard.PADDING, null);
            } else {
                paint.drawImage(cellImage, this.posX * GUIBoard.PADDING + 2, this.posY * GUIBoard.PADDING + 2,
                        GUIBoard.PADDING, GUIBoard.PADDING, null);
            }
        } else {
            if(this.suggestBomb) {
                paint.drawImage(flagImage, this.posX * GUIBoard.PADDING + 5, this.posY * GUIBoard.PADDING - 5,
                        GUIBoard.PADDING, GUIBoard.PADDING, null);
            } else if(this.suggestEmpty) {
                paint.drawImage(cellImage, this.posX * GUIBoard.PADDING + 2, this.posY * GUIBoard.PADDING + 2,
                        GUIBoard.PADDING, GUIBoard.PADDING, null);
            } else {
                paint.drawImage(cellImage, this.posX * GUIBoard.PADDING + 2, this.posY * GUIBoard.PADDING +2,
                        GUIBoard.PADDING, GUIBoard.PADDING, null);
            }
        }
    }
}
