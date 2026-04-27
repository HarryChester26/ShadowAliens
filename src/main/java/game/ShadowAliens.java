package game;

import java.util.Properties;
import game.entities.Entity;
import bagel.*;

/**
 * Main game class that manages initialising the screens and game objects
 */
public class ShadowAliens extends AbstractGame {

  public static final int PAUSED = 0;
  public static final int INITIAL_SPEED_RATE = 1;

  private static Properties gameProps;
  public static double screenWidth;
  public static double screenHeight;
  public static int speedRate;
  private boolean screenPaused;

  private BattleScreen battleScreen;
  private PausedScreen pausedScreen;

  public ShadowAliens(Properties gameProps) {
    super(Integer.parseInt(gameProps.getProperty("window.width")),
        Integer.parseInt(gameProps.getProperty("window.height")),
        "Shadow Aliens");

    ShadowAliens.gameProps = gameProps;
    screenWidth = Integer.parseInt(gameProps.getProperty("window.width"));
    screenHeight = Integer.parseInt(gameProps.getProperty("window.height"));

    String[] backgroundColours = gameProps.getProperty("background.colour").split(",");
    Window.setClearColour(
        Double.parseDouble(backgroundColours[0]),
        Double.parseDouble(backgroundColours[1]),
        Double.parseDouble(backgroundColours[2]));

    initialise();
  }

  public void initialise() {
    speedRate = INITIAL_SPEED_RATE;
    screenPaused = false;
    battleScreen = new BattleScreen(gameProps);
    pausedScreen = new PausedScreen(gameProps);
  }

  // Ignore speedRate = 0 as freezing entities is only valid for paused screen
  public void increaseSpeedRate() {
    speedRate = speedRate == -1 ? 1 : speedRate + 1;
  }

  public void decreaseSpeedRate() {
    speedRate = speedRate == 1 ? -1 : speedRate - 1;
  }

  public void togglePlayerInvincibility() {
    battleScreen.togglePlayerInvincibility();
  }

  /**
   * Run and render the next frame.
   * 
   * @param input The current mouse/keyboard input.
   */
  @Override
  protected void update(Input input) {
    battleScreen.process(input, gameProps);
    DevModes.process(input, this);

    if (input.wasPressed(Keys.ESCAPE)) {
      screenPaused = !screenPaused;
    }

    if (screenPaused) {
      Entity.setTimeScale(PAUSED);
      pausedScreen.process();
    } else {
      Entity.setTimeScale(speedRate);
    }
  }

  public static void main(String[] args) {
    Properties gameProps = IOUtils.readPropertiesFile(System.getProperty("gameData"));
    ShadowAliens game = new ShadowAliens(gameProps);
    game.run();
  }

}
