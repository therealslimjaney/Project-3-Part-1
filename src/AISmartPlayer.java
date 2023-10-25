import java.util.Objects;

public class AISmartPlayer implements WOFPlayer {

    private int counter = 0;
    private String lettersRanked = "etainoshrdlucmfwygpbvkqj";
    @Override
    public char nextGuess() {
        char guess = lettersRanked.charAt(counter);
        counter++;
        return guess;
    }

    @Override
    public String playerId() {

        return "Smart Player";
    }

    @Override
    public void reset() {

        counter=0; // reset guess counter for a new game (next phrase in phraseList)
    }

    @Override
    public String toString() {
        return "AISmartPlayer{" +
                "counter=" + counter +
                ", lettersRanked='" + lettersRanked + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AISmartPlayer that = (AISmartPlayer) o;
        return counter == that.counter && Objects.equals(lettersRanked, that.lettersRanked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(counter, lettersRanked);
    }
}