public class Player {
    private int score;
    private String input;
    private int timerX;
    private int timerY;

    public Player(int timerX, int timerY) {
        score = 0;
        input = "";
        this.timerX = timerX;
        this.timerY = timerY;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
