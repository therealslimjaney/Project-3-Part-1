abstract class Game {
    /**
     * Plays a series of games, records the results, and returns an AllGamesRecord object summarizing the set.
     *
     * @return An AllGamesRecord object containing records of all the games played.
     */
    //This method is called on a WOFGame object
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
     * Checks
     * Subclasses of the 'Game' class are required to provide an implementation for this method.
     *
     * @return True if the next game should be played, false otherwise.
     */
    public abstract boolean playNext();

    @Override
    public String toString() {
        return "Game{}";
    }

}