package arkanoid;

import geometry.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the environment of the Game.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /** Constructs a new GameEnvironment object with an empty list of
     * collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /** add the given collidable to the environment.
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /** Returns a list of the collidables in the game environment.
     @return a list of all the collidables.
     */
    public  List<Collidable> getCollidables() {
        return this.collidables;
    }

    /** Returns the collision point and object of the closest collidable
     object that intersects with the given trajectory.

     @param trajectory the trajectory to check collisions with.
     @return the collision point and object of the closest collidable
      that intersects with the trajectory.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> collisionList = new ArrayList<>();

        // Check collisions with each of the collidables
        for (Collidable collidable: collidables) {
            Rectangle rect = collidable.getCollisionRectangle();
            Point collisionPoint =
                    trajectory.closestIntersectionToStartOfLine(rect);

            if (collisionPoint != null) {
                collisionList.add(new CollisionInfo(collisionPoint,
                        collidable));
            }
        }

        if (collisionList.isEmpty()) {
            return null;
        }

        double minDistance = Double.MAX_VALUE;
        CollisionInfo closest = null;

        for (CollisionInfo info:collisionList) {
            Point currentPoint = trajectory.start();
            Point collisionPoint = info.collisionPoint();

            double a = Math.pow(currentPoint.getX() - collisionPoint.getX(), 2);
            double b = Math.pow(currentPoint.getY() - collisionPoint.getY(), 2);
            double distance = Math.sqrt(a + b);

            if (distance < minDistance) {
                minDistance = distance;
                closest = info;
            }
        }

        return closest;
    }
}