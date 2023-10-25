/**
 * This interface defines the behavior and contract for a Wheel of Fortune player in the game.
 */
public interface WheelOfFortunePlayer {

    /**
     * Get the next guess from the player
     * @return char
     */
    char nextGuess();

    /**
     * Gets the id for the player
     * @return String playerId
     */
    String playerId();

    /**
     * Reset the player to start a new game
     * Must reset the index of the counter used to retrieve guesses from a string
     */
    void reset();

}