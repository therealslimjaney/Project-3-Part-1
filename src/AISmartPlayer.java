import java.util.Objects;
/**
 * This class represents a smart AI player for the Wheel of Fortune game.
 * The AI player generates guesses in order of their frequency used in the English language
 */
public class AISmartPlayer implements WheelOfFortunePlayer {

    private int counter = 0; // A counter to keep track of the index in the alphabet
    private String lettersRanked = "etainoshrdlucmfwygpbvkqj";

    /**
     * Get the next character guess made by the smart AI player strategy
     * Player guesses letters in order of their frequency used in the English language
     * @return The character
     */
    @Override
    public char nextGuess() {
        char guess = lettersRanked.charAt(counter);
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

        return "Smart Player";
    }

    /**
     * This method resets the guess counter for the player.
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
        return "AISmartPlayer{" +
                "counter=" + counter +
                ", lettersRanked='" + lettersRanked + '\'' +
                '}';
    }


    /**
     * Compare the smart AI player with another object for equality.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AISmartPlayer that = (AISmartPlayer) o;
        return counter == that.counter && Objects.equals(lettersRanked, that.lettersRanked);
    }

    /**
     * Compute a hash code for the smart player
     * @return The computed hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(counter, lettersRanked);
    }
}