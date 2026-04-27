package game;

import game.entities.*;
import game.ui.*;
import bagel.Window;
import bagel.Input;
import bagel.Keys;
import java.util.Properties;

public class BattleScreen {
  public static final int UP = 1;
  public static final int DOWN = -1;
  public static final int LEFT = -1;
  public static final int RIGHT = 1;
  public static final int DIRECTIONLESS = 0;

  private final PlayerLives playerLives;
  private final Wave wave;
  private final PlayerScore score;
  private final PlayerShip playerShip;
  private final ArrayEntities projectiles;
  private final ArrayEntities enemies;
  private final ArrayEntities explosions;

  public BattleScreen(Properties gameProps) {

    projectiles = new ArrayEntities();
    enemies = new ArrayEntities();
    explosions = new ArrayEntities();

    playerLives = new PlayerLives(gameProps.getProperty("playerLives.image"),
        gameProps.getProperty("playerLives.startPosition"),
        Double.parseDouble(gameProps.getProperty("playerLives.gap")),
        Integer.parseInt(gameProps.getProperty("player.initialLives")));

    wave = new Wave(gameProps.getProperty("wave.text"),
        gameProps.getProperty("wave.pos"),
        gameProps.getProperty("text.font"),
        gameProps.getProperty("text.size"),
        gameProps.getProperty("text.colour"));

    score = new PlayerScore(gameProps.getProperty("score.text"),
        gameProps.getProperty("score.pos"),
        gameProps.getProperty("text.font"),
        gameProps.getProperty("text.size"),
        gameProps.getProperty("text.colour"));

    playerShip = new PlayerShip(gameProps.getProperty("player.image"),
        (double) Window.getWidth() / 2,
        Double.parseDouble(gameProps.getProperty("player.posY")),
        gameProps.getProperty("player.speed"),
        gameProps.getProperty("player.shootCooldown"));

    // Fetch enemyShip's data and loop until cannot find new enemyShip
    for (int i = 0; gameProps.getProperty("enemy." + i + ".arrivalTime") != null; i++) {
      enemies.addEntities(new EnemyShip(gameProps.getProperty("enemy.image"),
          Double.parseDouble(gameProps.getProperty("enemy." + i + ".posX")),
          gameProps.getProperty("enemy." + i + ".movementSpeed"),
          gameProps.getProperty("enemy." + i + ".arrivalTime")));
    }
  }

  public void togglePlayerInvincibility() {
    playerShip.toggleInvincibleMode();
  }

  public void process(Input input, Properties gameProps) {

    if (!playerLives.checkValid()) {
      Window.close();
    }

    if (input.isDown(Keys.A)) {
      playerShip.moveEntity(LEFT);
    }

    if (input.isDown(Keys.D)) {
      playerShip.moveEntity(RIGHT);
    }

    if (input.wasPressed(Keys.SPACE) && !playerShip.countCooldown()) {
      projectiles.addEntities(new Projectile(
          gameProps.getProperty("projectile.image"),
          playerShip.getX(),
          playerShip.getY(),
          gameProps.getProperty("projectile.movementSpeed")));
      playerShip.startCooldown();
    }

    // Handle collision between playerShip and enemyShip
    for (int i = enemies.getSize() - 1; i >= 0; i--) {
      if (playerShip.checkCollision(enemies.getEntity(i))) {
        enemies.removeAt(i);
        if (!playerShip.getInvincibleMode()) {
          playerLives.decrementHealth();
        }
      }
    }

    // Handle collision between projectiles and enemyShip, also invoke explosion
    for (int i = projectiles.getSize() - 1; i >= 0; i--) {
      for (int j = enemies.getSize() - 1; j >= 0; j--) {
        if (enemies.getEntity(j).checkCollision(projectiles.getEntity(i))) {
          explosions.addEntities(new Explosion(gameProps.getProperty("explosion.image"),
              enemies.getEntity(j).getX(),
              enemies.getEntity(j).getY(),
              gameProps.getProperty("explosion.duration")));
          projectiles.removeAt(i);
          enemies.removeAt(j);
          score.incrementScore();
          break;
        }
      }
    }

    enemies.drawEntities(UP);
    explosions.drawEntities(DIRECTIONLESS);
    playerShip.drawEntity();
    projectiles.drawEntities(DOWN);
    wave.drawText(wave.getWave());
    score.drawText(score.getScore());
    playerLives.drawEntity();
    playerShip.countCooldown();
  }

}
