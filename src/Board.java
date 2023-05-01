import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Board {
    private String[] grid;

    private Image image;

    private int rows;
    private int cols;


    public Board(String[] grid, Image image, int rows, int cols) {
        this.grid = grid;
        this.image = image;
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public String[] getGrid() {return grid;}

    public Image getImage() {return image;}
}
