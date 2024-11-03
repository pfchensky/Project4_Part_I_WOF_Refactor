import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomAIPlayer implements WheelOfFortunePlayer{
    private Random random;
    private Set<Character> guessedLetters;
    private String id;

    public RandomAIPlayer(String id) {
        this.id = id;
        random = new Random();
        guessedLetters = new HashSet<>();
    }

    @Override
    public char nextGuess() {
        char guess;
        do {
            guess = (char) ('a' + random.nextInt(26));
        } while (guessedLetters.contains(guess));
        guessedLetters.add(guess);
        return guess;
    }

    @Override
    public String playerId() {
        return id;
    }

    @Override
    public void reset() {
        guessedLetters.clear();
    }

}
