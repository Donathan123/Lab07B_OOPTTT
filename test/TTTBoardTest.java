import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for TTTBoard.
 *
 * @author Donathan Chuon
 */
class TTTBoardTest
{
    private TTTBoard board;

    @BeforeEach
    void setUp()
    {
        board = new TTTBoard();
    }

    /** Places marks from a picture of the board, e.g. "XO ", "   ", "  X". */
    private void fill(String... rows)
    {
        for (int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                char ch = rows[r].charAt(c);
                if (ch != ' ')
                {
                    board.placeMark(r, c, String.valueOf(ch));
                }
            }
        }
    }

    @Test
    void newBoardIsEmpty()
    {
        for (int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                assertEquals(TTTBoard.EMPTY, board.getMark(r, c));
                assertTrue(board.isValidMove(r, c));
            }
        }
        assertFalse(board.isFull());
    }

    @Test
    void takenSquareIsNotValid()
    {
        board.placeMark(1, 1, "X");
        assertFalse(board.isValidMove(1, 1));
        assertEquals("X", board.getMark(1, 1));
    }

    @Test
    void squaresOffTheBoardAreNotValid()
    {
        assertFalse(board.isValidMove(-1, 0));
        assertFalse(board.isValidMove(0, 3));
        assertFalse(board.isValidMove(3, 3));
    }

    @Test
    void rowWin()
    {
        fill("   ", "OOO", "XX ");
        assertTrue(board.isWin("O"));
        assertFalse(board.isWin("X"));
    }

    @Test
    void columnWin()
    {
        fill("XO ", "XO ", "X  ");
        assertTrue(board.isWin("X"));
        assertFalse(board.isWin("O"));
    }

    @Test
    void diagonalWins()
    {
        fill("X O", " X ", "O X");
        assertTrue(board.isWin("X"));

        board.clear();
        fill("X O", " O ", "O X");
        assertTrue(board.isWin("O"));
    }

    @Test
    void noWinOnEmptyOrMixedBoard()
    {
        assertFalse(board.isWin("X"));
        fill("XOX", "XOO", "OXX");
        assertFalse(board.isWin("X"));
        assertFalse(board.isWin("O"));
    }

    @Test
    void fullBoardTie()
    {
        fill("XOX", "XOO", "OXX");
        assertTrue(board.isFull());
        assertTrue(board.isTie());
    }

    @Test
    void tieWithASquareLeft()
    {
        fill("XOX", "XOO", "OX ");
        assertFalse(board.isFull());
        assertTrue(board.isTie());
    }

    @Test
    void notATieWhileALineIsStillOpen()
    {
        fill(" OX", "OOX", "XXO");
        assertFalse(board.isTie());
    }

    @Test
    void clearEmptiesTheBoard()
    {
        fill("XOX", "XOO", "OXX");
        board.clear();
        assertTrue(board.isValidMove(0, 0));
        assertFalse(board.isFull());
        assertFalse(board.isTie());
    }
}
