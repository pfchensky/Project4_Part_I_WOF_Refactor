public class GameRecord implements Comparable<GameRecord>{
    protected int score;
    protected String playerID;

    public GameRecord(int point,String Id){
        this.score=point;
        this.playerID=Id;
    }

    @Override
    public int compareTo(GameRecord other) {
        return Integer.compare(other.score,this.score);
    }
}
