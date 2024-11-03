import java.util.Scanner;

public class WOFUserGame extends WheelOfFortune{

    public char getGuess(String previousGuesses){
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


    public static void main(String [] args) {
        WOFUserGame wofUserGame = new WOFUserGame();
        AllGamesRecord record = wofUserGame.playAll();
        System.out.println(record);  // or call specific functions of record

        // Display records using AllGamesRecord methods
        System.out.println("Here is your game performance: ");
        System.out.println("Total Average Scores: "+record.average());
        System.out.println("Your high scores list as follows: ");
        System.out.println(record.highGameList(2));
    }
}
