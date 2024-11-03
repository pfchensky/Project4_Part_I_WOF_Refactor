public abstract class Game {


    public AllGamesRecord playAll(){
        AllGamesRecord allGamesRecord=new AllGamesRecord();
        while(playNext()){
            // Play the single game and get the record
            GameRecord gameRecord=play();
            //Add the result
            allGamesRecord.add(gameRecord);
        }
        return allGamesRecord;
    }

    abstract GameRecord play();
    abstract boolean playNext();
}
