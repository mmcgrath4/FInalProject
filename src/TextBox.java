import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextBox extends JPanel implements ActionListener {
    private JTextField textField;
    private JTextArea textArea;
    private Game g;
    private ImageIcon background;
    private Player p;
    private final String newline = "\n";
    private final int panelWidth = 410;
    private final int panelHeight = 60;
    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 900;
    private final int imageX = 380;
    private final int imageY = 50;
    private final int imageWidth = 750;
    private final int imageHeight = 575;

    public TextBox(Player p) {
        super(new GridBagLayout());
        this.p = p;
        background = new ImageIcon("Resources/background.jpg");

        textField = new JTextField(20);
        textField.addActionListener(this);

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        JLabel label = new JLabel(background);
        this.add(label);
//        label.setLocation(0,0);

//        textArea = new JTextArea(5, 20);
//        textArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(textArea);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);
        setSize(panelWidth, panelHeight);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
//        add(scrollPane);
    }

    public void actionPerformed(ActionEvent evt) {
        p.setInput(textField.getText());
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
//    private static void createAndShowGUI() {
//        //Create and set up the window.
//        JFrame frame = new JFrame("TextDemo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Add contents to the window.
//        frame.add(new TextDemo());
//
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        //Schedule a job for the event dispatch thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
}
