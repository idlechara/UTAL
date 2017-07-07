package trainer;

import engine.Board;
import engine.Move;

/**
 * Created by rjerez1992 on 17-10-2015.
 */
public class Tournament {
    //Current individual that evaluates the board
    static Individual currentInd;
    //Bounds of the Negamax
    static double upperBound = Double.MAX_VALUE;
    static double lowerBound = Double.MIN_VALUE;
    //How many movements to look-ahead
    static int lookAhead = 2;

    /**
     * Matches two individuals
     * @param ind1 individual 1
     * @param ind2 individual 2
     * @return best individual
     */
    public static Individual Match(Individual ind1, Individual ind2){
        System.out.println("Starting  match");
        //Board of the match
        Board board = new Board();

        while (true){
            //White moves
            currentInd = ind1;
            board = makeMove(board);
            //Checks if the game is over and returns the winner
            if (board.isCheckMate() || board.isStalemate() || board.getValidMoves().length < 1){
                return ind1;
            }

            //Black moves
            currentInd = ind2;
            board = makeMove(board);
            //Checks if the game is over and returns the winner
            if (board.isCheckMate() || board.isStalemate() || board.getValidMoves().length < 1){
                return ind2;
            }
        }
    }

    /**
     * Makes a move on the given board
     * @param b current board
     * @return board after the move was done
     */
    static public Board makeMove(Board b){
        //Valid moves
        Move[] moves = b.getValidMoves();

        //Param
        Board newBoard;
        double responseValue;
        double lastValue = 0;
        Move lastMove = null;

        //Runs an evaluation for every possible move and gets the better one
        for (Move m : moves){
            newBoard = b.clone();
            newBoard.makeMove(m);
            //Fast mode uses this evaluation (if gonna use it, please comment the negamax response value)
            //responseValue = currentInd.getResponse(newBoard);

            //Good deep mode
            responseValue = Negamax(newBoard, lookAhead, lowerBound, upperBound, 1);
            //Saves the best move according to its value
            if (responseValue > lastValue){
                lastMove = m;
                lastValue = responseValue;
            }

        }
        if (lastMove == null){
            lastMove = moves[0];
        }

        //Returns the new board
        b.makeMove(lastMove);
        return b;
    }

    /**
     * Runs a Negamax with Look-Ahead Alpha-Beta Prunning
     * @param b Board
     * @param depth Look ahead depth
     * @param alpha Upperbound
     * @param beta Lowerbound
     * @param color color
     * @return Value of the given board
     */
    public static double Negamax(Board b, int depth, double alpha, double beta, double color){
        if (depth==0 || b.isCheckMate() || b.isStalemate() || b.getValidMoves().length == 0){
            return color*currentInd.getResponse(b);
        }

        double val = 0;
        Board newBoard;
        Move[] moves = b.getValidMoves();

        for (Move m : moves){
            newBoard = b.clone();
            newBoard.makeMove(m);
            val = -Negamax(newBoard, depth-1, -beta, -alpha, -color);
            alpha = Math.max(alpha, val);
            if (alpha >= beta){
                break;
            }
        }

        //Returns alpha
        return alpha;
    }
}