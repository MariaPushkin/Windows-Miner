package ru.game.miner.logics.levels;

import ru.game.miner.logics.Cell;
import ru.game.miner.logics.SaperLogic;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Класс простой логики
 * Дата 2.12.17
 */
public class Easy implements SaperLogic {
    private Cell[][] cells;

    @Override
    public void loadBoard(Cell[][] cells) {this.cells = cells;}

    @Override
    public boolean shouldBang(int x, int y) {
        final Cell selected = this.cells[x][y];
        return selected.isBomb() && selected.isSuggestEmpty();
    }

    @Override
    public boolean finish() {
        boolean finish = false;
        for(Cell[] row : cells) {
            for(Cell cell : row) {
                finish = ((cell.isSuggestBomb() && cell.isBomb()) ||
                        (cell.isSuggestEmpty() && !cell.isBomb()));
            }
        }
        return finish;
    }

    @Override
    public void suggest(int x, int y, boolean bomb) {
        if(bomb) {
            this.cells[x][y].suggestBomb();
        } else {
            this.cells[x][y].suggestEmpty();
        }
    }

    /**
     * Расстановка бомб
     * @param bomb количество бомб
     * Дата 6.12.17
     * TODO: сделать распределение независимым от размера
     */
    @Override
    public void putBombs(int bomb, int x, int y) {
        Random r1 = new Random();
        Set<Integer> xs = new HashSet<>();
        Integer tempInt;
        while (xs.size() < bomb){
            while (xs.contains(tempInt = r1.nextInt(88)));
            if(tempInt / 10 < 9 && tempInt % 10 < 9 && tempInt / 10 != x && tempInt % 10 != y) {
                xs.add(tempInt);
            }
        }
        for (int m : xs) {
            System.out.println(m / 10);
            System.out.println(m % 10);
            this.cells[m / 10][m % 10].setBomb();
        }
    }

    @Override
    public void setDigits() {
        for (int x = 0; x != this.cells.length; x++) {
            for (int y = 0; y != this.cells[0].length; y++) {
                if(!this.cells[x][y].isBomb()) {
                    int count = 0;
                    for(int xx = x - 1; xx < x + 2 && xx < this.cells.length; xx++) {
                        for(int yy = y - 1; yy < y + 2 && yy < this.cells[0].length; yy++) {
                            if(xx == -1) xx++;
                            if(yy == -1) yy++;
                            if (this.cells[xx][yy].isBomb()) count++;
                        }
                    }
                    this.cells[x][y].addBombNum(count);
                }
            }
        }
    }
}
