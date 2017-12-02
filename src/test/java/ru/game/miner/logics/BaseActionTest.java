package ru.game.miner.logics;

import org.junit.Test;
import ru.game.miner.console.ConsoleBoard;
import ru.game.miner.console.ConsoleCell;
import ru.game.miner.logics.levels.Easy;

public class BaseActionTest {
    final BaseAction action = new BaseAction(
            new Easy(), new ConsoleBoard(),
            () -> new Cell[][] {
                    {new ConsoleCell(true), new ConsoleCell(false)},
                    {new ConsoleCell(true), new ConsoleCell(false)}
            }
    );

    @Test
    public void successGame() {
        action.initGame();
        action.select(0,0,true);
        action.select(1,0,true);
        action.select(0,1,false);
        action.select(1,1,false);
    }

    @Test
    public void failureGame() {
        action.initGame();
        action.select(0,0,true);
        action.select(1,0,false);
    }
}