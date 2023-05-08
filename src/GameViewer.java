import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    private Game g;
    private TextField box1;
    private TextField box2;
    private Image background;
    private Board b;
    private JPanel panel;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final int imageX = 380;
    private final int imageY = 50;
    private final int imageWidth = 750;
    private final int imageHeight = 575;

    public GameViewer(Game g) {
        this.g = g;
        box1 = new TextField("", 20);
        box2 = new TextField("", 20);
        panel = new JPanel();

        background = new ImageIcon("Resources/background.jpg").getImage();
        b = g.getBoard();

//        this.add(box1);
//        this.add(box2);
        this.add(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Word Search Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawImage(b.getImage(), imageX, imageY, imageWidth, imageHeight, this);

    }

}
