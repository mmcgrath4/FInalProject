public class Player {
    private int score;
    private String input;


    public Player() {
        score = 0;
        input = "";
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
