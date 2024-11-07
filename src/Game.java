public abstract class Game {

    //Method to play multiple games until the user decides to stop
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


   // Abstract method to play a single game.
    abstract GameRecord play();

   // Abstract method to determine if the player wants to play another game.
    abstract boolean playNext();
}
