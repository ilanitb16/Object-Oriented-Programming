package arkanoid;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import geometry.CollisionInfo;

import java.awt.Color;
import java.util.Random;

/** The Ball class is responsible representing the ball on the screen.
 */
public class Ball implements Sprite {
    private static final int WIDTH = 800;  // given width
    private static final int HEIGHT = 600; // given height
    private geometry.Point center;
    private int radius = 0;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    /**
     * Constructs a new sprites.Ball with the given center position, radius and color.
     *
     * @param center  starting point
     * @param radius  radius of circle
     * @param color  color of circle
     * @param environment the environment in which the balls move
     */
    public Ball(geometry.Point center, int radius, java.awt.Color color,
                GameEnvironment environment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.environment = environment;
    }

    /**
     * Constructs a new sprites.Ball with the given center position, radius and color.
     *
     * @param x x coordinate starting point
     * @param y y coordinate starting point
     * @param radius  radius of circle
     * @param color of circle
     * @param environment the environment in which the balls move
     */
    public Ball(int x, int y, int radius, Color color, GameEnvironment environment) {
        this.center = new Point(x, y);
        this.radius = radius;
        if (this.color != null) {
            this.color = color;
        } else {
            this.color = new Color(color.getRGB());
        }
        this.environment = environment;

        Random rnd = new Random();

        int speed = (rnd.nextInt(50));
        Velocity velocity = Velocity.fromAngleAndSpeed(60, speed);
        setVelocity(velocity);
    }

    /**
     *Returns the X coordinate of the center.
     *@return the X coordinate of the center of the circle
     */

    public double getX() {
        return center.getX();
    }

    /**
     *Returns the Y coordinate of the center.
     *@return the Y coordinate of the center of the circle
     */
    public double getY() {
        return center.getY();
    }

    /**
     *Returns the radius of this circle.
     *@return the radius of this circle
     */
    public int getSize() {
        return this.radius;
    }

    /**
     *Returns the color of this circle.
     *@return the color of this circle
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     *Returns the velocity of this circle.
     *@return the velocity of this circle
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**Draw the ball on the given DrawSurface.
     * @param surface the given surface.
   */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), radius);
    }

    /**
     *Sets the velocity of the ball.
     @param v the velocity to set
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     Sets the velocity of the ball.
     @param dx horizontal change
     @param dy vertical change
     */
    public void setVelocity(double dx, double dy) {
        if (this.velocity != null) {
            this.velocity.setDx(dx);
            this.velocity.setDy(dy);
        }
        this.velocity = new Velocity(dx, dy);
    }

    /**
     *Moves the ball one step using the current velocity width, height and
     * offset.The ball bounces from the sides and top/bottom of the
     *surface if it hits it. The new position is set post-moving.
     */
    public void moveOneStep() {
        // next position to set the ball to
        geometry.Point e = velocity.applyToPoint(center);
        Line trajectory = new Line(center, e);

        // Check for collisions with the collidable objects in the game
        // environment
        CollisionInfo collisionInfo =
                environment.getClosestCollision(trajectory);

        // no collisions, the ball can be safely moved to the end of the line
        if (collisionInfo == null) {
            this.center = trajectory.end();
            return;
        }

       // else, move the ball "almost" to the point of the collision
        geometry.Point collisionPoint = collisionInfo.collisionPoint();
        setVelocity(collisionInfo.collisionObject().hit(collisionPoint,
                velocity));

        // Set new position
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /** Moves the ball one step.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     Adds the ball to a Game object.
     @param g The Game object.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
