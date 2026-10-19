/**
 * The Tic Tac Toe board. It holds the 3 x 3 grid of marks and knows the
 * rules about the board: whether a square is free, whether a player has
 * three in a row, and whether the game can no longer be won.
 *
 * These were the static methods that all used the board array in the
 * console and Ass 01 versions. Now they belong to a Board object.
 *
 * @author Donathan Chuon
 */
public class TTTBoard
{
    /** Number of rows on the board. */
    public static final int ROWS = 3;
    /** Number of columns on the board. */
    public static final int COLS = 3;
    /** The mark used for an empty square. */
    public static final String EMPTY = " ";

    private final String[][] board = new String[ROWS][COLS];

    /**
     * Creates an empty board.
     */
    public TTTBoard()
    {
        clear();
    }

    /**
     * Sets every square back to empty.
     */
    public void clear()
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                board[row][col] = EMPTY;
            }
        }
    }

    /**
     * A move is valid only if the square is on the board and still empty.
     *
     * @param row the row, 0 - 2
     * @param col the column, 0 - 2
     * @return true if a mark can be placed there
     */
    public boolean isValidMove(int row, int col)
    {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS)
        {
            return false;
        }
        return board[row][col].equals(EMPTY);
    }

    /**
     * Puts a player's mark on a square. The caller should check
     * isValidMove first.
     *
     * @param row    the row, 0 - 2
     * @param col    the column, 0 - 2
     * @param player "X" or "O"
     */
    public void placeMark(int row, int col, String player)
    {
        board[row][col] = player;
    }

    /**
     * Gets the mark on a square.
     *
     * @param row the row, 0 - 2
     * @param col the column, 0 - 2
     * @return "X", "O" or EMPTY
     */
    public String getMark(int row, int col)
    {
        return board[row][col];
    }

    /**
     * Checks if a player has three in a row, column or diagonal.
     *
     * @param player "X" or "O"
     * @return true if the player has won
     */
    public boolean isWin(String player)
    {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    /**
     * The game is a tie when every one of the 8 win vectors (3 rows,
     * 3 columns, 2 diagonals) has both an X and an O in it. That covers the
     * full board tie and the tie where nobody can win but squares are left.
     *
     * @return true if neither player can win any more
     */
    public boolean isTie()
    {
        for (int row = 0; row < ROWS; row++)
        {
            if (!isBlocked(board[row][0], board[row][1], board[row][2]))
            {
                return false;
            }
        }
        for (int col = 0; col < COLS; col++)
        {
            if (!isBlocked(board[0][col], board[1][col], board[2][col]))
            {
                return false;
            }
        }
        if (!isBlocked(board[0][0], board[1][1], board[2][2]))
        {
            return false;
        }
        return isBlocked(board[0][2], board[1][1], board[2][0]);
    }

    /**
     * @return true if there are no empty squares left
     */
    public boolean isFull()
    {
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                if (board[row][col].equals(EMPTY))
                {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isRowWin(String player)
    {
        for (int row = 0; row < ROWS; row++)
        {
            if (board[row][0].equals(player) && board[row][1].equals(player)
                    && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isColWin(String player)
    {
        for (int col = 0; col < COLS; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player)
                    && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(String player)
    {
        boolean mainDiagonal = board[0][0].equals(player) && board[1][1].equals(player)
                && board[2][2].equals(player);
        boolean otherDiagonal = board[0][2].equals(player) && board[1][1].equals(player)
                && board[2][0].equals(player);
        return mainDiagonal || otherDiagonal;
    }

    /**
     * A win vector is blocked when it holds both an X and an O.
     */
    private boolean isBlocked(String a, String b, String c)
    {
        boolean hasX = a.equals("X") || b.equals("X") || c.equals("X");
        boolean hasO = a.equals("O") || b.equals("O") || c.equals("O");
        return hasX && hasO;
    }
}
