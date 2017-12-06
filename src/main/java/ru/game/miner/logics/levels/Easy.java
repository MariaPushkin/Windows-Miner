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
        return selected.isBomb() && !selected.isSuggestBomb();
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
    public void putBombs(int bomb) {
        Random r1 = new Random();
        Set<Integer> xs = new HashSet<>();
        Integer tempInt;
        while (xs.size() < bomb){
            while (xs.contains(tempInt = r1.nextInt(88)));
            if(tempInt / 10 < 9 && tempInt % 10 < 9) {
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
        this.cells[0][0].addBombNum(1);
    }
}
