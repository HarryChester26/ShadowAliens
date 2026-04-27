package game.ui;

public class PlayerScore extends TextDisplay {

  private int numsScore;

  public PlayerScore(String text, String pos, String font, String fontSize, String colour) {
    super(text, pos, font, fontSize, colour);
    numsScore = 0;
  }

  public void incrementScore() {
    numsScore++;
  }

  public int getScore() {
    return numsScore;
  }
}
