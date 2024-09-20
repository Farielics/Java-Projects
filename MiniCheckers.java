/*
* Names: Faryal Alizai
* netID: falizai4
* G#: 01364057
* Lecture section: 004
* Lab section: 208
*/
import java.util.ArrayList;

public class MiniCheckers {
//minicheckers class represents a checkers game
    private char[][] board;
    //2d array that represents the game board
    private Player red;
    //player with red checkers
    private Player black;
    //player with black checkers

    public MiniCheckers(Player red, Player black) {
    //contructors for inicalizing minicheckers game + players
        this.red = red;
        this.black = black;
        this.board = new char[][] {
                {'r', '.', 'r', '.', 'r'},
                {'.', '_', '.', '_', '.'},
                {'_', '.', '_', '.', '_'},
                {'.', '_', '.', '_', '.'},
                {'b', '.', 'b', '.', 'b'}
        };
        //initalizes the board with red and black checkers
    }

    public char[][] getBoard() {
    //getter method for board
        return board;
        //returns the board
    }

    public void setBoard(char[][] board) {
    //setter method to update the board
        this.setBoard(board);
    }

    public Player getRed() {
    //getter method for red checkers
        return red;
        //returns red
    }

    public void setRed(Player red) {
    //setter to update red checkers
        this.red = red;
    }

    public Player getBlack() {
    //getter method for black checkers
        return black;
        //returns black
    }

    public void setBlack(Player black) {
    //setter method to update black checkers
        this.black = black;
    }

    public int countChecker(char color) {
    //counts the number of checkers based on their color
        int count = 0;
        for (char[] row : board) {
            for (char piece : row) {
                if (piece == color) {
                    count++;
                //goes through the board and counts the specific colored checkers
                }
            }
        }
        return count;
        //returns the final count
    }

    public boolean checkWin(Player player) {
    //check if the specified player has won
        char opponentChecker = (player == red) ? 'b' : 'r';
        //determin the opponets checker color
        return countChecker(opponentChecker) == 0;
        //check if there are no more opponets
    }

    public boolean checkLose(Player player) {
    //check if specific plater lost
        char opponentChecker = (player == red) ? 'b' : 'r';
        //checks the opponetns color
        return checkWin((player == red) ? black : red) || possibleMoves(player).isEmpty();
        //checks if they won or if theres no others moves
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                res += board[i][j];
            }
            res += "\n";
        }
        return res;
    }

    private MiniCheckers makeClone() {
        /* provided code. You may call this method, but you should NOT modify it */
        MiniCheckers newMiniCheckers = new MiniCheckers(this.red, this.black);
        char[][] newBoard = newMiniCheckers.getBoard();
        char[][] curBoard = this.getBoard();
        for (int i = 0; i < curBoard.length; i++) {
            for (int j = 0; j < curBoard[i].length; j++) {
                newBoard[i][j] = curBoard[i][j];
            //copies elms from current board
            }
        }
        newMiniCheckers.setBoard(newBoard);
        return newMiniCheckers;
    }

    private MiniCheckers movePiece(int startRow, int startCol, int endRow, int endCol) {
        /* provided code. You may call this method, but you should NOT modify it */
        MiniCheckers move = this.makeClone();
        char[][] newBoard = move.getBoard();
        char tmpPiece = newBoard[startRow][startCol];
        //moves checker
        newBoard[startRow][startCol] = '_';
        newBoard[endRow][endCol] = tmpPiece;
        if ((tmpPiece == 'r' && endRow == newBoard.length - 1) || (tmpPiece == 'b' && endRow == 0)) {
            newBoard[endRow][endCol] = Character.toUpperCase(newBoard[endRow][endCol]);
        }
        return move;
    }

    private static void removePiece(char[][] board, int i, int j) {
    //removes checker from specific postion
        /* provided code. You may call this method, but you should NOT modify it */
        board[i][j] = '_';
    }

    private static boolean validIndex(char[][] board, int i, int j) {
    //check if speicifed row + colum are valid
        /* provided code. You may call this method, but you should NOT modify it */
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
        return true;
    }

    private static boolean redCanMoveHere(char[][] board, int startRow, int startCol, int endRow, int endCol) {
    //checks if a red can move to the positon
        /* Your code here! */

        if (!validIndex(board, startRow, startCol) || !validIndex(board, endRow, endCol)) {
        //checks if valid
            return false;
        }

        char piece = board[startRow][startCol];

        if (piece != 'r' || startRow >= endRow || Math.abs(startRow - endRow) != 1 || Math.abs(startCol - endCol) != 1) {
            return false;
        //if piece is a red checker can jump to a specific positon
        }

        if (piece == 'r' || !Character.isUpperCase(board[startRow][startCol])) {
            return endRow - startRow == -1 && board[endRow][endCol] == '_';
        } else {
            return false;
        }
    }

    private static boolean redCanJumpHere(char[][] board, int startRow, int startCol, int endRow, int endCol) {
        /* provided code. You may call this method, but you should NOT modify it */
        if (!validIndex(board, startRow, startCol) || !validIndex(board, endRow, endCol)) return false;
        if (Math.abs(startRow - endRow) != 2 || Math.abs(startCol - endCol) != 2) return false;
        if (board[startRow][startCol] == 'r') {
            if (endRow != startRow + 2) return false;
            if (board[endRow][endCol] != '_') return false;
            if ((endCol == startCol + 2 && (board[startRow + 1][startCol + 1] == 'b' || board[startRow + 1][startCol + 1] == 'B')) ||
                    (endCol == startCol - 2 && (board[startRow + 1][startCol - 1] == 'b' || board[startRow + 1][startCol - 1] == 'B'))) {
                return true;
            } else {
                return false;
            }
        } else if (board[startRow][startCol] == 'R') {
            if (board[endRow][endCol] != '_') return false;
            if (endRow > startRow && endCol > startCol) {
                //down-right
                if (board[startRow + 1][startCol + 1] == 'b' || board[startRow + 1][startCol + 1] == 'B') return true;
                else return false;
            } else if (endRow < startRow && endCol > startCol) {
                //up-right
                if (board[startRow - 1][startCol + 1] == 'b' || board[startRow - 1][startCol + 1] == 'B') return true;
                else return false;
            } else if (endRow > startRow && endCol < startCol) {
                //down-left
                if (board[startRow + 1][startCol - 1] == 'b' || board[startRow + 1][startCol - 1] == 'B') return true;
                else return false;
            } else if (endRow < startRow && endCol < startCol) {
                //up-left
                if (board[startRow - 1][startCol - 1] == 'b' || board[startRow - 1][startCol - 1] == 'B') return true;
                else return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean blackCanMoveHere(char[][] board, int startRow, int startCol, int endRow, int endCol) {
        /* Your code here! */
        if (!validIndex(board, startRow, startCol) || !validIndex(board, endRow, endCol)) {
            return false;
        }

        char piece = board[startRow][startCol];
        char opponentChecker = 'r';

        if (piece != 'b' || startRow <= endRow || Math.abs(startRow - endRow) != 1 || Math.abs(startCol - endCol) != 1) {
            return false;
        }

        if (piece == 'b' && Character.isUpperCase(board[startRow][startCol])) {
            return endRow - startRow == 1 && board[endRow][endCol] == '_';
        } else {
            return false;
        }
    }

    private static boolean blackCanJumpHere(char[][] board, int startRow, int startCol, int endRow, int endCol) {
        /* provided code. You may call this method, but you should NOT modify it */
        if (!validIndex(board, startRow, startCol) || !validIndex(board, endRow, endCol)) return false;
        if (Math.abs(startRow - endRow) != 2 || Math.abs(startCol - endCol) != 2) return false;
        if (board[startRow][startCol] == 'b') {
            if (endRow != startRow - 2) return false;
            if (board[endRow][endCol] != '_') return false;
            if ((endCol == startCol + 2 && (board[startRow - 1][startCol + 1] == 'r' || board[startRow - 1][startCol + 1] == 'R')) ||
                    (endCol == startCol - 2 && (board[startRow - 1][startCol - 1] == 'r' || board[startRow - 1][startCol - 1] == 'R'))) {
                return true;
            } else {
                return false;
            }
        } else if (board[startRow][startCol] == 'B') {
            if (board[endRow][endCol] != '_') return false;
            if (endRow > startRow && endCol > startCol) {
                //down-right
                if (board[startRow + 1][startCol + 1] == 'r' || board[startRow + 1][startCol + 1] == 'R') return true;
                else return false;
            } else if (endRow < startRow && endCol > startCol) {
                //up-right
                if (board[startRow - 1][startCol + 1] == 'r' || board[startRow - 1][startCol + 1] == 'R') return true;
                else return false;
            } else if (endRow > startRow && endCol < startCol) {
                //down-left
                if (board[startRow + 1][startCol - 1] == 'r' || board[startRow + 1][startCol - 1] == 'R') return true;
                else return false;
            } else if (endRow < startRow && endCol < startCol) {
                //up-left
                if (board[startRow - 1][startCol - 1] == 'r' || board[startRow - 1][startCol - 1] == 'R') return true;
                else return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public ArrayList<MiniCheckers> possibleMoves(Player player) {
        /* provided code. You may call this method, but you should NOT modify it */
        char[][] curBoard = this.getBoard();
        ArrayList<MiniCheckers> rv = new ArrayList<MiniCheckers>();
        if (player == this.red) {
            boolean couldJump = false;
            for (int i = 0; i < curBoard.length; i++) {
                for (int j = 0; j < curBoard[i].length; j++) {
                    if (board[i][j] == 'r' || board[i][j] == 'R') {
                        if (redCanJumpHere(board, i, j, i - 2, j - 2)) {
                            MiniCheckers newBoard = movePiece(i, j, i - 2, j - 2);
                            removePiece(newBoard.board, i - 1, j - 1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if (redCanJumpHere(board, i, j, i - 2, j + 2)) {
                            MiniCheckers newBoard = movePiece(i, j, i - 2, j + 2);
                            removePiece(newBoard.board, i - 1, j + 1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if (redCanJumpHere(board, i, j, i + 2, j - 2)) {
                            MiniCheckers newBoard = movePiece(i, j, i + 2, j - 2);
                            removePiece(newBoard.board, i + 1, j - 1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if (redCanJumpHere(board, i, j, i + 2, j + 2)) {
                            MiniCheckers newBoard = movePiece(i, j, i + 2, j + 2);
                            removePiece(newBoard.board, i + 1, j + 1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                    }
                }
            }
            if (!couldJump) {
                for (int i = 0; i < curBoard.length; i++) {
                    for (int j = 0; j < curBoard[i].length; j++) {
                        if (board[i][j] == 'r' || board[i][j] == 'R') {
                            if (redCanMoveHere(board, i, j, i - 1, j - 1)) {
                                rv.add(movePiece(i, j, i - 1, j - 1));
                            }
                            if (redCanMoveHere(board, i, j, i - 1, j + 1)) {
                                rv.add(movePiece(i, j, i - 1, j + 1));
                            }
                            if (redCanMoveHere(board, i, j, i + 1, j - 1)) {
                                rv.add(movePiece(i, j, i + 1, j - 1));
                            }
                            if (redCanMoveHere(board, i, j, i + 1, j + 1)) {
                                rv.add(movePiece(i, j, i + 1, j + 1));
                            }
                        }
                    }
                }
            }
        } else if (player == this.black) {
            boolean couldJump = false;
            //check for jumps first
            for (int i = 0; i < curBoard.length; i++) {
                for (int j = 0; j < curBoard[i].length; j++) {
                    if (board[i][j] == 'b' || board[i][j] == 'B') {
                        if (blackCanJumpHere(board, i, j, i - 2, j - 2)) {
                            MiniCheckers newBoard = movePiece(i, j, i - 2, j - 2);
                            removePiece(newBoard.board, i - 1, j - 1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if (blackCanJumpHere(board, i, j, i - 2, j + 2)) {
                            MiniCheckers newBoard = movePiece(i, j, i - 2, j + 2);
                            removePiece(newBoard.board, i - 1, j + 1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if (blackCanJumpHere(board, i, j, i + 2, j - 2)) {
                            MiniCheckers newBoard = movePiece(i, j, i + 2, j - 2);
                            removePiece(newBoard.board, i + 1, j - 1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                        if (blackCanJumpHere(board, i, j, i + 2, j + 2)) {
                            MiniCheckers newBoard = movePiece(i, j, i + 2, j + 2);
                            removePiece(newBoard.board, i + 1, j + 1);
                            rv.add(newBoard);
                            couldJump = true;
                        }
                    }
                }
            }
            if (!couldJump) {
                for (int i = 0; i < curBoard.length; i++) {
                    for (int j = 0; j < curBoard[i].length; j++) {
                        if (board[i][j] == 'b' || board[i][j] == 'B') {
                            if (blackCanMoveHere(board, i, j, i - 1, j - 1)) {
                                rv.add(movePiece(i, j, i - 1, j - 1));
                            }
                            if (blackCanMoveHere(board, i, j, i - 1, j + 1)) {
                                rv.add(movePiece(i, j, i - 1, j + 1));
                            }
                            if (blackCanMoveHere(board, i, j, i + 1, j - 1)) {
                                rv.add(movePiece(i, j, i + 1, j - 1));
                            }
                            if (blackCanMoveHere(board, i, j, i + 1, j + 1)) {
                                rv.add(movePiece(i, j, i + 1, j + 1));
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Unrecognized player?!");
        }
        return rv;
    }
}