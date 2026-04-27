package game.ui;

public class Wave extends TextDisplay {

  private int numsWave;

  public Wave(String text, String pos, String font, String fontSize, String colour) {
    super(text, pos, font, fontSize, colour);
    numsWave = 1;
  }

  public int getWave() {
    return numsWave;
  }

}
