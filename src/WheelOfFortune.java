import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

//WOF game to guess a phrase
public abstract class WheelOfFortune extends GuessingGame{
    abstract char getGuess(String previousGuesses);
    protected String preGuess;

    //Constructor without parameters
    public WheelOfFortune(){
        super();
        this.preGuess="";
        this.count=100;
    }

    // instance method
    public void randomPhrase(){
        List<String> phraseList=null;
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        // Get a random phrase from the list
        Random rand = new Random();
        int r= rand.nextInt(3); // gets 0, 1, or 2
        this.phrase = phraseList.get(r);

    }
    //Method to generate hidden phrase
    @Override
    public void generateHiddenPhrase(){
        //Get a hidden phrase
        this.hiddenPhrase=new StringBuilder("");
        for(int i=0;i<phrase.length();i++){
            char ch=phrase.charAt(i);
            if(Character.isLetter(ch)){
                hiddenPhrase.append("*");
            }
            else{
                hiddenPhrase.append(ch);
            }
        }

        System.out.println(hiddenPhrase);
    }

    //method to processGuess
    public void processGuess(char guess) {


        int index=phrase.toLowerCase().indexOf(guess);

        if(index!=-1) {
            while(index!=-1){
                hiddenPhrase.setCharAt(index, phrase.charAt(index));
                index = phrase.indexOf(guess, index + 1);
            }
            System.out.println(hiddenPhrase);

        }

        else{
            System.out.println("your guess wrong,please make another guess");
            System.out.println(hiddenPhrase);
        }
    }

    // Main game to loop method
    @Override
    public GameRecord play(){
        // Reset data members for a new game
        this.preGuess="";
        this.count=100;
        int score=INITIAL_SCORE;
        randomPhrase();
        generateHiddenPhrase();


        while(!phrase.equals(hiddenPhrase.toString()) && count>0 && score>0) {
            char guess =getGuess(preGuess);
            String originalHiddenPhrase = hiddenPhrase.toString();

            processGuess(guess);

            if (hiddenPhrase.toString().equals(originalHiddenPhrase)) {
                score -= SCORE_DEDUCTION;
                System.out.println("Incorrect guess. Score deducted. Current score: " + score);
            } else {
                System.out.println("Correct guess! Your current score remains: " + score);
            }

            count--;
            System.out.println("Remaining attempts: " + count);
        }

        if (phrase.equals(hiddenPhrase.toString())) {
            System.out.println("Congratulations! You win! Your final score is " + score + ".");
        } else if (score <= 0) {
            System.out.println("Game over! You've run out of score.");
        } else {
            System.out.println("Please start a new game!");
        }

        return new GameRecord(score,"Player1");
    }
//    @Override
//    public boolean playNext(){
//        System.out.print("Do you want to play the next game? (y/n): ");
//        Scanner scanner = new Scanner(System.in);
//        String response = scanner.nextLine().trim().toLowerCase();
//        return response.equals("y");
//
//    }
}
