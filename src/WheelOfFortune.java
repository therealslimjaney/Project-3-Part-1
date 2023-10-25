import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * The 'WheelOfFortune' class is an abstract class that serves as the basis for implementing Wheel of Fortune games.
 * Subclasses must implement the abstract method 'getGuess'
 *
 * @see Game
 */
abstract class WheelOfFortune extends Game{
    protected String phrase;
    protected StringBuilder hiddenPhrase;
    protected StringBuilder previousGuesses;
    protected int guessesRemaining=5;
    protected ArrayList<String> phraseList;
    protected char guess;

    protected int roundCounter=0;

    /**
     * Constructs a new instance of the 'WheelOfFortune' game, initializing its state, including phrases and previous guesses.
     */
    public WheelOfFortune(){
            phraseList=readPhrases();
            previousGuesses = new StringBuilder();
    }

    /**
     * Returns a string representation of the 'WheelOfFortune' game object
     *
     * @return A string
     */
    @Override
    public String toString() {
        return "WOF{" +
                "phrase='" + phrase + '\'' +
                ", hiddenPhrase=" + hiddenPhrase +
                ", previousGuesses=" + previousGuesses +
                ", guessesRemaining=" + guessesRemaining +
                ", phraseList=" + phraseList +
                ", guess=" + guess +
                ", roundCounter=" + roundCounter +
                '}';
    }
    /**
     * Indicates whether some other object is "equal to" this one
     *
     * @param o The reference object with which to compare.
     * @return true if this object is equal to the 'o' argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WheelOfFortune wof = (WheelOfFortune) o;
        return guessesRemaining == wof.guessesRemaining && guess == wof.guess && roundCounter == wof.roundCounter && Objects.equals(phrase, wof.phrase) && Objects.equals(hiddenPhrase, wof.hiddenPhrase) && Objects.equals(previousGuesses, wof.previousGuesses) && Objects.equals(phraseList, wof.phraseList);
    }

    /**
     * Returns a hash code value for the 'WheelOfFortune' game object.
     * @return int hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(phrase, hiddenPhrase, previousGuesses, guessesRemaining, phraseList, guess, roundCounter);
    }

    /**
     * Defines an abstract method for the WheelOfFortune game.
     * All concrete WheelOfFortune games must implement this method.
     *
     * @param previousGuesses A StringBuilder representing the previous guesses made in the game.
     * @return The next guess as a character.
     */
    abstract char getGuess(StringBuilder previousGuesses);

    /**
     * Reads a list of phrases from a file and returns them as an ArrayList of strings.
     *
     * @return An ArrayList containing the phrases read from the file.
     */
    private ArrayList<String> readPhrases() {
        List<String> phraseList = null;
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (
                IOException e) {
            System.out.println(e);
        }
        return new ArrayList<>(phraseList);
    }

    /**
     * Generates a random index to return an element of a phraseList and removes that element from phraseList
     *
     * @return a phrase
     */
    public String randomPhrase() {
        int x; // The index of the phrase to return
        if (phraseList.size() == 1) {
            x = 0;
        } else {
            Random rand = new Random();
            x = rand.nextInt(phraseList.size());
        }
        String phrase = phraseList.get(x);
        phraseList.remove(x);
        return phrase;
    }

    /**
     * Generates the initial hidden phrase before the user has made any guesses, replacing letters in the original
     * phrase with asterisks (*)
     *
     * @param phrase the original phrase to obscure with asterisks
     * @return the hidden phrase with letters replaced by asterisks
     */
    public StringBuilder getHiddenPhrase(String phrase) {
        StringBuilder initialCode = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.isLetter(phrase.charAt(i))) {
                initialCode.append("*");
            } else {
                initialCode.append(phrase.charAt(i));
            }
        }
        return initialCode;
    }

    /**
     * Determines if a guess is a match and calls the appropriate method
     *
     * @return void
     */
    public void processGuess() {
        String stringGuess = String.valueOf(this.guess);
        if (this.phrase.contains(stringGuess) || this.phrase.contains(stringGuess.toUpperCase())) {
            processCorrect();
        } else {
            processIncorrect();
        }
    }

    /**
     * Updates previousGuesses with both lowercase and uppercase and calls updateHiddenPhrase.
     */
    public void processCorrect() {
        this.previousGuesses.append(this.guess);
        this.previousGuesses.append(Character.toUpperCase(this.guess));
        updateHiddenPhrase();
    }

    /**
     * Updates previousGuesses with both lowercase and uppercase.
     */
    public void processIncorrect() {
        this.previousGuesses.append(this.guess);
        this.previousGuesses.append(Character.toUpperCase(this.guess));
        this.guessesRemaining--;
    }

    /**
     * Updates the 'hiddenPhrase' to reflect the current correct guesses made
     * The method iterates through each character in the 'phrase', checks if it's a letter, and if it has been guessed.
     */
    public void updateHiddenPhrase() {
        this.hiddenPhrase.setLength(0); //clear existing content of the hiddenPhrase StringBuilder
        for (int i = 0; i < this.phrase.length(); i++) {
            char currentChar = this.phrase.charAt(i);
            if ((Character.isLetter(currentChar)) && (this.previousGuesses.indexOf(String.valueOf(currentChar))) == -1) { // If the character is a letter and it hasn't been guessed yet obscure with *
                this.hiddenPhrase.append("*");
            } else {
                this.hiddenPhrase.append(this.phrase.charAt(i));
            }
        }
    }

    /**
     * Resets the game to its initial state with a new round.
     * Subclasses must extend this for resetting the game phrases
     */
    public void loadNewGame() {
        previousGuesses.setLength(0);
        roundCounter=0;
        guessesRemaining=5;
    }

    /**
     * Displays game instructions for the player.
     */
    public void displayGameInstructions() {
        System.out.println("\n=================== Welcome to the Wheel of Fortune Game! ===================\nObjective: Try to guess the hidden phrase by suggesting letters one at a time.\nInstructions:\n1. Enter a letter to make a guess (e.g., \"a\" or \"B\").\n2. You will receive feedback after each guess.\n3. You can make up to " + guessesRemaining + "  incorrect guesses.\n4. Keep guessing until you either guess the phrase correctly or run out of guesses.\n -GOOD LUCK!");
    }

    /**
     * Prints the current state of the game for player
     */
    public void displayGameInfo() {
        System.out.println("\n<---ROUND " + roundCounter + "--->\nPhrase:\t" + hiddenPhrase + "\nPrevious Guesses: " + previousGuesses + "\n#Guesses Remaining: " + guessesRemaining);
    }

    /**
     * Displays the game result when the player has won.
     */
    public void displayGameResult() {
        System.out.println("Congratulations! You have won!\n");
        System.out.println("<----GAME SUMMARY---->\nPhrase:\t" + hiddenPhrase + "\nScore: " + guessesRemaining + " (defined as guesses remaining)");
    }
}