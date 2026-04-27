package game.entities;

import bagel.Window;

public class PlayerShip extends MovingEntity {

  private final double shootCooldown;
  private double countCooldown;
  private boolean invincibleMode;
  private final double leftBound, rightBound;

  public PlayerShip(String pathToImage, double posX, double posY, String movementSpeed, String cooldown) {
    super(pathToImage, posX, posY, movementSpeed);
    shootCooldown = Integer.parseInt(cooldown);
    countCooldown = 0;
    invincibleMode = false;
    leftBound = super.getFigure().getWidth() / 2;
    rightBound = Window.getWidth() - super.getFigure().getWidth() / 2;
  }

  public void startCooldown() {
    countCooldown = shootCooldown;
  }

  public boolean countCooldown() {
    countCooldown -= super.getTimeScale();
    return countCooldown > 0;
  }

  public void toggleInvincibleMode() {
    invincibleMode = !invincibleMode;
  }

  public boolean getInvincibleMode() {
    return invincibleMode;
  }

  @Override
  public boolean checkValid() {
    return super.getX() >= leftBound && super.getX() <= rightBound;
  }

  @Override
  public void moveEntity(int direction) {
    if (checkValid()) {
      super.setX(super.getX() + direction * super.getMovementSpeed());
      super.setIsMoving(true);
    }
    // Make sure that PlayerShip won't move out of bound
    if (!checkValid()) {
      super.setX(super.getX() < leftBound ? leftBound : rightBound);
    }
  }
}
