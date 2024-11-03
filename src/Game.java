public abstract class Game {

    /** Method to play multiple games until the user decides to stop
     *
     * @return return all games records
     */
    public AllGamesRecord playAll(){
        AllGamesRecord allGamesRecord=new AllGamesRecord();
        int roundNumber=1;
        do {
            System.out.println("Start game, round "+ roundNumber +"!");
            // Play the single game and get the record
            GameRecord gameRecord=play();// play a single game and get the record

            //Add the result
            allGamesRecord.add(gameRecord);
            roundNumber++;
        }while(playNext());
        return allGamesRecord;
    }

    abstract GameRecord play();
    abstract boolean playNext();
}
