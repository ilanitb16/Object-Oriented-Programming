package geometry;

import arkanoid.Collidable;

/** Class holding the required information about the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /** Constructs a new CollisionInfo object with the specified collision
     * point and collision object.
     @param collisionPoint The point at which the collision occurs.
     @param collisionObject The collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /** the point at which the collision occurs.
     @return The point of collision.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /** The collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
