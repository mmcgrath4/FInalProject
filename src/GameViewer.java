import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class GameViewer extends JFrame implements ActionListener  {
    private JLayeredPane panel;
    private JTextField tf1;
    private JTextField tf2;
    private ImageIcon background;
    private ImageIcon boardImage;
    private Game game;
    private boolean inputReceived;
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
    private final int timer1X = 200;
    private final int timerY = 525;
    private final int timer2X = 685;
    private final int scoreX = 450;
    private final int scoreY = 480;
    private final int messageX = 20;
    private final int messageY = 250;
    private final int turnX = 780;


    public GameViewer(Game g) {
        game = g;
        inputReceived = false;
        background = new ImageIcon("Resources/background.jpg");
        boardImage = new ImageIcon("Resources/search2.png");
        panel = new JLayeredPane();
        initFrame();
        panel.setBounds(0, 0, WINDOW_WIDTH,WINDOW_HEIGHT);
        initTextBoxes();
        initImages();
        this.add(panel);
        this.setVisible(true);
        createBufferStrategy(2);
    }

    // Initializes the JFrame
    public void initFrame() {
        this.setTitle("Word Search Game");
        this.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
    }

    // Creates and initializes the text boxes
    public void initTextBoxes() {
        tf1 = new JTextField(20);
        // Sets size of text field
        tf1.setBounds(tf1X,tfY,tfWidth, tfHeight);
        tf1.addActionListener(this);
        tf2 = new JTextField(20);
        tf2.setBounds(tf2X,tfY,tfWidth, tfHeight);
        tf2.addActionListener(this);
        // Adds text fields to the layered pane
        panel.add(tf1);
        panel.add(tf2);
    }

    // Initializes the images in the form of JLabels and adds them to the layered pane
    public void initImages() {
        JLabel bg = new JLabel(background);
        bg.setBounds(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
        JLabel board = new JLabel(boardImage);
        board.setBounds(imageX,imageY,imageWidth,imageHeight);
        panel.add(board);
        panel.add(bg);
    }

    // Called when user hits return to submit their answer
    @Override
    public void actionPerformed(ActionEvent e) {
        inputReceived = true;
        game.getP1().setInput(tf1.getText());
        tf1.setText("");
        game.getP2().setInput(tf2.getText());
        tf2.setText("");
    }

    // Buffer Strategy to help with timer animation
    public void paint(Graphics g) {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public void myPaint(Graphics g) {
        super.paint(g);
        drawTimers(g);
        drawScore(g);
        drawMessage(g);
        drawTurn(g);
    }

    // Draws the timers underneath the text boxes
    public void drawTimers(Graphics g) {
        coverTimers(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 18));
        String timer1;
        String timer2;
        String time = String.valueOf(game.getTurnTime());
        if (game.getTurnTime() < 10) {
            time = "0" + time;
        }
        if (game.getCurrentUser().equals(game.getP1())) {
            timer1 = "0 : " + time;
            timer2 = "0 : 00";
        }
        else {
            timer1 = "0 : 00";
            timer2 = "0 : " + time;
        }
        g.drawString(timer1, timer1X, timerY);
        g.drawString(timer2, timer2X, timerY);
    }

    // Resets the space with the timers so the strings are not drawn on top of each other
    public void coverTimers(Graphics g) {
        g.setColor(new Color(111,168,220));
        g.fillRect(0, timerY, WINDOW_WIDTH, tfHeight);
    }

    // Draws the score of the game
    public void drawScore(Graphics g) {
        String score = game.getP1().getScore() + " - " + game.getP2().getScore();
        g.drawString(score, scoreX, scoreY);
    }

    // Draws message after user has provided input on the left hand side of the frame
    public void drawMessage(Graphics g) {
        if (inputReceived) {
            String message = "Nope. Next person.";
            if (game.isRightAnswer()) {
                message = "Correct!";
            }
            g.setFont(new Font("Verdana", Font.BOLD, 20));
            g.drawString(message, messageX, messageY);
        }
    }

    // Prints who's turn it is on the right hand side of the frame
    public void drawTurn(Graphics g) {
        String turn = "Player 2's turn";
        if (game.getCurrentUser().equals(game.getP1())) {
            turn = "Player 1's turn";
        }
        g.drawString(turn, turnX, messageY);
    }

    public boolean getInputReceived() {
        return inputReceived;
    }

    public void setInputReceived(boolean b) {
        inputReceived = b;
    }


}

