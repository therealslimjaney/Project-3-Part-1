/**
 * The abstract 'Game' class serves as a base for implementing various games.
 * Subclasses provide implement the 'play' and 'playNext' methods.
 *
 * @see GameRecord
 * @see AllGamesRecord
 */
abstract class Game {
    /**
     * Plays a series of games, records the results, and returns an AllGamesRecord object summarizing the set.
     *
     * @return An AllGamesRecord object containing records of all the games played.
     */
    public AllGamesRecord playAll() {
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        while (playNext()) {
            GameRecord game = this.play();
            allGamesRecord.addGameRecord(game);
        }
        return allGamesRecord;
    }

    /**
     * Plays a game and returns a GameRecord representing the game's outcome.
     * Subclasses of the 'Game' class must implement this method to define the specific game logic.
     *
     * @return A GameRecord containing the score and playerId.
     */
    public abstract GameRecord play();


    /**
     * Checks if the next game should be played
     * Subclasses of the 'Game' class are required to implement this method.
     *
     * @return True if the next game should be played, false otherwise.
     */
    public abstract boolean playNext();

    /**
     * Returns a string representation of the 'Game' object
     *
     * @return A string
     */
    @Override
    public String toString() {
        return "Game{}";
    }

}