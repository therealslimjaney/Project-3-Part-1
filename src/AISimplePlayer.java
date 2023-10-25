import java.util.Objects;
/**
 * This class represents a simple AI player for the Wheel of Fortune game.
 * The AI player generates guesses in a sequential manner from the English alphabet.
 */
public class AISimplePlayer implements WheelOfFortunePlayer {
    private int counter = 0; // A counter to keep track of the index in the alphabet
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Get the next character guess made by the simple AI player.
     *
     * @return The character representing the player's next guess from the English alphabet.
     */
    @Override
    public char nextGuess() {
        char guess = alphabet.charAt(counter);
        counter++;
        return guess;
    }

    /**
     * Gets the playerId
     *
     * @return A String.
     */
    @Override
    public String playerId() {
        return "Simple Player";
    }

    /**
     * Resets the guess counter for the player.
     */
    @Override
    public void reset() {

        counter=0; // reset guess counter for a new game (next phrase in phraseList)
    }


    /**
     * Get a string representation of the AI player's state.
     *
     * @return A String containing information about the player's current state
     */
    @Override
    public String toString() {
        return "AISimplePlayer{" +
                "counter=" + counter +
                ", alphabet='" + alphabet + '\'' +
                '}';
    }

    /**
     * Compare the simple AI player with another object for equality.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AISimplePlayer that = (AISimplePlayer) o;
        return counter == that.counter && Objects.equals(alphabet, that.alphabet);
    }

    /**
     * Compute a hash code for the simple AI player.
     *
     * @return The computed hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(counter, alphabet);
    }
}