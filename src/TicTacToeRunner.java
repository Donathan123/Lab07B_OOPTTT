import javax.swing.SwingUtilities;

/**
 * Main class for the OOP Tic Tac Toe game. It creates the frame on the
 * Swing event thread and shows it.
 *
 * @author Donathan Chuon
 */
public class TicTacToeRunner
{
    /**
     * Starts the game.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                TicTacToeFrame frame = new TicTacToeFrame();
                frame.setVisible(true);
            }
        });
    }
}
