package game;

import bagel.Input;
import bagel.Keys;

public class DevModes {

  public static void process(Input input, ShadowAliens game) {
    if (input.wasPressed(Keys.R)) {
      game.initialise();
    }

    if (input.wasPressed(Keys.G)) {
      game.increaseSpeedRate();
    }

    if (input.wasPressed(Keys.F)) {
      game.decreaseSpeedRate();
    }

    if (input.wasPressed(Keys.I)) {
      game.togglePlayerInvincibility();
    }
  }
}
