import javax.swing.*;
import java.awt.TextField;

public class GameViewer extends JFrame {
    private Game g;
    private TextField box1;
    private TextField box2;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;

    public GameViewer(Game g) {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("War Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

}
