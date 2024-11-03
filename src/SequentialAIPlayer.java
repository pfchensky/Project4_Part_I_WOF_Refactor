public class SequentialAIPlayer implements WheelOfFortunePlayer{
    private int currentIndex;
    private String id;

    public SequentialAIPlayer(String id) {
        this.id = id;
        this.currentIndex = 0;
    }

    @Override
    public char nextGuess() {
        char guess = (char) ('a' + currentIndex);
        currentIndex = (currentIndex + 1) % 26;
        return guess;
    }

    @Override
    public String playerId() {
        return id;
    }

    @Override
    public void reset() {
        currentIndex = 0; // reset to start
    }
}
