import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.Timer;

public class Game{
    private GameViewer window;
    private Board[] boards;
    private Board b;
    private Player p1;
    private Player p2;
    private Timer clock;
    private String[] grid;
    private ArrayList<String> words;
    public static final int DICTIONARY_SIZE = 10000;
    public static final String[] DICTIONARY = new String[DICTIONARY_SIZE];
    private static int numBoards = 2;
    private static int targetScore = 5;

    public Game() {

        boards = new Board[numBoards];
        makeBoards();
        int random = (int) (Math.random() * numBoards);
        b = boards[1];
        p1 = new Player();
        p2 = new Player();
        clock = new Timer();
        grid = b.getGrid();
        words = new ArrayList<>();
        window = new GameViewer(this);
    }

    public void playGame() {
        loadDictionary();
        generateWords(grid);
        window.repaint();
        while (p1.getScore() <= targetScore && p2.getScore() <= targetScore) {

        }
    }

    public ArrayList<String> generateWords(String[] grid) {
        checkRows(grid);
        checkCols(grid);
        checkDiagonals(grid);
        return words;
    }

    public void checkRows(String[] grid) {
        for (String row: grid) {
            findWords(row);
        }
    }
    public void checkCols(String[] grid) {
        for (int i = 0; i < b.getCols(); i++) {
            String str = "";
            for (int j = 0; j < b.getRows(); j++) {
                str += grid[j].substring(i, i + 1);
            }
            findWords(str);
        }
    }
    public void checkDiagonals(String[] grid) {
        int col = b.getCols() - 1;
        int row = 0;
        int diagonalLength = 1;
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

    public void findWords(String letters) {
        String word;
        for (int i = 0; i < letters.length(); i ++) {
            word = "";
            for (int j = i; j < letters.length(); j++) {
                word += letters.charAt(j);
                if (binarySearch(word.toLowerCase(Locale.ROOT), DICTIONARY, 0, DICTIONARY_SIZE - 1)) {
                    words.add(word);
                }
            }
        }
    }

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

    public Board getBoard() {
        return this.b;
    }

    public static void main(String[] args){
        Game g = new Game();
        g.playGame();
    }
}
