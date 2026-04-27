package game;

import game.ui.TextDisplay;
import java.util.Properties;

public class PausedScreen {
  private final TextDisplay timescaleDisplay;
  private final TextDisplay pausedTitle;
  private final TextDisplay[] controlsList;

  public PausedScreen(Properties gameProps) {
    timescaleDisplay = new TextDisplay(gameProps.getProperty("timescale.text"),
        gameProps.getProperty("timescale.pos"),
        gameProps.getProperty("text.font"),
        gameProps.getProperty("text.size"),
        gameProps.getProperty("text.colour"));

    pausedTitle = new TextDisplay(gameProps.getProperty("pausedTitle.text"),
        Double.parseDouble(gameProps.getProperty("pausedTitle.posY")),
        gameProps.getProperty("text.font"),
        gameProps.getProperty("pausedTitle.size"),
        gameProps.getProperty("text.colour"));

    String[] controlTexts = gameProps.getProperty("controlsList.text").split(",");

    controlsList = new TextDisplay[controlTexts.length];
    double rowGap = Double.parseDouble(gameProps.getProperty("controlsList.rowGap"));

    for (int i = 0; i < controlTexts.length; i++) {
      controlsList[i] = new TextDisplay(controlTexts[i],
          Double.parseDouble(gameProps.getProperty("controlsList.startPosY")) + i * rowGap,
          gameProps.getProperty("text.font"),
          gameProps.getProperty("text.size"),
          gameProps.getProperty("text.colour"));
    }
  }

  public void process() {
    timescaleDisplay.drawText(ShadowAliens.speedRate);
    pausedTitle.drawText();
    for (TextDisplay control : controlsList) {
      control.drawText();
    }
  }
}
