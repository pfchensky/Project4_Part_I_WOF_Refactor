//generate letter by sequential order like from a to z
public class SequentialAIPlayer implements WheelOfFortunePlayer{
    private int currentIndex;
    private String id;

    //constructor to initialize
    public SequentialAIPlayer(String id) {
        this.id = id;
        this.currentIndex = 0;
    }

    //get next guess
    @Override
    public char nextGuess() {
        char guess = (char) ('a' + currentIndex);
        currentIndex = (currentIndex + 1) % 26;
        return guess;
    }

    //get player Id
    @Override
    public String playerId() {
        return id;
    }

    //reset to start a new game
    @Override
    public void reset() {
        currentIndex = 0; // reset to start
    }
}
