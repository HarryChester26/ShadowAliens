package game.entities;

public class Explosion extends Entity {

  private double duration;

  public Explosion(String pathToImage, double posX, double posY, String duration) {
    super(pathToImage, posX, posY);
    this.duration = Double.parseDouble(duration);
  }

  @Override
  public boolean checkValid() {
    duration -= super.getTimeScale();
    return duration > 0;
  }
}
