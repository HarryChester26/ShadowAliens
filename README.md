# SWEN20003 Semester 1, 2026
# Project 1
# Shadow Aliens

## Running Instructions
* The project's structure use SDK Amazon Corretto 25.0.2 -aarch64 with language level 25. 
* In the run configuration, VM options is set to -DgameData=gameData.properties and main class: game.ShadowAliens
* Working directory is set to /path/to/SWEN20003-Project1-Skeleton.2026.APR.8
## Assumptions
* Collision between projectiles and enemy ships only occurs when both entities appear on the window.
* Timescale with value smaller than 1 is displayed by negative numbers. For example: 1/2 is -1, 1/3 is -2, ...
* The wave of the game is always 1, hence, there isn't any method to increment the wave value
* Top window screen is called upper bound and vice versa.
* An entity will be removed if its pixel is totally off the screen (right after that phenomena happens)
## AI Statement
* I admit that I used AI for brainstorming and developing my idea, making it more concrete. However, all the coding parts are made by myself.
* The AI model I used is Gemini.
## Code References
## Design Report
### OOP
* Overloading: In the entities package, the file Entity.java from lines 13 to 26 uses constructor overloading to handle different initialization based on the input type. Some entities are provided with both x,y coordinates but entity like EnemyShip only have x coordinate and y coordinate are set to default value.
* Encapsulation: Also in the file Entity.java, all instance variables are set to private, and they can only be accessed through public methods like getX, getY, getFigure, etc.
* Polymorphism: In the file Entity.java, Entity class (parent class) is defined as an abstract class with abstract method checkValid (lines 76). This method will be overridden in many subclasses (PlayerShip, EnemyShip, Projectile, Explosion) with their own valid conditions to be displayed on the window. We can see the use of this technique in the ArrayEntities class in the drawEntities method.
### Design choice
* In my opinion, the decision of creating another class MovingEntity extended from the parent class Entity is the most impactful to my over design. This way will explicitly denote the difference between fixed entities and moving entities. 
* The parent class entity will have all the common features that an entity will have such as: image, coordinate attributes and getter, setter, drawing methods. Then I create another subclass MovingEntity extended 
from the parent class Entity. A moving entity will have movement speed attribute with an abstract moving method. All entities like EnemyShip, PlayerShip, Projectile will be the subclass of this class, with their own movement (override the moving method). Unlike fixed entities such as: Explosion and PlayerLives, they will be the subclass of the parent class Entity with no movement speed. 
* To achieve this, I can move the movement speed and moving method to the Entity class and set the movement speed of fixed entities to zero. Additionally, I can implement an interface called CanMove to make my entities move as the way I want.
* However, both of these designs are not efficient as they will lead to redundant use of attribute and method and wrong definition of an interface. By creating another subclass (MovingEntity) and treat it as a parent class, my project will have better structure and more readable. Besides, it's easier to debug when you only want to change moving entities but keep the fixed one intact.
## Design Report References
* None