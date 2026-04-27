package game.entities;

public class Projectile extends MovingEntity {

  private final double upperBound;

  public Projectile(String pathToImage, double posX, double posY, String movementSpeed) {
    super(pathToImage, posX, posY, movementSpeed);
    upperBound = -super.getFigure().getHeight() / 2;
  }

  @Override
  public boolean checkValid() {
    return super.getY() >= upperBound;
  }

  @Override
  public void moveEntity(int direction) {
    if (checkValid()) {
      super.setIsMoving(true);
      super.setY(super.getY() + direction * super.getMovementSpeed());
    }
  }
}
