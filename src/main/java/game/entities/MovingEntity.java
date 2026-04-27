package game.entities;

public abstract class MovingEntity extends Entity {
  private final double movementSpeed;
  private boolean isMoving;

  // Constructor for EnemyShip
  public MovingEntity(String pathToImage, double pos, String movementSpeed) {
    super(pathToImage, pos);
    this.movementSpeed = Double.parseDouble(movementSpeed);
    isMoving = false;
  }

  // Constructor for PlayerShip, Projectile, Explosion
  public MovingEntity(String pathToImage, double posX, double posY, String movementSpeed) {
    super(pathToImage, posX, posY);
    this.movementSpeed = Double.parseDouble(movementSpeed);
    isMoving = false;
  }

  public double getMovementSpeed() {
    return movementSpeed * super.getTimeScale();
  }

  public boolean checkIsMoving() {
    return isMoving;
  }

  public void setIsMoving(boolean value) {
    isMoving = value;
  }

  public abstract void moveEntity(int direction);

  @Override
  public boolean checkCollision(Entity e) {

    // A moving entity cannot collide with another entity if it is not moving.
    if (!this.isMoving) {
      return false;
    }

    // Handle collision between two moving entities
    if (e instanceof MovingEntity) {
      return super.checkCollision(e) && ((MovingEntity) e).checkIsMoving();
    }

    return super.checkCollision(e);
  }
}
