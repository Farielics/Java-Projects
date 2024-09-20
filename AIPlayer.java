/*
* Names: Faryal Alizai
* netID: falizai4
* G#: 01364057
* Lecture section: 004
* Lab section: 208
*/
import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {
    private String name;
    private Player opponent;

    public AIPlayer(String name, Player opponent) {
    //Constructor
        this.name = name;
        this.opponent = opponent;
    }

    public Player getOpponent() {
    //Getter method for opponent
        return opponent;
        //returns opponent
    }

    public void setOpponent(Player opponent) {
    //setter method for opponent
        this.opponent = opponent;
    }

    @Override
    public String toString() {
        return name + " (AI)";
    //Override toString method
    }

    @Override
    public MiniCheckers chooseMove(MiniCheckers game) {
    //Override chooseMove method

        int initialDepth = 10;
        //Initial depth for recursion

        ArrayList<MiniCheckers> possibleMoves = game.possibleMoves(this);
        //Get all possible moves for the current game
        
        MiniCheckers bestMove = null;
        double bestValue = Double.NEGATIVE_INFINITY;
        //Initialize variables for best move and its value

        ArrayList<MiniCheckers> bestMoves = new ArrayList<>();
        //List to store moves with the highest value

        for (MiniCheckers move : possibleMoves) {
            double value = minValue(move, initialDepth - 1);
        //Evaluate each possible move

            if (value > bestValue) {
                bestValue = value;
            //Update the best move if a higher value is found
                bestMoves.clear();
                // Clear previous best moves
                bestMoves.add(move);
            } else if (value == bestValue) {
                bestMoves.add(move);
            //If the value is the same as the current best, add it to the list
            }
        }

        if (!bestMoves.isEmpty()) {
            bestMove = bestMoves.get(new Random().nextInt(bestMoves.size()));
        //Randomly select a move from the list of best moves
        }

        return bestMove;
        //returns the best move
    }


    public double maxValue(MiniCheckers game, int depth) {
        if (depth == 0 || game.checkWin(this) || game.checkLose(this)) {
        //Recursive method for max value
            return boardValue(game);
            //returns boardvalue
        }

        double bestValue = Double.NEGATIVE_INFINITY;

        for (MiniCheckers move : game.possibleMoves(this)) {
            double value = minValue(move, depth - 1);
            bestValue = Math.max(bestValue, value);
        }

        return bestValue;
    }

    public double minValue(MiniCheckers game, int depth) {
        if (depth == 0 || game.checkWin(opponent) || game.checkLose(opponent)) {
        //Recursive method for min value
            return boardValue(game);
            //retuns boardValue
        }

        double bestValue = Double.POSITIVE_INFINITY;

        for (MiniCheckers move : game.possibleMoves(opponent)) {
            double value = maxValue(move, depth - 1);
            bestValue = Math.min(bestValue, value);
        }

        return bestValue;
        //returns bestValue
    }
}