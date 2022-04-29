import java.util.ArrayList;

public class ConnectFourBoard implements Comparable<ConnectFourBoard>{
    private ArrayList<ArrayList<String>> board;
    private int numColumns, numRows;
    private boolean player1Turn;
    private boolean player1Won;
    private boolean player2Won;
    private boolean isOver;

    public ConnectFourBoard(){
        numRows = 6;
        numColumns = 7;
        board = new ArrayList<>();
        clearBoard();
        player1Turn = true;
        player1Won = false;
        player2Won = false;
        isOver = false;
    }

    public ConnectFourBoard(int r, int c){
        numRows = r;
        numColumns = c;
        board = new ArrayList<>();
        clearBoard();
        player1Turn = true;
        player1Won = false;
        player2Won = false;
        isOver = false;
    }

    public ConnectFourBoard(ConnectFourBoard other){
        numRows = other.numRows;
        numColumns = other.numColumns;
        board = new ArrayList<>();
        for(ArrayList<String> column : other.board){
            board.add(new ArrayList<>(column));
        }
        player1Turn = other.player1Turn;
        player1Won = other.player1Won;
        player2Won = other.player2Won;
        isOver = other.isOver;
    }

    public void clearBoard(){
        for(int i = 0; i < numColumns; i++)
            board.add(new ArrayList<>(numRows));
    }

    public String toString(){
        StringBuilder stringBoard = new StringBuilder();
        for(int r = numRows - 1; r >= 0 ; r--){
            if (r != numRows)
                stringBoard.append("\n");
            stringBoard.append("—————————————————————————————\n|");
            for(int c = 0; c < numColumns; c++) {
                if(board.get(c).size() <= r) {
                    stringBoard.append("   |");
                }
                else{
                    stringBoard.append(" ").append(board.get(c).get(r)).append(" |");
                }
            }

        }
        stringBoard.append("\n—————————————————————————————");
        return stringBoard.toString();
    }

    public void addToken(int column){
        if(column > numColumns - 1 || column < 0 || board.get(column).size() >= numRows || isOver)
            return;
        if (player1Turn) {
            board.get(column).add("X");
            player1Won = playerVictory("X");

        }
        else {
            board.get(column).add("O");
            player2Won = playerVictory("O");
        }
        player1Turn = !player1Turn;
        isOver = player1Won || player2Won || getPossibleMoves().isEmpty();
    }

    private boolean ascendingDiagCheck(String player, int r, int c){
        int count = 0;
        while(r + count >= 3 && c + count >= 3){
            if(board.get(c).size() <= r || !board.get(c).get(r).equals(player))
                count = 0;
            else {
                count++;
                if (count == 4)
                    return true;
            }
            r--;
            c--;
        }
        return false;
    }

    private boolean descendingDiagCheck(String player, int r, int c){
        int count = 0;
        while(r + count >= 3 && 6 - c + count >= 3){

            if(board.get(c).size() <= r || !board.get(c).get(r).equals(player))
                count = 0;
            else {
                count++;
                if (count == 4)
                    return true;
            }
            r--;
            c++;
        }
        return false;
    }

    public boolean playerVictory(String player){
        int count = 0;
        // Horizontal Check
        for(int r = 0; r < numRows; r++) {
            for(int c = numColumns - 1; c + count >= 3; c--){
                if(board.get(c).size() <= r || !board.get(c).get(r).equals(player))
                    count = 0;
                else {
                    count++;
                    if (count == 4)
                        return true;
                }
            }
        }
        // Vertical Check
        for(int c = 0; c < numColumns; c++) {
            for(int r = numRows - 1; r + count >= 3; r--){
                if(board.get(c).size() <= r || !board.get(c).get(r).equals(player))
                    count = 0;
                else {
                    count++;
                    if (count == 4)
                        return true;
                }
            }
        }

        // Ascending Diagonal
        for(int r = numRows - 1; r >= 3; r--){
            if(ascendingDiagCheck(player, r, numColumns-1))
                return true;
        }
        for(int c = numColumns - 1; c >= 3; c--){
            if(ascendingDiagCheck(player, numRows - 1, c))
                return true;
        }

        // Descending Diagonal
        for(int r = numRows - 1; r >= 3; r--){
            if(descendingDiagCheck(player, r, 0))
                return true;
        }
        for(int c = 0; c <= 4; c++){
            if(descendingDiagCheck(player, numRows - 1, c))
                return true;
        }
        return false;
    }

    public ArrayList<Integer> getPossibleMoves(){
        ArrayList<Integer> moves = new ArrayList<>();
        if (isOver)
            return moves;
        for(int i = 0; i < numColumns; i++){
            if(board.get(i).size() < numRows)
                moves.add(i);
        }
        return moves;
    }

    public boolean isPlayer1Turn() {
        return player1Turn;
    }

    public boolean isPlayer1Won() {
        return player1Won;
    }

    public boolean isPlayer2Won() {
        return player2Won;
    }

    public boolean isOver(){
        return isOver;
    }

    public ArrayList<ConnectFourBoard> genNextStates(){
        ArrayList<ConnectFourBoard> nextStates = new ArrayList<>();
        for(Integer i : getPossibleMoves()){
            ConnectFourBoard newBoard = new ConnectFourBoard(this);
            newBoard.addToken(i);
            nextStates.add(newBoard);
        }
        return nextStates;
    }

    public int compareTo(ConnectFourBoard o) {
        if(player1Turn)
            return ConnectFourBot.evaluate(this) - ConnectFourBot.evaluate(o);
        return ConnectFourBot.evaluate(o) - ConnectFourBot.evaluate(this);
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getNumRows() {
        return numRows;
    }

    public ArrayList<ArrayList<String>> getBoard() {
        return board;
    }
}
