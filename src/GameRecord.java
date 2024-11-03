public class GameRecord implements Comparable<GameRecord>{
    protected int score;
    protected String playerID;

    public GameRecord(int point,String Id){
        this.score=point;
        this.playerID=Id;
    }

    @Override
    public int compareTo(GameRecord other) {
        return Integer.compare(other.score, this.score);
    }
    @Override
    public String toString(){
        return "PlayerID: "+playerID+", Score: " +score;
    }
    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(obj==null|| this.getClass()!=obj.getClass()){
            return false;
        }
        GameRecord other=(GameRecord) obj;
        return this.score==other.score;
    }
    @Override
    public int hashCode(){
        return Integer.hashCode(score);
    }
}
