//interface to define common abstract methods
public interface WheelOfFortunePlayer {
    //abstract method to get next guess
    char nextGuess();
    //abstract method to get next player Id
    String playerId();
    // abstract method to start a new game
    void reset();
}
