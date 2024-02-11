package arkanoid;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.Color;

/** The Block class is responsible representing the blocks on the screen.
 */
public class Block implements Collidable, Sprite {
    private static final Color BORDER_COLOR = Color.BLACK;
    private static final Color DEFAULT_COLOR = new Color(147, 162, 255);
    private geometry.Rectangle rect;
    private Color color;

    /** Constructs a block with a default color of gray.
     @param x the x coordinate of the block's upper left corner
     @param y the y coordinate of the block's upper left corner
     @param width the width of the block
     @param height the height of the block
     */
    public Block(double x, double y, double width, double height) {
        rect = new geometry.Rectangle(new geometry.Point(x, y), width, height);
        this.color = DEFAULT_COLOR;
    }

    /** Constructs a block with a specified color.
     @param x the x coordinate of the block's upper left corner
     @param y the y coordinate of the block's upper left corner
     @param width the width of the block
     @param height the height of the block
     @param color the color of the block
     */
    public Block(double x, double y, double width, double height, Color color) {
        rect = new geometry.Rectangle(new geometry.Point(x, y), width, height);
        this.color = color;
    }

    /**Returns the rectangle shape of the block.
     @return the rectangle shape of the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /** Calculates and returns the new velocity of the ball after it collides
     *  with the block. The new velocity depends on the collision point and
     *  the current velocity of the ball.

     @param collisionPoint the point of collision
     @param currentVelocity the current velocity of the ball
     @return the new velocity of the ball post-collision
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        // Check if ball hits the left size or the right size
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        double leftX = this.rect.getUpperLeft().getX();
        double rightX = this.rect.getUpperLeft().getX() + this.rect.getWidth();

        double topY = this.rect.getUpperLeft().getY();
        double bottomY = this.rect.getUpperLeft().getY() + this.rect.getHeight();

        // if the collision point is on one of the sides of the rectangle
        if (collisionPoint.getX() == leftX) {
            dx = -dx;
        } else if (collisionPoint.getX() == rightX) {
            dx = -dx;
        }
        if (collisionPoint.getY() == topY) {
            dy = -dy;
        } else if (collisionPoint.getY() == bottomY) {
            dy = -dy;
        }

        return new Velocity(dx, dy);
    }

    /** draw the block to the screen.
     * @param surface representing the surface we work on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());
        surface.setColor(BORDER_COLOR);
        surface.drawRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());
    }

    /** Notify the block that time has passed.
     */
    public void timePassed() {
    }

    /** Adds the paddle to a given Game object.
     @param g The Game object to which the sprite object will be added.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite((Sprite) this);
    }
}
