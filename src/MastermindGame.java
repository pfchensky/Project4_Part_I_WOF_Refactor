import java.util.Random;
import java.util.Scanner;

public class MastermindGame extends GuessingGame {
    private static final char[] COLORS = {'R', 'G', 'B', 'Y', 'O', 'P'};
    private static final int CODESIZE = 4;
    private String secretCode;

    public MastermindGame() {
        super();
        this.count = 10;
    }

    // Generate the secret code
    public void generateSecretCode() {
        Random rand = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODESIZE; i++) {
            int j = rand.nextInt(COLORS.length);
            code.append(COLORS[j]);
        }
        this.secretCode = code.toString();
        System.out.println("Secret code generated! " + secretCode);
    }

    @Override
    public void generateHiddenPhrase() {
        StringBuilder hiddenPhrase = new StringBuilder();
        for (int i = 0; i < CODESIZE; i++) {
            hiddenPhrase.append('*');
        }
        this.hiddenPhrase = hiddenPhrase;
    }

    //public void processGuess(char guess) {}

    public int checkExacts(String guess) {
        int exacts = 0;
        StringBuilder secretSB = new StringBuilder(secretCode);
        StringBuilder guessSB = new StringBuilder(guess);
        for (int i = 0; i < CODESIZE; i++) {
            if (secretSB.charAt(i) == guessSB.charAt(i)) {
                exacts++;
                // Mark exact matches
                secretSB.setCharAt(i, '-');
                guessSB.setCharAt(i, '*');
            }
        }
        return exacts;
    }

    public int checkPartials(String guess) {
        int partials = 0;
        StringBuilder secretSB = new StringBuilder(secretCode);
        StringBuilder guessSB = new StringBuilder(guess);

        // First, mark exact matches in secretSB and guessSB
        for (int i = 0; i < CODESIZE; i++) {
            if (secretSB.charAt(i) == guessSB.charAt(i)) {
                secretSB.setCharAt(i, '-');
                guessSB.setCharAt(i, '*');
            }
        }

        // Then, find partial matches
        for (int i = 0; i < CODESIZE; i++) {
            if (guessSB.charAt(i) != '*') { // Skip already matched characters
                for (int j = 0; j < CODESIZE; j++) {
                    if (secretSB.charAt(j) == guessSB.charAt(i) && secretSB.charAt(j) != '-') {
                        partials++;
                        secretSB.setCharAt(j, '-'); // Mark partial match
                        break;
                    }
                }
            }
        }
        return partials;
    }

    @Override
    public GameRecord play() {
        generateSecretCode();
        Scanner scanner = new Scanner(System.in);
        int exacts=0;

        int score=INITIAL_SCORE;
        while (count > 0) {
            System.out.print("Enter your 4-color guess (e.g., RGBY): ");
            String guess = scanner.nextLine().toUpperCase();

            if (guess.length() != CODESIZE || !guess.matches("[RGBYOP]+")) {
                System.out.println("Invalid input. Please enter 4 colors using R, G, B, Y, O, P.");
                continue;
            }

            exacts = checkExacts(guess);
            int partials = checkPartials(guess);
            System.out.println("Exact matches: " + exacts + ", Partial matches: " + partials);

            if (exacts == CODESIZE) {
                System.out.println("Congratulations! You've cracked the code! Current score:" + score);
                break;
            }
            count--;
            score=score-SCORE_DEDUCTION;
            System.out.println("Remaining attempts: " + count+", Current score: " + score);
        }
        if (count == 0 && exacts!=CODESIZE) {
            System.out.println("Game over! The secret code was: " + secretCode);
        }
        return new GameRecord(score, "Player1");
    }

    public static void main(String[] args) {
        MastermindGame mastermind = new MastermindGame();
        AllGamesRecord record = mastermind.playAll();
        System.out.println(record);  // or call specific functions of record

        // Display records using AllGamesRecord methods
        System.out.println("Here is your game performance: ");
        System.out.println("Total Average Scores: " + record.average());
        System.out.println("Your high scores list as follows: ");
        System.out.println(record.highGameList(2));
    }
}
