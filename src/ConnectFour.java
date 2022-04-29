
public class ConnectFour {
    private ConnectFourBoard board;
    private ConnectFourBot bot;
    private ConnectFourFrame graphics;

    public ConnectFour(){
        board = new ConnectFourBoard(6, 7);
        bot = new ConnectFourBot();
        graphics = new ConnectFourFrame(board);
    }

    public ConnectFour(int d){
        board = new ConnectFourBoard(6, 7);
        bot = new ConnectFourBot();
        graphics = new ConnectFourFrame(board, d);
    }

    public static void main(String[] args) {
        ConnectFour myGame = new ConnectFour(14);
//        myGame.board.addToken(6);
//        System.out.println(myGame.board);
//        System.out.println(myGame.bot.miniMax(myGame.board, -100, 100, 30));
        while(!myGame.board.isOver()){
            if (!myGame.board.isPlayer1Turn()) {
                myGame.board = ConnectFourBot.chooseBestMove(myGame.board, 8);
                myGame.graphics.getPanel().setBoard(myGame.board);
                myGame.graphics.getPanel().update(myGame.graphics.getPanel().getGraphics());
            }
            myGame.graphics.getPanel().update(myGame.graphics.getPanel().getGraphics());
        }
        myGame.graphics.getPanel().update(myGame.graphics.getPanel().getGraphics());
    }
}
