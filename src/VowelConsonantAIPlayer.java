import java.util.HashSet;
import java.util.Set;

//generate letter first by vowel then by consonant
public class VowelConsonantAIPlayer implements WheelOfFortunePlayer{
    private static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};
    private Set<Character> guessedLetters;
    private String id;
    private int index;

    //constructor to initialize
    public VowelConsonantAIPlayer(String id) {
        this.id = id;
        guessedLetters = new HashSet<>();
        reset();
    }

    //get next guess
    @Override
    public char nextGuess() {
        while (index < VOWELS.length) {
            char vowel = VOWELS[index++];
            if (!guessedLetters.contains(vowel)) {
                guessedLetters.add(vowel);
                return vowel;
            }
        }

        // If all vowels are guessed, guess consonants
        for (char c = 'a'; c <= 'z'; c++) {
            if ("aeiou".indexOf(c) == -1 && !guessedLetters.contains(c)) {
                guessedLetters.add(c);
                return c;
            }
        }
        return ' ';
    }
    //get player Id
    @Override
    public String playerId() {
        return id;
    }

    //reset to start
    @Override
    public void reset() {
        guessedLetters.clear();
        index = 0;
    }
}
