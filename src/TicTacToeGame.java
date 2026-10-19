/**
 * The rules of a 2-player Tic Tac Toe game. It owns the board, keeps track
 * of whose turn it is and how many moves have been made, and decides what
 * each move results in. It knows nothing about the GUI, so the same game
 * could be used with a console, web or mobile front end.
 *
 * @author Donathan Chuon
 */
public class TicTacToeGame
{
    /** The first move on which a win is possible (X's third move). */
    public static final int MOVES_FOR_WIN = 5;
    /** The first move on which we check for a tie. */
    public static final int MOVES_FOR_TIE = 7;

    private final TTTBoard board = new TTTBoard();
    private String currentPlayer;
    private int moveCount;

    /**
     * Creates a new game with an empty board and X to move.
     */
    public TicTacToeGame()
    {
        reset();
    }

    /**
     * Starts a new game: clears the board and X goes first.
     */
    public void reset()
    {
        board.clear();
        currentPlayer = "X";
        moveCount = 0;
    }

    /**
     * Tries to make a move for the current player.
     *
     * If the square is taken nothing changes and the same player has to
     * pick again. Otherwise the mark is placed and the game checks for a
     * win (from the 5th move) and a tie (from the 7th move). If the game
     * goes on, the turn passes to the other player.
     *
     * @param row the row, 0 - 2
     * @param col the column, 0 - 2
     * @return what the move resulted in
     */
    public MoveResult makeMove(int row, int col)
    {
        if (!board.isValidMove(row, col))
        {
            return MoveResult.INVALID;
        }

        board.placeMark(row, col, currentPlayer);
        moveCount++;

        if (moveCount >= MOVES_FOR_WIN && board.isWin(currentPlayer))
        {
            return MoveResult.WIN;
        }
        if (moveCount >= MOVES_FOR_TIE && board.isTie())
        {
            return MoveResult.TIE;
        }

        switchPlayer();
        return MoveResult.CONTINUE;
    }

    private void switchPlayer()
    {
        if (currentPlayer.equals("X"))
        {
            currentPlayer = "O";
        }
        else
        {
            currentPlayer = "X";
        }
    }

    /**
     * @return the player whose turn it is, or who just won
     */
    public String getCurrentPlayer()
    {
        return currentPlayer;
    }

    /**
     * @return the number of moves made in this game
     */
    public int getMoveCount()
    {
        return moveCount;
    }

    /**
     * @return the board for this game
     */
    public TTTBoard getBoard()
    {
        return board;
    }
}
