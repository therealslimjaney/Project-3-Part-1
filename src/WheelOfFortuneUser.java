import java.util.Scanner;

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

    public void loadNewGame() {
        super.loadNewGame();
        phrase = randomPhrase();
        hiddenPhrase=getHiddenPhrase(phrase);
    }

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