/*
* Names: Faryal Alizai
* netID: falizai4
* G#: 01364057
* Lecture section: 004
* Lab section: 208
*/
import java.util.Scanner;

public class UserPlayer extends Player {
//Userplayer extends the Player class
    private String name;
    //players name
    private Scanner input;
    //instance variables
    
    public UserPlayer(Scanner input, String name) {
        this.input = input;
        this.name = name;
    //constructor for initializing userplayer
    }

    @Override
    public String toString() {
    //override toString method
        return name;
        //retuns name
    }

    @Override
    public MiniCheckers chooseMove(MiniCheckers game) {
    //override chooseMove method
        System.out.println("Current Board:\n" + game.toString());
        //display the current state of the board game
        System.out.println("Possible Moves:\n" + game.possibleMoves(this));
        //display the possible moves for the player

        System.out.print("Select a move (enter the corresponding index): ");
        int selectedIndex = input.nextInt();
        //Assuming user enters a valid option, asks to select a move

        return game.possibleMoves(this).get(selectedIndex);
        //retun the selected move
    }
}