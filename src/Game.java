import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class Game{
    private GameViewer window;
    private Board b;
    private Player p1;
    private Player p2;
    private Timer clock;
    private char[][] grid;
    private ArrayList<String> words;
    public static final int DICTIONARY_SIZE = 143091;
    public static final String[] DICTIONARY = new String[DICTIONARY_SIZE];

    public Game() {
        window = new GameViewer(this);
        b = new Board();
        p1 = new Player();
        p2 = new Player();
        clock = new Timer();
        grid = b.getGrid();
        words = new ArrayList<>();
    }

    public void playGame() {
        generateWords(grid);
    }

    public ArrayList<String> generateWords(char[][] grid) {
        checkRows(grid);
        checkCols(grid);
        checkDiagonals(grid);
        return words;
    }

    public void checkRows(char[][] grid) {
        for (char[] row: grid) {
            String str = String.valueOf(row);
            findWords("", str);
        }
        for (String word : words) {
            System.out.println(word);
        }
    }
    public void checkCols(char[][] grid) {

    }
    public void checkDiagonals(char[][] grid) {

    }

    public void findWords(String word, String letters) {
        // Adds current combination of letters to words list
        
        words.add(word);
        // Base Case: if full length word has been created it returns
        if (letters.length() == 0) {
            return;
        }
        for (int i = 0; i < letters.length(); i ++) {
            // Recursively calls this method
            // Takes away a letter from letters and adds it to the word
            findWords(word + letters.charAt(i), letters.substring(0,i) + letters.substring(i + 1));
        }
//        for (int i = letters.length() - 1; i > -1; i --) {
//            findWords(word + letters.charAt(i), letters.substring(0,i) + letters.substring(i + 1));
//        }
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

    public static void main(String[] args){
        Game g = new Game();
        g.playGame();
    }
}
