
public class Game{
    private GameViewer window;
    private Board b;

    public Game(Board b) {
        this.window = new GameViewer(this);
        this.b = b;
    }
}
