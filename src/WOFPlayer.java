public interface WOFPlayer {

    /**
     * get the next guess from the player
     * @return
     */
    char nextGuess();

    /**
     * String playerId()  — an id for the player
     */
    String playerId();

    /**
     * reset the player to start a new game
     */
    void reset();

}