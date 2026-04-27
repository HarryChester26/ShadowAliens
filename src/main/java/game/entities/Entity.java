package game.entities;

import bagel.Image;
import bagel.util.Rectangle;
import game.utils.Point;

public abstract class Entity {
  private final Image figure;
  private final Point coordinate;
  private static double timeScale;

  // Constructor for EnemyShip
  public Entity(String pathToImage, double pos) {
    figure = new Image(pathToImage);
    coordinate = new Point(pos, -figure.getHeight() / 2);
  }

  // Constructor for Projectile, PlayerShip, Explosion, and PlayerLives
  public Entity(String pathToImage, double posX, double posY) {
    figure = new Image(pathToImage);
    coordinate = new Point(posX, posY);
  }

  public void setX(double value) {
    coordinate.setX(value);
  }

  public void setY(double value) {
    coordinate.setY(value);
  }

  public static void setTimeScale(double value) {
    if (value > 0) {
      timeScale = value;
    } else if (value < 0) {
      timeScale = 1.0 / (Math.abs(value) + 1);
    } else {
      timeScale = value;
    }
  }

  public double getX() {
    return coordinate.getX();
  }

  public double getY() {
    return coordinate.getY();
  }

  public double getTimeScale() {
    return timeScale;
  }

  public Image getFigure() {
    return figure;
  }

  public void drawEntity() {
    figure.draw(coordinate.getX(), coordinate.getY());
  }

  // Check if an entity is valid to be drawn (and moved)
  public abstract boolean checkValid();

  // Handle collision between two entities, only if their bounding boxes intersect
  public boolean checkCollision(Entity e) {
    if (e == null || e == this)
      return false;

    // Get the top left coordinates of both entities
    double aX = this.getX() - this.getFigure().getWidth() / 2;
    double aY = this.getY() - this.getFigure().getHeight() / 2;
    double bX = e.getX() - e.getFigure().getWidth() / 2;
    double bY = e.getY() - e.getFigure().getHeight() / 2;

    Rectangle a = new Rectangle(aX, aY, this.getFigure().getWidth(), this.getFigure().getHeight());
    Rectangle b = new Rectangle(bX, bY, e.getFigure().getWidth(), e.getFigure().getHeight());
    return a.intersects(b);
  }
}
