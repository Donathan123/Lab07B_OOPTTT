import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Swing GUI for Tic Tac Toe. It only handles the display and the
 * player's clicks; all of the game rules are in TicTacToeGame and TTTBoard.
 *
 * The frame has a TicTacToeGame and a 3 x 3 grid of TTTTileButtons that all
 * share one listener. The listener passes the row and column of the clicked
 * tile to the game and then shows the result.
 *
 * @author Donathan Chuon
 */
public class TicTacToeFrame extends JFrame
{
    private final TicTacToeGame game = new TicTacToeGame();
    private final TTTTileButton[][] tiles = new TTTTileButton[TTTBoard.ROWS][TTTBoard.COLS];

    private JPanel mainPnl;
    private JPanel titlePnl;
    private JPanel boardPnl;
    private JPanel controlPnl;

    private JLabel statusLbl;
    private JButton quitBtn;

    /**
     * Builds the frame: status label, 3 x 3 board of tiles and Quit button.
     */
    public TicTacToeFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTitlePanel();
        mainPnl.add(titlePnl, BorderLayout.NORTH);

        createBoardPanel();
        mainPnl.add(boardPnl, BorderLayout.CENTER);

        createControlPanel();
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        add(mainPnl);

        setTitle("Tic Tac Toe");
        setSize(450, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // ---------------------------------------------------------------
    // GUI set up
    // ---------------------------------------------------------------

    private void createTitlePanel()
    {
        titlePnl = new JPanel();
        statusLbl = new JLabel("Player X's turn", JLabel.CENTER);
        statusLbl.setFont(new Font("SansSerif", Font.BOLD, 24));
        titlePnl.add(statusLbl);
    }

    private void createBoardPanel()
    {
        boardPnl = new JPanel();
        boardPnl.setLayout(new GridLayout(TTTBoard.ROWS, TTTBoard.COLS));

        // One listener instance shared by all nine tiles
        TileListener listener = new TileListener();

        for (int row = 0; row < TTTBoard.ROWS; row++)
        {
            for (int col = 0; col < TTTBoard.COLS; col++)
            {
                tiles[row][col] = new TTTTileButton(row, col);
                tiles[row][col].setText(TTTBoard.EMPTY);
                tiles[row][col].setFont(new Font("SansSerif", Font.BOLD, 72));
                tiles[row][col].setFocusPainted(false);
                tiles[row][col].addActionListener(listener);
                boardPnl.add(tiles[row][col]);
            }
        }
    }

    private void createControlPanel()
    {
        controlPnl = new JPanel();
        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("SansSerif", Font.PLAIN, 18));
        quitBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                quitGame();
            }
        });
        controlPnl.add(quitBtn);
    }

    /**
     * The single listener for all of the tiles. It finds the row and column
     * of the clicked tile, asks the game to make the move, and shows the
     * result. There is no game logic in here any more.
     */
    private class TileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            TTTTileButton tile = (TTTTileButton) ae.getSource();
            String player = game.getCurrentPlayer();

            MoveResult result = game.makeMove(tile.getRow(), tile.getCol());

            switch (result)
            {
                case INVALID:
                    JOptionPane.showMessageDialog(TicTacToeFrame.this,
                            "That square is already taken!\nPlayer " + player
                                    + ", please pick an empty square.",
                            "Illegal Move", JOptionPane.WARNING_MESSAGE);
                    break;

                case CONTINUE:
                    tile.setText(player);
                    statusLbl.setText("Player " + game.getCurrentPlayer() + "'s turn");
                    break;

                case WIN:
                    tile.setText(player);
                    statusLbl.setText("Player " + player + " wins!");
                    JOptionPane.showMessageDialog(TicTacToeFrame.this,
                            "Player " + player + " wins!",
                            "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    promptPlayAgain();
                    break;

                case TIE:
                    tile.setText(player);
                    statusLbl.setText("It's a tie!");
                    String msg;
                    if (game.getBoard().isFull())
                    {
                        msg = "It's a tie! The board is full.";
                    }
                    else
                    {
                        msg = "It's a tie! Neither player can win.\nEvery row, column and diagonal is blocked.";
                    }
                    JOptionPane.showMessageDialog(TicTacToeFrame.this, msg,
                            "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    promptPlayAgain();
                    break;
            }
        }
    }

    // ---------------------------------------------------------------
    // Game flow
    // ---------------------------------------------------------------

    /**
     * Asks if the players want another game. Yes starts a new game,
     * No says goodbye and ends the program.
     */
    private void promptPlayAgain()
    {
        int answer = JOptionPane.showConfirmDialog(this,
                "Would you like to play again?",
                "Play Again?", JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION)
        {
            newGame();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Thanks for playing!",
                    "Goodbye", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Resets the game and clears every tile.
     */
    private void newGame()
    {
        game.reset();
        for (int row = 0; row < TTTBoard.ROWS; row++)
        {
            for (int col = 0; col < TTTBoard.COLS; col++)
            {
                tiles[row][col].setText(TTTBoard.EMPTY);
            }
        }
        statusLbl.setText("Player X's turn");
    }

    /**
     * Asks the user to confirm before quitting.
     */
    private void quitGame()
    {
        int answer = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to quit?",
                "Quit", JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION)
        {
            JOptionPane.showMessageDialog(this, "Thanks for playing!",
                    "Goodbye", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
}
