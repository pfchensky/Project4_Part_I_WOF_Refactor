import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class WOFAIGame extends WheelOfFortune{

    private List<WheelOfFortunePlayer> players;// List of AI players
    private int currentPlayerIndex;
    private List<String> phraseList;
    private int phraseIndex=0;

    //Default constructor with a single default AI player
    public WOFAIGame(){
        this.players=new ArrayList<>();
        this.phraseList=new ArrayList<>();
        this.players.add(new SequentialAIPlayer("DefaultAI"));
    }
    //Constructor that accepts a single AI Player
    public WOFAIGame(WheelOfFortunePlayer player){
        this.players=new ArrayList<>();
        this.players.add(player);
        this.phraseList=new ArrayList<>();
    }
    public WOFAIGame(List<WheelOfFortunePlayer> players){
        this.players=players;
        this.phraseList=new ArrayList<>();
    }

    public void getPhrase() {
        //List<String> phraseList = null;

        // Read the phraseList from a file
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println("Error reading phrases file: " + e.getMessage());
            return;  // Return if file reading fails
        }

        // Check if the file is empty
        if (phraseList == null || phraseList.isEmpty()) {
            System.out.println("The phrases file is empty.");
            return;  // Return if the file is empty
        }

        // Get the current phrase
        this.phrase = phraseList.get(phraseIndex);
        System.out.println("Selected phrase: " + this.phrase);
        //phraseIndex++;
    }

    @Override
    public GameRecord play(){
        getPhrase();
        System.out.println("Hidden phrase: ");
        generateHiddenPhrase();
        this.preGuess = "";
        this.count = 100;

        // Get current player
        WheelOfFortunePlayer currentPlayer = players.get(currentPlayerIndex);

        while (!phrase.equals(hiddenPhrase.toString()) && count > 0) {
            char guess = currentPlayer.nextGuess();
            System.out.println("Ai Guess is : "+guess+"!");
            processGuess(guess);
            preGuess += guess;
            count--;
            System.out.println("Your current score is : "+ count);
            System.out.println();
        }

        // print result
        if (phrase.equals(hiddenPhrase.toString())) {
            System.out.println("AI Player: " + currentPlayer.playerId() + " won the game! "+"your Score is :"+count+".");
            System.out.println();
        } else {
            System.out.println("AI Player: " + currentPlayer.playerId() + " lost the game!");
            System.out.println();
        }

        return new GameRecord(count > 0 ? count : 0, currentPlayer.playerId());

    }
    @Override
    public boolean playNext() {
        // Have the current player guess each phrase in sequence
        phraseIndex++;

        // if the current player has guessed all phrases,reset phrase index and switch to the next player
        if (phraseIndex >= phraseList.size()) {
            phraseIndex = 0;
            currentPlayerIndex++;

            // If all players have had a turn, stop the game
            if (currentPlayerIndex >= players.size()) {
                return false;
            }
        }
        players.get(currentPlayerIndex).reset();

        // Continue the game as long as there are players and phrases left
        return true;
    }



    @Override
    public char getGuess(String previousGuesses) {
        WheelOfFortunePlayer currentPlayer= players.get(currentPlayerIndex);
        return currentPlayer.nextGuess();
    }

    public static void main(String [] args) {
        List<WheelOfFortunePlayer> aiPlayers=new ArrayList<>();
        aiPlayers.add(new SequentialAIPlayer("AI1"));
        aiPlayers.add(new RandomAIPlayer("AI2"));
        aiPlayers.add(new VowelConsonantAIPlayer("AI3"));

        WOFAIGame wofaiGame=new WOFAIGame(aiPlayers);

        AllGamesRecord record = wofaiGame.playAll();


        // Display records using AllGamesRecord methods
        System.out.println();
        System.out.println("Here is AI game performance: ");
        System.out.println();
        System.out.println("Total Average Scores: "+record.average());
        System.out.println();
        System.out.println("Your high scores list as follows: ");
        System.out.println(record.highGameList(9));

    }

}
