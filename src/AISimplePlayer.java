import java.util.Objects;

public class AISimplePlayer implements WOFPlayer {
    private int counter = 0; // A counter to keep track of the number of Guesses made in a game
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    @Override
    public char nextGuess() {
        char guess = alphabet.charAt(counter);
        counter++;
        return guess;
    }

    @Override
    public String playerId() {
        return "Simple Player";
    }

    @Override
    public void reset() {

        counter=0; // reset guess counter for a new game (next phrase in phraseList)
    }

    @Override
    public String toString() {
        return "AISimplePlayer{" +
                "counter=" + counter +
                ", alphabet='" + alphabet + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AISimplePlayer that = (AISimplePlayer) o;
        return counter == that.counter && Objects.equals(alphabet, that.alphabet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(counter, alphabet);
    }
}