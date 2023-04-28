import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Board {
    private char[][] grid;
    private ArrayList<char[][]> grids;
    private Image image;
    private Image[] images;
    private static int numBoards = 1;

    public Board() {
        grids = new ArrayList<>();
        makeGrids();
        images = new Image[numBoards];
        int random = (int) (Math.random() * numBoards);
        grid = grids.get(random);
        image = images[random];
    }

    public void makeGrids() {
        char[][] grid1 = {{'H', 'E', 'C', 'I', 'L', 'S', 'J', 'O','F','O','N','I','O','N'},
                        {'X', 'A', 'M', 'P', 'S', 'D', 'K', 'T','S','E','I','R','F','V'},
                        {'S', 'T', 'E', 'L', 'O', 'M', 'I', 'A','R','G','A','N','Z','E'},
                        {'B', 'A', 'C', 'U', 'X', 'D', 'O', 'M','H','F','Q','T','S','L'},
                        {'L', 'F', 'G', 'E', 'M', 'K', 'S', 'O','A','I','B','E','W','P'},
                        {'O', 'H', 'P', 'C', 'L', 'D', 'E', 'T','R','G','E','L','B','P'},
                        {'P', 'M', 'Y', 'U', 'F', 'E', 'V', 'U','Q','H','T','G','N','A'},
                        {'N', 'E', 'W', 'A', 'S', 'L', 'I', 'M','C','Y','S','A','K','E'},
                        {'O', 'F', 'P', 'S', 'C', 'I', 'L', 'R','A','G','Z','U','R','N'},
                        {'C', 'O', 'T', 'P', 'S', 'V', 'O', 'C','R','W','A','P','M','I'},
                        {'A', 'N', 'H', 'S', 'E', 'E', 'T', 'R','I','E','I','P','S','P'},
                        {'B', 'A', 'I', 'N', 'D', 'R', 'J', 'H','P','Z','D','O','K','W'},
                        {'J', 'Z', 'N', 'A', 'S', 'Y', 'B', 'U','Z','E','T','R','I','N'},
                        {'T', 'S', 'U', 'R', 'C', 'L', 'W', 'A','C','R','E','V','O','L'},
                        };
        grids.add(grid1);
    }

    public char[][] getGrid() {return grid;}

    public void makeImages() {
        images[0] = new ImageIcon("Resources/search1.png").getImage();
    }

    public Image getImage() {return image;}
}
