package game.entities;

public class PlayerLives extends Entity {
  private int initialLives;
  private final double gapSize;

  public PlayerLives(String pathToImage, String coordinate, double gapSize, int totalLives) {
    String[] position = coordinate.split(",");
    super(pathToImage, Double.parseDouble(position[0]), Double.parseDouble(position[1]));
    initialLives = totalLives;
    this.gapSize = gapSize;
  }

  public void decrementHealth() {
    if (initialLives > 0) {
      initialLives--;
    }
  }

  @Override
  public void drawEntity() {
    for (int i = 0; i < initialLives; i++) {
      super.getFigure().draw(super.getX() + (i * gapSize), super.getY());
    }
  }

  @Override
  public boolean checkValid() {
    return initialLives > 0;
  }
}
