import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ConnectFourPanel extends JPanel {

    ConnectFourBoard board;
    int width, height, circleDiameter, padding, depth;
    JLabel result;



    public ConnectFourPanel(ConnectFourBoard b){
        depth = 8;
        helperConstructor(b);
    }

    public ConnectFourPanel(ConnectFourBoard b, int d){
        depth = d;
        helperConstructor(b);
    }


    private void helperConstructor(ConnectFourBoard b){
        board = b;
        circleDiameter = 60;
        padding = 30;
        width = (circleDiameter + padding) * board.getNumColumns() + 3* padding;
        height = (circleDiameter + padding) * board.getNumRows() + 3* padding;
        setPreferredSize(new Dimension(width, height));
        result = new JLabel("");
        result.setPreferredSize(new Dimension(300, 100));
        result.setVisible(true);
        result.setHorizontalAlignment(SwingConstants.CENTER);
        result.setVerticalAlignment(SwingConstants.CENTER);
        result.setFont(new Font("Serif", Font.PLAIN, 18));
        GridLayout grid = new GridLayout();
        setLayout(grid);
        add(result);

        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                try{
                    if(board.isPlayer1Turn())
                        board.addToken(Integer.parseInt("" + e.getKeyChar()));
                    update(getGraphics());
                }catch(Exception ignored){}
                if(e.getKeyChar() == 's') {
                    board = ConnectFourBot.chooseBestMove(board, depth);
                    update(getGraphics());
                }
                else if(e.getKeyChar() == 'r'){
                    board.addToken(ConnectFourBot.randomMove(board));
                    update(getGraphics());
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        addKeyListener(kl);
        setVisible(true);
        setFocusable(true);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.blue);
        g2d.fillRect(padding, padding, width-2*padding, height-2*padding);
        int x = 3 * padding, y = 2 * padding;
        for(int c = 0; c < board.getNumColumns(); c++){
            g2d.drawString(""+c, x, 20);
            x += circleDiameter + padding;
        }
        x = 2 * padding;
        for(int c = 0; c < board.getNumColumns(); c++){
            for(int r = board.getNumRows() - 1; r >= 0; r--){
                if(board.getBoard().get(c).size() <= r)
                    g2d.setPaint(Color.white);
                else if(board.getBoard().get(c).get(r).equals("X"))
                    g2d.setPaint(Color.red);
                else if(board.getBoard().get(c).get(r).equals("O"))
                    g2d.setPaint(Color.yellow);
                g2d.fillOval(x, y, circleDiameter, circleDiameter);
                y += circleDiameter + padding;
            }
            y = 2 * padding;
            x += circleDiameter + padding;
        }

    }

    public void update(Graphics g){
        int x = 2 * padding, y = 2 * padding;
        Graphics2D g2d = (Graphics2D) g;
        for(int c = 0; c < board.getNumColumns(); c++){
            for(int r = board.getNumRows() - 1; r >= 0; r--){
                if(board.getBoard().get(c).size() <= r)
                    g2d.setPaint(Color.white);
                else if(board.getBoard().get(c).get(r).equals("X"))
                    g2d.setPaint(Color.red);
                else if(board.getBoard().get(c).get(r).equals("O"))
                    g2d.setPaint(Color.yellow);
                g2d.fillOval(x, y, circleDiameter, circleDiameter);
                y += circleDiameter + padding;
            }
            y = 2 * padding;
            x += circleDiameter + padding;

        }
        if (board.isPlayer1Won()) {
            result.setText("Player 1 (Red) Wins");
        }
        else if(board.isPlayer2Won()) {
            result.setText("Player 2 (Yellow) Wins");
        }
        else if(board.isOver())
            result.setText("Tie");

        result.update(g2d);
    }

    public void setBoard(ConnectFourBoard board) {
        this.board = board;
    }
}
