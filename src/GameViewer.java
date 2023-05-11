import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameViewer implements ActionListener {
    private JFrame frame;
    private JLayeredPane panel;
    private JTextField tf1;
    private JTextField tf2;
    private ImageIcon background;
    private ImageIcon boardImage;
    private Game g;
    private final int WINDOW_WIDTH = 960;
    private final int WINDOW_HEIGHT = 540;
    private final int tf1X = 90;
    private final int tf2X = 573;
    private final int tfY = 436;
    private final int tfWidth = 282;
    private final int tfHeight = 42;
    private final int imageX = 247;
    private final int imageY = 30;
    private final int imageWidth = 466;
    private final int imageHeight = 342;




    public GameViewer(Game g) {
        this.g = g;
        background = new ImageIcon("Resources/background.jpg");
        boardImage = new ImageIcon("Resources/search2.png");
        panel = new JLayeredPane();
        frame = new JFrame("Word Search Game");
        frame.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


        panel.setBounds(0, 0, WINDOW_WIDTH,WINDOW_HEIGHT);

        JLabel bg = new JLabel(background);
        bg.setBounds(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);

        JLabel board = new JLabel(boardImage);
        board.setBounds(imageX,imageY,imageWidth,imageHeight);

        tf1 = new JTextField(20);
        tf1.setBounds(tf1X,tfY,tfWidth, tfHeight);
        tf1.addActionListener(this);
        tf2 = new JTextField(20);
        tf2.setBounds(tf2X,tfY,tfWidth, tfHeight);
        tf2.addActionListener(this);

        panel.add(tf1);
        panel.add(tf2);
        panel.add(board);
        panel.add(bg);
        frame.add(panel);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        g.getP1().setInput(tf1.getText());
        g.getP2().setInput(tf2.getText());
    }
}

