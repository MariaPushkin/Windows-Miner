package ru.game.miner.logics;

/**
 * Базовые действия пользователя
 */
public class BaseAction implements UserAction {
    private final GeneratorBoard generator;
    private final Board board;
    private final SaperLogic logic;
    private int bombsCounter = 0;

    public BaseAction(final SaperLogic logic, final Board board, final GeneratorBoard generator) {
        this.generator = generator;
        this.board = board;
        this.logic = logic;
    }

    public void initGame() {
        final Cell[][] cells = generator.generate();
        this.board.drawBoard(cells);
        this.logic.loadBoard(cells);
    }

    public void select(int x, int y, boolean bomb) {
        this.logic.suggest(x, y, bomb);
        board.drawCell(x, y);
        if(this.logic.shouldBang(x, y)) {
            this.board.drawBang();
        }
        if(this.logic.finish(this.board.getBombsAmount())) {
            this.board.drawCongratulate();
        }
    }
}
