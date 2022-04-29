import java.util.ArrayList;
import java.util.Collections;

public class ConnectFourBot {
    private int depth;
    public ConnectFourBot(){
        depth = 5;
    }

    public static int randomMove(ConnectFourBoard board) {
        ArrayList<Integer> possibleMoves = board.getPossibleMoves();
        if(possibleMoves.isEmpty())
            return -1;
        return possibleMoves.get((int) (Math.random()*possibleMoves.size()));

    }

    public static int miniMax(ConnectFourBoard board, int alpha, int beta, int depth){
        if (depth == 0)
            return evaluate(board);
        else if(board.isPlayer1Turn()){
            int maxEval = -100;
//            if(depth <= 2)
//                System.out.println();
            ArrayList<ConnectFourBoard> children = board.genNextStates();
            Collections.sort(children);
//            System.out.println(children);
            for(ConnectFourBoard b : children){
                int eval = miniMax(b, alpha, beta, depth-1);
                if(eval > maxEval) {
                    maxEval = eval;
                }
                alpha = Math.max(alpha, eval);
                if (beta <= alpha)
                    break;
            }
            if(maxEval < -30)
                return maxEval + 1;
            return maxEval;
        }
        else{
            int minEval = 100;
            ArrayList<ConnectFourBoard> children = board.genNextStates();
            Collections.sort(children);
            for(ConnectFourBoard b : children){
                int eval = miniMax(b, alpha, beta, depth-1);
                if(eval < minEval) {
                    minEval = eval;
                }
                beta = Math.min(beta, eval);
                if (beta <= alpha)
                    break;
            }
            if(minEval > 30)
                return minEval - 1;
            return minEval;
        }
    }

    public static ConnectFourBoard chooseBestMove(ConnectFourBoard board, int depth){
        ArrayList<ConnectFourBoard> boards = board.genNextStates();
        if(boards.isEmpty())
            return board;
        Collections.shuffle(boards);
        ConnectFourBoard bestBoard = boards.get(0);
        int bestEval = miniMax(bestBoard,-100, 100, depth);
        for(ConnectFourBoard b : boards){
           int eval = miniMax(b,-100, 100, depth);
           if (board.isPlayer1Turn() && eval > bestEval){
               bestEval = eval;
               bestBoard = b;
           }
           else if(!board.isPlayer1Turn() && eval < bestEval){
                bestEval = eval;
                bestBoard = b;
            }
        }

        return bestBoard;
    }

    public static int evaluate(ConnectFourBoard board){
        if(board.isPlayer1Won())
            return 100;
        else if(board.isPlayer2Won())
            return -100;
        return 0;
    }


}
