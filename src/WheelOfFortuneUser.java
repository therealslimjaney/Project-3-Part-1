import java.util.Scanner;

/**
 * The 'WheelOfFortuneUser' class is a subclass of 'WheelOfFortune' that enables a human user to play the game via the command line
 *
 * @see WheelOfFortune
 */
public class WheelOfFortuneUser extends WheelOfFortune {

    /**
     * Prompts the player to enter a guess and validates the input.
     *
     * @param previousGuesses A StringBuilder containing the previous guesses made by the player.
     * @return The player's valid letter guess as a character.
     */
    @Override
    public char getGuess(StringBuilder previousGuesses) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Guess a letter: ");
        String stringGuess = scanner.next().toLowerCase();
        char charGuess;

        if (stringGuess.length() == 1) {
            charGuess = stringGuess.charAt(0);
        } else {
            System.out.println("Please enter a single letter as your guess.");
            return getGuess(previousGuesses); // Recursively call the method to get a valid guess
        }

        if (previousGuesses.indexOf(String.valueOf(charGuess)) != -1) {
            System.out.println("You have already guessed '" + charGuess + "'");
            return getGuess(previousGuesses); // Recursively call the method to get a new guess
        }

        return charGuess;
    }
    /**
     * Implements the game logic for a human user playing Wheel of Fortune.
     *
     * @return A GameRecord.
     */
    @Override
    public GameRecord play() {
        GameRecord record = new GameRecord();
        record.playerId = "user";
        displayGameInstructions();
        while (guessesRemaining >= 0) {
            if (guessesRemaining == 0) {
                System.out.println("GAME OVER!\n");
                break;
            } else {
                displayGameInfo();
                this.guess = getGuess(this.previousGuesses);
                roundCounter++;
                processGuess();
                updateHiddenPhrase();
                if (hiddenPhrase.indexOf("*") == -1) {
                    record.score = guessesRemaining;
                    displayGameResult();
                    break;
                }
            }
        }
        return record;
    }

    /**
     * Determines if the user wants to play another game and resets the game state if necessary.
     *
     * @return boolean true if the user wants to play another game, false otherwise.
     */
    @Override
    public boolean playNext() {
        if (phraseList.isEmpty()) {
            System.out.println("\nSorry, we are all out of game phrases.\n"); // Stop the game if the phraseList is empty.
            return false;
        }
        System.out.println("\nPlay another game? Enter 'y' or 'n': "); // Need error handling here
        Scanner scanner = new Scanner(System.in);
        String playNext = scanner.next().toLowerCase();
        if (playNext.equals("y")) {
            loadNewGame();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "WOFUser{}";
    }

    /**
     * Resets the game to its initial state with a new round.
     */
    public void loadNewGame() {
        super.loadNewGame();
        phrase = randomPhrase();
        hiddenPhrase=getHiddenPhrase(phrase);
    }

    /**
     * The main method to start and play the Wheel of Fortune game with human user inputs
     *
     * @param args Command-line arguments
     */
    public static void main(String [] args) {
        WheelOfFortuneUser userGame = new WheelOfFortuneUser();
        AllGamesRecord allGamesRecord = userGame.playAll();

        // Display AllGamesRecord object
        System.out.println(allGamesRecord);

        //Display High Game List for 2 games
        System.out.println(allGamesRecord.highGameList(2));

        //Display average of games all games in AllGamesRecord object
        System.out.println(allGamesRecord.average());
    }
}