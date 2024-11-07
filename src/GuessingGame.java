import java.util.Scanner;

// this class provides basic setup for the WOF game and Mastermind Game
public abstract class GuessingGame extends Game{
    //Instance variables
    protected String phrase;
    protected StringBuilder hiddenPhrase;
    protected int count;
    protected static final int INITIAL_SCORE = 100;
    protected static final int SCORE_DEDUCTION = 10;

    //constructor to initialize
    public GuessingGame(){
    }

    //Abstract method to generate the hidden phrase based on the original phrase.
    abstract void generateHiddenPhrase();


    //method used to ask if the player wants to play the next game.
    @Override
    public boolean playNext(){
        System.out.print("Do you want to play the next game? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y");
    }

}
