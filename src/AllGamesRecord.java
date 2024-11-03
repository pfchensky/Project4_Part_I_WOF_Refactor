import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllGamesRecord {
    private List<GameRecord> gameRecords;

    public AllGamesRecord(){
        this.gameRecords=new ArrayList<>();
    }

    public void add(GameRecord gameRecord){
        gameRecords.add(gameRecord);
    }


    public double average(){
        double totalScore=0;
        for(GameRecord gameRecord:gameRecords){
            totalScore=totalScore+gameRecord.score;
        }
        double ave=totalScore/gameRecords.size();
        return ave;
    }

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
       return aveID;
   }

    public ArrayList<GameRecord> highGameList(int n){
        Collections.sort(gameRecords);
        return new ArrayList<>(gameRecords.subList(0,n));
    }

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

}
