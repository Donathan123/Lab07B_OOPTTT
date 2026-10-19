/**
 * The possible outcomes of trying to make a move in the game.
 *
 * @author Donathan Chuon
 */
public enum MoveResult
{
    /** The square was already taken, so the move was not made. */
    INVALID,
    /** The move was made and the game goes on with the other player. */
    CONTINUE,
    /** The move was made and the current player won. */
    WIN,
    /** The move was made and the game is a tie. */
    TIE
}
