import javax.swing.JButton;

/**
 * One square on the Tic Tac Toe board. It is a JButton that also remembers
 * its row and column so the listener can tell the game which square was
 * clicked. (Reused from Ass 01.)
 *
 * @author Donathan Chuon
 */
public class TTTTileButton extends JButton
{
    private final int row;
    private final int col;

    /**
     * Creates a tile for the given position on the board.
     *
     * @param row the row of the tile, 0 - 2
     * @param col the column of the tile, 0 - 2
     */
    public TTTTileButton(int row, int col)
    {
        super();
        this.row = row;
        this.col = col;
    }

    /**
     * @return the row of this tile
     */
    public int getRow()
    {
        return row;
    }

    /**
     * @return the column of this tile
     */
    public int getCol()
    {
        return col;
    }
}
