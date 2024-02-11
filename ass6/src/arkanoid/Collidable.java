package arkanoid;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/** Represents an object that be collided ino.
 */
public interface Collidable {
    /** return the "collision shape" of the object.
     @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /** Notify the object that we collided with it at collisionPoint with
     // a given velocity.

     @param collisionPoint point of collision.
     @param currentVelocity the velocity at the moment of collision.
     @param hitter the ball hitting.
     @return the new velocity expected after the hit (based on the force the
     object inflicted on us).
     */
     Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /** draw the collidable on the screen.
     * @param surface the given surface.
     */
     void drawOn(DrawSurface surface);
}
