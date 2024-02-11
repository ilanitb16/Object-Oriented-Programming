package arkanoid;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import geometry.CollisionInfo;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    private BufferedImage bgImage = null;


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
     * @param center  starting point
     * @param environment the environment in which the balls move
     * @param bgImageName bg image
     */
    public Ball(geometry.Point center, String bgImageName,
                GameEnvironment environment) {
        this.environment = environment;
        this.center = center;
        this.color = Color.WHITE;

        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + "\\src\\assets\\images\\" + bgImageName;
        try {
            bgImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.radius = ((BufferedImage) bgImage).getWidth() / 2;
        Random rnd = new Random();

        int speed = (rnd.nextInt(2)) + 8;
        int angel = (rnd.nextInt(5)) + 55;

        Velocity velocity = Velocity.fromAngleAndSpeed(angel, speed);
        setVelocity(velocity);
    }

    /**
     * Constructs a new sprites.Ball with the given center position,
     * radius and color.
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

        int speed = (rnd.nextInt(2)) + 8;
        int angel = (rnd.nextInt(5)) + 55;

        Velocity velocity = Velocity.fromAngleAndSpeed(angel, speed);
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
        int x = (int) center.getX();
        int y = (int) center.getY();

        if (bgImage == null) {
            surface.setColor(color);
            surface.fillCircle(x, y, radius);
        } else {
            surface.drawImage(x, y, bgImage);
        }
    }

    /**
     *Sets the velocity of the ball.
     @param v the velocity to set
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /** Sets the velocity of the ball.
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
        Collidable collisionObject = collisionInfo.collisionObject();

        setVelocity(collisionObject.hit(this,
                collisionPoint, velocity));

        // Set new position
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /** Moves the ball one step.
     */
    public void timePassed() {
        moveOneStep();
    }

    /** Adds the ball to a Game object.
     @param g The Game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /** Removes the ball from a given Game object.
     @param game The Game object which the sprite will be removed from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
