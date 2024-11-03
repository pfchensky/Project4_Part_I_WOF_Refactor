import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WheelOfFortuneObject {

    //Instance variables
    String phrase;
    StringBuilder hiddenPhrase;
    String preGuess;
    int count;

    //Constructor without parameters
    public WheelOfFortuneObject(){
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

    public char getGuess(){
        System.out.print("Please guess a letter in the phrase: ");
        Scanner in=new Scanner(System.in);
        String input;
        while (true){
            input=in.next().toLowerCase();
            //Check if input is a single letter
            if(input.length()==1 && Character.isLetter(input.charAt(0))){
                char guess=input.charAt(0);
                if(preGuess.indexOf(String.valueOf(guess))!=-1){
                    System.out.println("You have already guess this letter, Please make another guess");
                }
                else {
                    preGuess=preGuess+guess;
                    return guess;
                }
            }
            else{
                System.out.println("Invalid input, Please enter one letter");
            }
        }

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
    public void playGame(){
        randomPhrase();
        generateHiddenPhrase();

        while(!phrase.equals(hiddenPhrase.toString()) && count>0) {
            char guess =getGuess();
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


    }
    public static void main(String[] args) {
        //Create instance
        WheelOfFortuneObject wheelOfFortune=new WheelOfFortuneObject();
        wheelOfFortune.playGame();
    }

}