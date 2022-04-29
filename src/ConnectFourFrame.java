import javax.swing.*;
import java.awt.*;

public class ConnectFourFrame extends JFrame {
    private ConnectFourPanel panel;
    public ConnectFourFrame(ConnectFourBoard b){
        panel = new ConnectFourPanel(b);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public ConnectFourFrame(ConnectFourBoard b, int d){
        panel = new ConnectFourPanel(b, d);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public ConnectFourPanel getPanel() {
        return panel;
    }
}
