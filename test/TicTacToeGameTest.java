import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for TicTacToeGame.
 *
 * @author Donathan Chuon
 */
class TicTacToeGameTest
{
    private TicTacToeGame game;

    @BeforeEach
    void setUp()
    {
        game = new TicTacToeGame();
    }

    @Test
    void xGoesFirstAndPlayersAlternate()
    {
        assertEquals("X", game.getCurrentPlayer());
        assertEquals(MoveResult.CONTINUE, game.makeMove(0, 0));
        assertEquals("O", game.getCurrentPlayer());
        assertEquals(MoveResult.CONTINUE, game.makeMove(1, 1));
        assertEquals("X", game.getCurrentPlayer());
        assertEquals(2, game.getMoveCount());
    }

    @Test
    void invalidMoveKeepsTheSamePlayer()
    {
        game.makeMove(1, 1);
        assertEquals(MoveResult.INVALID, game.makeMove(1, 1));
        assertEquals("O", game.getCurrentPlayer());
        assertEquals(1, game.getMoveCount());
        assertEquals("X", game.getBoard().getMark(1, 1));
    }

    @Test
    void xWinsTopRowOnFifthMove()
    {
        game.makeMove(0, 0);
        game.makeMove(1, 0);
        game.makeMove(0, 1);
        game.makeMove(1, 1);
        assertEquals(MoveResult.WIN, game.makeMove(0, 2));
        assertEquals("X", game.getCurrentPlayer());
        assertEquals(5, game.getMoveCount());
    }

    @Test
    void oCanWin()
    {
        game.makeMove(0, 1);
        game.makeMove(0, 0);
        game.makeMove(0, 2);
        game.makeMove(1, 1);
        game.makeMove(1, 0);
        assertEquals(MoveResult.WIN, game.makeMove(2, 2));
        assertEquals("O", game.getCurrentPlayer());
    }

    @Test
    void tieBeforeTheBoardIsFull()
    {
        int[][] moves = {{0, 0}, {1, 1}, {0, 2}, {0, 1}, {2, 1}, {1, 2}, {1, 0}};
        for (int[] m : moves)
        {
            assertEquals(MoveResult.CONTINUE, game.makeMove(m[0], m[1]));
        }
        assertEquals(MoveResult.TIE, game.makeMove(2, 0));
        assertFalse(game.getBoard().isFull());
    }

    @Test
    void fullBoardTie()
    {
        int[][] moves = {{0, 2}, {1, 1}, {1, 2}, {2, 2}, {2, 0}, {0, 1}, {2, 1}, {1, 0}};
        for (int[] m : moves)
        {
            assertEquals(MoveResult.CONTINUE, game.makeMove(m[0], m[1]));
        }
        assertEquals(MoveResult.TIE, game.makeMove(0, 0));
        assertTrue(game.getBoard().isFull());
    }

    @Test
    void resetStartsANewGame()
    {
        game.makeMove(0, 0);
        game.makeMove(1, 1);
        game.reset();
        assertEquals("X", game.getCurrentPlayer());
        assertEquals(0, game.getMoveCount());
        assertTrue(game.getBoard().isValidMove(0, 0));
        assertTrue(game.getBoard().isValidMove(1, 1));
    }
}
