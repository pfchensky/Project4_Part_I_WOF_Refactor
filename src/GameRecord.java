//class to record a single game
public class GameRecord implements Comparable<GameRecord>{
    protected int score;
    protected String playerID;

    // constructor to initialize point and ID
    public GameRecord(int point,String Id){
        this.score=point;
        this.playerID=Id;
    }

    // compareTo method to compare score of gameRecord
    @Override
    public int compareTo(GameRecord other) {
        return Integer.compare(other.score, this.score);
    }

    // toString Method to printout string format
    @Override
    public String toString(){
        return "PlayerID: "+playerID+", Score: " +score;
    }

    //equals method to compare object content
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
