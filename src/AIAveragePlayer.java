import java.util.Objects;
/**
 * This class represents an average AI player for the Wheel of Fortune game.
 * The AI player generates guesses in a sequential manner from the English alphabet, but using vowels first
 */
public class AIAveragePlayer implements WheelOfFortunePlayer {
    private int counter = 0; // A counter to keep track of the index in the alphabet
    private final String vowelsFirst = "aeioubcdfghjklmnpqrstvwxyz";

    /**
     * Get the next character guess made by the average AI player strategy
     *
     * @return The character representing the player's next guess from the English alphabet, using vowels first.
     */
    @Override
    public char nextGuess() {
        char guess = vowelsFirst.charAt(counter);
        counter++;
        return guess;
    }

    /**
     * Gets the playerId
     *
     * @return A String.
     */
    @Override
    public String playerId () {
            return "Average Player";
        }

    /**
     * This method resets the guess counter for the player.
     */
    @Override
    public void reset () {
        counter=0; // reset guess counter for a new game (next phrase in phraseList)
        }

    /**
     * Get a string representation of the AI player's state.
     *
     * @return A String containing information about the player's current state
     */
    @Override
    public String toString() {
        return "AIAveragePlayer{" +
                "counter=" + counter +
                ", vowelsFirst='" + vowelsFirst + '\'' +
                '}';
    }

    /**
     * Compare the average AI player with another object for equality.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AIAveragePlayer that = (AIAveragePlayer) o;
        return counter == that.counter && Objects.equals(vowelsFirst, that.vowelsFirst);
    }

    /**
     * Compute a hash code for the average player
     * @return The computed hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(counter, vowelsFirst);
    }
}