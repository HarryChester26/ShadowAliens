package game.entities;

import bagel.DrawOptions;
import bagel.Window;

public class EnemyShip extends MovingEntity {

  private double arrivalTime;
  private final DrawOptions rotation = new DrawOptions();
  private final double underBound;

  public EnemyShip(String pathToImage, double posX, String movementSpeed, String arrivalTime) {
    super(pathToImage, posX, movementSpeed);
    this.arrivalTime = Integer.parseInt(arrivalTime);
    // Rotate the enemy image to its right direction
    rotation.setRotation(Math.PI / 2);
    underBound = Window.getHeight() + super.getFigure().getHeight() / 2;
  }

  public boolean timeToArrival() {
    arrivalTime -= super.getTimeScale();
    return arrivalTime <= 0;
  }

  @Override
  public void drawEntity() {
    super.getFigure().draw(super.getX(), super.getY(), rotation);
  }

  @Override
  public boolean checkValid() {
    return super.getY() <= underBound;
  }

  @Override
  public void moveEntity(int direction) {
    if (timeToArrival() && checkValid()) {
      super.setY(super.getY() + direction * super.getMovementSpeed());
      super.setIsMoving(true);
    }
  }
}
