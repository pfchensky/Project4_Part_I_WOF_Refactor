import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//generate letter by random sequences
public class RandomAIPlayer implements WheelOfFortunePlayer{
    private Random random;
    private Set<Character> guessedLetters;
    private String id;

    //constructor to initialize
    public RandomAIPlayer(String id) {
        this.id = id;
        random = new Random();
        guessedLetters = new HashSet<>();
    }
    // to get next guess
    @Override
    public char nextGuess() {
        char guess;
        do {
            guess = (char) ('a' + random.nextInt(26));
        } while (guessedLetters.contains(guess));
        guessedLetters.add(guess);
        return guess;
    }
    //get player ID
    @Override
    public String playerId() {
        return id;
    }

    //reset to start a new game
    @Override
    public void reset() {
        guessedLetters.clear();
    }

}
