//By Mikey McGrath
// May 2023

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Game implements ActionListener {
    private GameViewer window;
    private Board[] boards;
    private Board b;
    private Player p1;
    private Player p2;
    private int turnTime;
    private String[] grid;
    private ArrayList<String> words;
    private ArrayList<String> used;
    private Player currentUser;
    private boolean rightAnswer;
    private boolean gameOver;
    private Player winner;
    public static final int DICTIONARY_SIZE = 10001;
    public static final String[] DICTIONARY = new String[DICTIONARY_SIZE];
    private static int numBoards = 2;
    private static int targetScore = 3;
    private static int TIME_LIMIT = 30;
    private static final int SLEEP_TIME = 1000;

    public Game() {
        boards = new Board[numBoards];
        makeBoards();
        b = boards[1];
        p1 = new Player();
        p2 = new Player();
        turnTime = TIME_LIMIT;
        grid = b.getGrid();
        words = new ArrayList<>();
        used = new ArrayList<>();
        currentUser = p1;
        rightAnswer = false;
        gameOver = false;
        winner = null;
        window = new GameViewer(this);
    }

    public void playGame() throws InterruptedException {
        loadDictionary();
        generateWords(grid);
        // Starts the actionPerformed method and calls it every second
        Timer clock = new Timer(SLEEP_TIME, this);
        clock.start();
        // Runs until one user has one the game (reached the target score)
        while (!gameOver) {
            rightAnswer = false;
            window.repaint();
            // Proceeds once user has inputted something
            if (window.getInputReceived()) {
                System.out.println("valid");
                if (isValidWord(currentUser.getInput())) {
                    rightAnswer = true;
                    // Adds a point if correct
                    currentUser.increaseScore();
                    window.repaint();
                    // Makes it so this word cannot be reused
                    used.add(currentUser.getInput());
                }
                switchUser();
                resetTimer();
                // Waits so the message can be displayed for 3 seconds
                Thread.sleep(2000);
            }
            window.setInputReceived(false);
            if (p1.getScore() == targetScore || p2.getScore() == targetScore) {
                gameOver = true;
            }
        }
        switchUser();
        winner = currentUser;
        window.repaint();
    }

    // Generates all the words in a given 2D array of chars (or array of Strings)
    public void generateWords(String[] grid) {
        checkRows(grid);
        checkCols(grid);
        checkDiagonals(grid);
    }

    // Checks the rows for possible words
    public void checkRows(String[] grid) {
        for (String row: grid) {
            findWords(row);
        }
    }

    // Checks columns for possible words
    public void checkCols(String[] grid) {
        // Traverses columns first instead of rows
        for (int i = 0; i < b.getCols(); i++) {
            String str = "";
            for (int j = 0; j < b.getRows(); j++) {
                str += grid[j].substring(i, i + 1);
            }
            findWords(str);
        }
    }

    // Checks diagonals for possible words
    public void checkDiagonals(String[] grid) {
        int col = b.getCols() - 1;
        int row = 0;
        int diagonalLength = 1;
        // Starts in the top right corner and traverses left, counting each diagonal from left to right
        while (col > -1 && row < b.getRows()) {
            row = 0;
            String str = "";
            for (int k = 0; k < diagonalLength; k++) {
                str += grid[row].substring(col, col + 1);
                col++;
                row++;
            }
            findWords(str);
            col -= diagonalLength + 1;
            diagonalLength ++;
        }

        // Starts in the bottom left corner and traverses up
        row = b.getRows() - 1;
        col = 0;
        diagonalLength = 1;
        while (row > -1 && col < b.getCols()) {
            col = 0;
            String str = "";
            for (int k = 0; k < diagonalLength; k++) {
                str += grid[row].substring(col, col + 1);
                row++;
                col ++;
            }
            findWords(str);
            row -= diagonalLength + 1;
            diagonalLength ++;
        }
    }

    // Finds all of the words in a given string
    public void findWords(String letters) {
        String word;
        for (int i = 0; i < letters.length(); i ++) {
            word = "";
            for (int j = i; j < letters.length(); j++) {
                word += letters.charAt(j);
                // Checks for the word in the dictionary file using binary search
                if (binarySearch(word.toLowerCase(Locale.ROOT), DICTIONARY, 0, DICTIONARY_SIZE - 1) && word.length() > 2) {
                    words.add(word.toLowerCase(Locale.ROOT));
                }
            }
        }
    }

    // Looks for the word in the dictonary
    public boolean binarySearch(String word, String[] dictionary, int low, int high) {
        // Base Case: returns false if the word has not been found and the whole dictionary has been searched
        if (low > high) {
            return false;
        }
        int med = (high + low) / 2;
        // Base Case: returns true if the word has been found
        if (dictionary[med].equals(word)) {
            return true;
        }
        // Sets new dictionary to the first half if the middle word is greater than the target
        if (dictionary[med].compareTo(word) > 0) {
            high = med - 1;
        }
        // Sets new dictionary to the second half if the middle word is less than the target
        else {
            low = med + 1;
        }
        // Recursively searches the new half of the dictionary
        return binarySearch(word, dictionary, low, high);
    }

    // Checks if user input is in the word search
    private boolean isValidWord(String word) {
        for (String str : words) {
            // Also makes sure that the word has not been used before
            if (str.equals(word) && !used.contains(word)) {
                return true;
            }
        }
        return false;
    }

    // Downloads the dictionary as an array of strings
    public static void loadDictionary() {
        Scanner s;
        File dictionaryFile = new File("Resources/dictionary.txt");
        try {
            s = new Scanner(dictionaryFile);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open dictionary file.");
            return;
        }
        int i = 0;
        while(s.hasNextLine()) {
            DICTIONARY[i++] = s.nextLine();
        }
    }

    // Manually downloads all the boards and their respective images
    // Only two at this point
    public void makeBoards() {
        String[] grid1 = {"HECILSJOFONION", "XAMPSDKTSEIFRV", "STELOMIARGANZE", "BACUXDOMHFQTSL",
                "LFGEMKSOAIBEWP", "OHPCLDETRGELBP", "PMYUFEVUQHTGNA", "NEWASLIMCYSAKE",
                "OFPSCILRAGZURN", "COTPSVOCRWAPMI", "ANHSEETRIEIPSP", "BAINDRJHPZDOKW",
                "JZNASYBUZETRIN", "TSURCLWACREVOL"};
        Image image1 = new ImageIcon("Resources/search1.png").getImage();
        boards[0] = new Board(grid1, image1, grid1.length, grid1[0].length());
        Image image2 = new ImageIcon("Resources/search2.png").getImage();
        String[] grid2 = {"HCLSGNIFADIOPML", "TAPULPRESENTSJU", "BNIXKJLMOTYRAJR", "ODRMCNEASHBVNFD",
                "NYPDGFTCJMQZTWS", "RCSNOWMANRTDAGC", "JATSETRUCSBRIPL", "SNOLHFWKDTNOEKK",
                "WECPVONBJAHCTEB", "SOKXDSXGYRBVMKN", "ELIHWABQTIQLAXC", "ZCNEDRUDOLPHYEI",
                "MBGACFEUYVEZRSW", "NDWOTIZHSGATDYF"};
        boards[1] = new Board(grid2, image2, grid2.length, grid2[0].length());
    }

    // Changes the currentUser variable
    public void switchUser() {
        if (currentUser.equals(p1)) {
            currentUser = p2;
        }
        else {
            currentUser = p1;
        }
    }
    // Resets the timer to 30 seconds
    public void resetTimer() {
        turnTime = TIME_LIMIT;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public Player getCurrentUser() {
        return currentUser;
    }

    public int getTurnTime() {
        return turnTime;
    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getWinner() {
        return winner;
    }

    // This handles the timers, as it is called every 1 second and decrements the timer
    @Override
    public void actionPerformed(ActionEvent e) {
        turnTime --;
        if (turnTime == 0) {
            switchUser();
            resetTimer();
        }
        window.repaint();
    }

    public static void main(String[] args) throws InterruptedException {
        Game g = new Game();
        g.playGame();
    }


}
