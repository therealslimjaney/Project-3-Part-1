
import java.util.ArrayList;
import java.util.Objects;

public class WOFAI extends WOF {
    private ArrayList<WOFPlayer> playerList = new ArrayList<>();
    private int playerIndex=0;
    private int phraseIndex=-1;

    public WOFAI() {
        AISmartPlayer player = new AISmartPlayer();
        playerList.add(player);
    }

    public WOFAI(WOFPlayer player) {
        playerList.add(player);
    }

    public WOFAI(ArrayList<WOFPlayer> players) {
        playerList = players;
    }

    @Override
    char getGuess(StringBuilder previousGuesses) {
        WOFPlayer player = playerList.get(playerIndex);
        char nextGuess;
        do {
            nextGuess = player.nextGuess();
        } while (previousGuesses.indexOf(String.valueOf(nextGuess)) != -1);
        return nextGuess;
    }

    @Override
    public GameRecord play() {
        GameRecord record = new GameRecord();
        WOFPlayer player = playerList.get(playerIndex);
        record.playerId = player.playerId();
        displayGameInstructions();
        while (guessesRemaining >= 0) {
            if (guessesRemaining == 0) {
                System.out.println("\n<------- GAME OVER! ------->\nScore: 0");
                player.reset();
                break;
            } else {
                displayGameInfo();
                this.guess = getGuess(previousGuesses);
                roundCounter++;
                processGuess();
                updateHiddenPhrase();
                if (hiddenPhrase.indexOf("*") == -1) {
                    record.score = guessesRemaining;
                    displayGameResult();
                    player.reset();
                    break;
                }
            }
        }
        return record;
    }

    @Override
    public boolean playNext() {
        if (phraseIndex+1 < phraseList.size()) {
            phraseIndex++;
            loadNewGame();
            return true;
        } else if (phraseIndex+1 == phraseList.size() && playerIndex+1<playerList.size()) {
            phraseIndex = 0;
            playerIndex++;
            loadNewGame();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "WOFAI{" +
                "playerList=" + playerList +
                ", playerIndex=" + playerIndex +
                ", phraseIndex=" + phraseIndex +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WOFAI wofai = (WOFAI) o;
        return playerIndex == wofai.playerIndex && phraseIndex == wofai.phraseIndex && Objects.equals(playerList, wofai.playerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), playerList, playerIndex, phraseIndex);
    }

    public void loadNewGame() {
            super.loadNewGame(); // Reset game-specific state variables
            phrase = phraseList.get(phraseIndex);
            hiddenPhrase = getHiddenPhrase(phrase);
    }

    public static void main(String[] args) {

        // Create 3 new players
        AISimplePlayer simplePlayer = new AISimplePlayer();
        AIAveragePlayer averagePlayer = new AIAveragePlayer();
        AISmartPlayer smartPlayer = new AISmartPlayer();

        // Create an ArrayList to store the players
        ArrayList<WOFPlayer> players = new ArrayList<>();

        // Add the players to the ArrayList
        players.add(simplePlayer);
        players.add(averagePlayer);
        players.add(smartPlayer);

        // Create an AIGame that takes in an ArrayList of players in the constructor
        WOFAI AIgame = new WOFAI(players);

        // Create AllGamesRecord object
        AllGamesRecord allGamesRecord = AIgame.playAll();

        // Display AllGamesRecord object
        System.out.println("All Games Record: ");
        System.out.println(allGamesRecord);

        // Display highGameList for each player (showing all 3 games)
        System.out.println("Simple Player highGameList: ");
        System.out.println(allGamesRecord.highGameList("Simple Player", 3));
        System.out.println("Average Player highGameList: ");
        System.out.println(allGamesRecord.highGameList("Average Player", 3));
        System.out.println("Smart Player highGameList: ");
        System.out.println(allGamesRecord.highGameList("Smart Player", 3));

        // Display average of all nine games
        System.out.println(allGamesRecord.average());

        // Display average of each player (contest)
        System.out.println("Simple Player average: " + allGamesRecord.average("Simple Player"));
        System.out.println("Average Player average: " + allGamesRecord.average("Average Player"));
        System.out.println("Smart Player average: " + allGamesRecord.average("Smart Player"));

    }
}
