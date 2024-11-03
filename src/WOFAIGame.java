import java.util.ArrayList;
import java.util.List;

public class WOFAIGame extends WheelOfFortune{

    private List<WheelOfFortunePlayer> players;// List of AI players
    private int currentPlayerIndex;
    private List<String> phraseList;
    private String currentPhrase;

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

    @Override
    public GameRecord play(){
        currentPhrase = phraseList.get(0);
        this.phrase = currentPhrase;
        generateHiddenPhrase();
        this.preGuess = "";
        this.count = 100;

        // Get current player
        WheelOfFortunePlayer currentPlayer = players.get(currentPlayerIndex);

        while (!phrase.equals(hiddenPhrase.toString()) && count > 0) {
            char guess = currentPlayer.nextGuess();
            processGuess(guess);
            preGuess += guess;
            count--;
        }

        // print result
        if (phrase.equals(hiddenPhrase.toString())) {
            System.out.println("AI Player " + currentPlayer.playerId() + " won the game!");
        } else {
            System.out.println("AI Player " + currentPlayer.playerId() + " lost the game!");
        }

        return new GameRecord(count > 0 ? count : 0, currentPlayer.playerId());

    }
    @Override
    public boolean playNext(){
        if(phraseList.isEmpty()){
            return false;
        }
        currentPlayerIndex++;
        if(currentPlayerIndex>=players.size()){
            currentPlayerIndex=0;
            phraseList.remove(0);
        }
        return !phraseList.isEmpty();
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
        System.out.println("Here is AI game performance: ");
        System.out.println("Total Average Scores: "+record.average());
        System.out.println("Your high scores list as follows: ");
        System.out.println(record.highGameList(3));

    }

}
