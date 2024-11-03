public class SequentialAIPlayer implements WheelOfFortunePlayer{
    private char currentGuess;
    private String id;

    public SequentialAIPlayer(String id) {
        this.id = id;
        reset();
    }

    @Override
    public char nextGuess() {
        return currentGuess <= 'z' ? currentGuess++ : ' ';
    }

    @Override
    public String playerId() {
        return id;
    }

    @Override
    public void reset() {
        currentGuess = 'a';
    }

}
