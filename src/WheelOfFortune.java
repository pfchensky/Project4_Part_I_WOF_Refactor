import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class WheelOfFortune extends Game{
    abstract char getGuess(String previousGuesses);
    //Instance variables
    protected String phrase;
    protected StringBuilder hiddenPhrase;
    protected String preGuess;
    protected int count;

    //Constructor without parameters
    public WheelOfFortune(){
        this.preGuess="";
        this.count = 100;
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
            System.out.println("your guess wrong");
            System.out.println("please make another guess");
            System.out.println(hiddenPhrase);
        }
    }

    // Main game to loop method
    @Override
    public GameRecord play(){
        randomPhrase();
        generateHiddenPhrase();

        while(!phrase.equals(hiddenPhrase.toString()) && count>0) {
            char guess =getGuess(preGuess);
            processGuess(guess);
            count--;
            System.out.println("you have "+count+ " chances");
        }

        if(phrase.equals(hiddenPhrase.toString())){
            System.out.println("Congratulations! You win!");
        }
        else {
            System.out.println("Please start a new game!");
        }
        return new GameRecord(count,"Player1");
    }

    @Override
    public boolean playNext(){
        System.out.print("Do you want to play the next game? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y");

    }
}
