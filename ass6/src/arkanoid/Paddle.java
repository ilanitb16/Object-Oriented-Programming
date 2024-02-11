package arkanoid;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import java.awt.Color;

/** The Paddle class is responsible representing our paddle.
 */
public class Paddle implements Sprite, Collidable {
    private  int paddleWidth = 150;
    private static final int PADDLE_HEIGHT = 15;
    private static final int PADDLE_BOTTOM_MARGIN = 2;
    private int paddleSpeed = 20;
    private static final Color PADDLE_BORDER_COLOR = Color.BLACK;
    private static final Color PADDLE_COLOR = Color.BLACK;
    private biuoop.KeyboardSensor keyboard;
    private geometry.Rectangle rect;
    private DrawSurface surface;
    private int xMinPos, xMaxPos;

    /** Constructor for a Paddle object.
     @param gui the game's GUI.
     @param surfaceBorderWidth the border width of the game's surface.
     */
    Paddle(GUI gui, int surfaceBorderWidth) {
        surface = gui.getDrawSurface();

        int surfaceWidth = surface.getWidth();
        int surfaceHeight = surface.getHeight();
        int xPos = surfaceWidth / 2 - paddleWidth / 2;
        int yPos = surfaceHeight - PADDLE_HEIGHT - PADDLE_BOTTOM_MARGIN
                - surfaceBorderWidth;

        xMinPos = surfaceBorderWidth;
        xMaxPos = surfaceWidth - paddleWidth - surfaceBorderWidth;
        rect = new geometry.Rectangle(xPos, yPos, paddleWidth, PADDLE_HEIGHT);
        keyboard = gui.getKeyboardSensor();
    }

    /** Moves the paddle left on the game's surface by the defined speed.
     */
    public void moveLeft() {
        int increment = 0;
        int xPos = (int) rect.getUpperLeft().getX();

        if (xPos > xMinPos) {
            if (xPos - paddleSpeed < xMinPos) {
                increment = xMinPos - xPos;
            } else {
                increment = -1 * paddleSpeed;
            }

            rect.incrementX(increment);
        }
    }

    /**Moves the paddle right on the game's surface by the defined speed.
     */
    public void moveRight() {
        int increment = 0;
        int xPos = (int) rect.getUpperLeft().getX();

        if (xPos < xMaxPos) {
            if (xPos + paddleSpeed > xMaxPos) {
                increment = xMaxPos - xPos;
            } else {
                increment = paddleSpeed;
            }

            rect.incrementX(increment);
        }
    }

    /** Notify the sprite that time has passed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
            return;
        }

        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /** draw the paddle to the scree.
     * @param surface the surface to work on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(PADDLE_COLOR);
        surface.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());
        surface.setColor(PADDLE_BORDER_COLOR);
        surface.drawRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(), (int) rect.getWidth(),
                (int) rect.getHeight());
    }

    /**Returns the rectangle shape of the block.
     @return the rectangle shape of the block
     */
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /** Calculates and returns the new velocity of the ball after it collides
     *  with the block. The new velocity depends on the collision point and
     *  the current velocity of the ball.

     @param collisionPoint the point of collision
     @param currentVelocity the current velocity of the ball
     @return the new velocity of the ball post-collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        // Check if ball hits the left size or the right size

        double x = collisionPoint.getX();
        double y = collisionPoint.getY();

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();


        // Find the intersection point on the paddle
        double relativeIntersectionX =
                (collisionPoint.getX() - this.getCollisionRectangle().getUpperLeft().getX())
                / this.getCollisionRectangle().getWidth();

        // Find the angles for each region of the paddle
        double[] regionAngles = {300, 330, dy < 0 ? dy : -dy, 30, 60};

        // Calculate the new angles according to the intersection point
        double newAngle;
        if (relativeIntersectionX == 0) {
            newAngle = regionAngles[0];
        } else {
            newAngle =
                    regionAngles[(int) (relativeIntersectionX * regionAngles.length - 1)];
        }

        // Change the ball's velocity according to the new angle
        Velocity newVelocity = Velocity.fromAngleAndSpeed(newAngle,
                currentVelocity.getSpeed());

        return newVelocity;
    }

    /** Adds the paddle to a given Game object.
     @param g The Game object to which the sprite object will be added.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite((Sprite) this);
    }

    /** Set function for paddle speed.
     @param speed The new speed to be set.
     */
    public void setSpeed(int speed) {
        paddleSpeed = speed;
    }

    /** Set function for paddle width.
     @param width The new speed to be set.
     */
    public void setWidth(int width) {
        paddleWidth = width;
    }
}