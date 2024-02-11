package arkanoid;

import biuoop.DrawSurface;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** The Block class is responsible representing the blocks on the screen.
 */
public class Block implements Collidable, Sprite {
    private static final Color BORDER_COLOR = Color.BLACK;
    private static final Color DEFAULT_COLOR = new Color(147, 162, 255);
    private geometry.Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners;
    private java.awt.Image  bgImage = null;

    /** Constructs a block with a default color of gray.
     @param x the x coordinate of the block's upper left corner
     @param y the y coordinate of the block's upper left corner
     @param width the width of the block
     @param height the height of the block
     */
    public Block(double x, double y, double width, double height) {
        rect = new geometry.Rectangle(new geometry.Point(x, y), width, height);
        this.color = DEFAULT_COLOR;
        hitListeners = new ArrayList<>();
    }
    /** Constructs a block with a default color of gray.
     @param x the x coordinate of the block's upper left corner
     @param y the y coordinate of the block's upper left corner
     @param bgImageName image
     */
    public Block(double x, double y, String bgImageName) {
        this.color = DEFAULT_COLOR;

        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + "\\src\\assets\\images\\" + bgImageName;

        try {
            bgImage = ImageIO.read(new File(filePath));

            int width = ((BufferedImage) bgImage).getWidth();
            int height = ((BufferedImage) bgImage).getHeight();

            rect = new geometry.Rectangle(new geometry.Point(x, y), width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }

        hitListeners = new ArrayList<>();
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
        hitListeners = new ArrayList<>();
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
     @param hitter the ball hitting the block.
     @return the new velocity of the ball post-collision
     */

    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {

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

        // Notify the hit listeners
        notifyHit(hitter);

        return new Velocity(dx, dy);
    }

    /** draw the block to the screen.
     * @param surface representing the surface we work on.
     */
    public void drawOn(DrawSurface surface) {

        int x = (int) rect.getUpperLeft().getX();
        int y = (int) rect.getUpperLeft().getY();

        if (bgImage == null) {
            surface.setColor(color);
            surface.fillRectangle((int) x,  y, (int) rect.getWidth(),
                    (int) rect.getHeight());
            surface.setColor(BORDER_COLOR);
            surface.drawRectangle(x, y, (int) rect.getWidth(),
                    (int) rect.getHeight());
        } else {
            surface.drawImage(x, y, bgImage);
        }

    }

    /** Notify the block that time has passed.
     */
    public void timePassed() {
    }

    /** Adds the block to a given Game object.
     @param g The Game object to which the sprite object will be added.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite((Sprite) this);
    }
    /** Removes the ball from a given Game object.
     @param game The Game object which the sprite will be removed from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /** This method will be called whenever a hit occurs, and will notify
     *  the registered HitListener objects by calling their hitEvent method.
     @param hitter represents the hitting ball.
     */
    private void notifyHit(Ball hitter) {
        for (HitListener hl : hitListeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /** This method will be called for an object that wants to be notified of
     *  hit events. Registers itself with a HitNotifier object.
     @param hl represents the hit listener.
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /** This method will for removing a hit listener from the list of listeners
     *  to hit events.
     @param hl represents the hit listener.
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
