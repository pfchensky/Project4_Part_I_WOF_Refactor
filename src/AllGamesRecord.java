import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// class to record all games
public class AllGamesRecord {
    private List<GameRecord> gameRecords;

    //constructor to initialize
    public AllGamesRecord(){
        this.gameRecords=new ArrayList<>();
    }

    // method to add a game record
    public void add(GameRecord gameRecord){
        gameRecords.add(gameRecord);
    }

    // calculate all gameRecords average
    public double average(){
        double totalScore=0;
        for(GameRecord gameRecord:gameRecords){
            totalScore=totalScore+gameRecord.score;
        }
        double ave=totalScore/gameRecords.size();
        return Math.round(ave * 100.0) / 100.0;
    }

    // calculate the same player gameRecord
   public double average(String playerId){
        double totalScoreID=0;
        int count=0;
       for(GameRecord gameRecord:gameRecords){
           if(gameRecord.playerID.equals(playerId)){
               totalScoreID=totalScoreID+gameRecord.score;
               count++;
           }
       }
       double aveID=totalScoreID/count;
       return Math.round(aveID * 100.0) / 100.0;
   }

    // List the first n number of highGameRecords
    public ArrayList<GameRecord> highGameList(int n){
        Collections.sort(gameRecords);
        return new ArrayList<>(gameRecords.subList(0,n));
    }

    // List the first n number of highGameRecords under one player
    public ArrayList<GameRecord> highGameList(String playerId, int n){
        List<GameRecord> playerScore=new ArrayList<>();
        for(GameRecord gameRecord:gameRecords){
            if(gameRecord.playerID.equals(playerId)){
                playerScore.add(gameRecord);
            }
        }
        Collections.sort(playerScore);
        return new ArrayList<>(playerScore.subList(0,n));

    }

    // toString Method to printout string format
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("All Game Records:\n");
        for(GameRecord gameRecord :gameRecords){
            sb.append(gameRecord.toString()).append("\n");
        }
        return sb.toString();
    }

    // equals methods to compare content
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AllGamesRecord other = (AllGamesRecord) obj;
        return gameRecords.equals(other.gameRecords);
    }

    @Override
    public int hashCode() {
        return gameRecords.hashCode();
    }
}
