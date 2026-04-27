package game.ui;

import bagel.Font;
import bagel.DrawOptions;
import bagel.Window;
import game.utils.Point;

public class TextDisplay {
  private final String text;
  private final Point coordinate;
  private Font textFont;
  private final DrawOptions textColour;

  // Constructor for Wave, PlayerScore, and timescaleDisplay
  public TextDisplay(String text, String pos, String font, String textSize, String colour) {
    textFont = new Font(font, Integer.parseInt(textSize));
    this.text = text;
    String[] positions = pos.split(",");
    coordinate = new Point(Double.parseDouble(positions[0]), Double.parseDouble(positions[1]));
    textFont = new Font(font, Integer.parseInt(textSize));
    String[] textComponentColors = colour.split(",");
    textColour = new DrawOptions().setBlendColour(Double.parseDouble(textComponentColors[0]),
        Double.parseDouble(textComponentColors[1]),
        Double.parseDouble(textComponentColors[2]));
  }

  // Constructor for pausedTitle and controlsList
  public TextDisplay(String text, double posY, String font, String fontSize, String colour) {
    textFont = new Font(font, Integer.parseInt(fontSize));
    this.text = text;
    // Make sure that the text is displayed in the center of the screen
    coordinate = new Point((Window.getWidth() - textFont.getWidth(text)) / 2, posY);
    String[] textComponentColors = colour.split(",");
    textColour = new DrawOptions().setBlendColour(Double.parseDouble(textComponentColors[0]),
        Double.parseDouble(textComponentColors[1]),
        Double.parseDouble(textComponentColors[2]));
  }

  public void drawText() {
    textFont.drawString(text, coordinate.getX(), coordinate.getY(), textColour);
  }

  public void drawText(int value) {
    textFont.drawString(text + " " + value, coordinate.getX(), coordinate.getY(), textColour);
  }
}
