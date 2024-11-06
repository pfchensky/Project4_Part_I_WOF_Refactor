import java.util.Scanner;

/**
 * the superclass
 */

public abstract class GuessingGame extends Game{
    //Instance variables
    protected String phrase;
    protected StringBuilder hiddenPhrase;
    protected int count;
    protected static final int INITIAL_SCORE = 100;
    protected static final int SCORE_DEDUCTION = 10;

    public GuessingGame(){
    }

    abstract void generateHiddenPhrase();
    //abstract void processGuess(char guess);

    @Override
    public boolean playNext(){
        System.out.print("Do you want to play the next game? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y");
    }

}
