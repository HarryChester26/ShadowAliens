package game.entities;

import java.util.ArrayList;

public class ArrayEntities {
  private final ArrayList<Entity> entities = new ArrayList<>();

  public int getSize() {
    return entities.size();
  }

  public Entity getEntity(int index) {
    return entities.get(index);
  }

  public void addEntities(Entity entity) {
    entities.add(entity);
  }

  public void removeAt(int index) {
    entities.remove(index);
  }

  public void drawEntities(int direction) {
    for (int i = entities.size() - 1; i >= 0; i--) {
      entities.get(i).drawEntity();
      // An entity can only move if it is a MovingEntity
      if (entities.get(i) instanceof MovingEntity) {
        ((MovingEntity) entities.get(i)).moveEntity(direction);
      }
      if (!(entities.get(i).checkValid())) {
        entities.remove(i);
      }
    }
  }
}
