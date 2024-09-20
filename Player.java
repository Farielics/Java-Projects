/*
* Names: Faryal Alizai
* netID: falizai4
* G#: 01364057
* Lecture section: 004
* Lab section: 208
*/
public abstract class Player {
//player class is declared as an abstract
    public abstract MiniCheckers chooseMove(MiniCheckers game);
    //represents the platers decison making for chosing moves

    public double boardValue(MiniCheckers game) {
        if (game.checkWin(this)) {
        //checks if the current player won
            return 1.0;
            //returns 1.0 if player won
        } else if (game.checkLose(this)) {
        //checks if the current player lost
            return -1.0;
            //if they lose then it returns -1.0
        } else {
        //if no one wins or loses
            return 0.0;
        //it returns 0.0 is the games ongoing or if theres no winner/loser
        }
    }
}