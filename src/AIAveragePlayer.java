import java.util.Objects;

public class AIAveragePlayer implements WOFPlayer {
    private int counter = 0;
    private final String vowelsFirst = "aeioubcdfghjklmnpqrstvwxyz";

    @Override
    public char nextGuess() {
        char guess = vowelsFirst.charAt(counter);
        counter++;
        return guess;
    }

        @Override
        public String playerId () {
            return "Average Player";
        }

        @Override
        public void reset () {
        counter=0; // reset guess counter for a new game (next phrase in phraseList)
        }

    @Override
    public String toString() {
        return "AIAveragePlayer{" +
                "counter=" + counter +
                ", vowelsFirst='" + vowelsFirst + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AIAveragePlayer that = (AIAveragePlayer) o;
        return counter == that.counter && Objects.equals(vowelsFirst, that.vowelsFirst);
    }

    @Override
    public int hashCode() {
        return Objects.hash(counter, vowelsFirst);
    }
}