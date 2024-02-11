// 322453200, Ilanit Berditchevski

import biuoop.DrawSurface;
import java.awt.*;

public class Ball {

    // given width and height
    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    Point center;
    int radius = 0;
    java.awt.Color color;
    Velocity velocity;
    /**
     * Constructs a new Ball with the given center position, radius and color.
     *
     * @param center  starting point
     * @param radius  radius of circle
     * @param color  color of circle
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        this.center = center;
        this.radius= radius;
        this.color= color;
    }

    /**
     * Constructs a new Ball with the given center position, radius and color.
     *
     * @param x x coordinate starting point
     * @param y y coordinate starting point
     * @param radius  radius of circle
     * @param color of circle
     */
    public Ball(int x, int y, int radius, Color color) {
        this.center = new Point(x,y);
        this.radius = radius;
        if(this.color != null) {
            this.color = color;
        }
        else{
            this.color = new Color(color.getRGB());
        }
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
     * Draw the ball on the given DrawSurface
   */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int)center.getX(),(int)center.getY(),radius);
    }

    /**
     *Sets the velocity of the ball.
     @param v the velocity to set
     */
    public void setVelocity(Velocity v){
        this.velocity = v;
    }

    /**
     Sets the velocity of the ball.
     @param dx horizontal change
     @param dy vertical change
     */
    public void setVelocity(double dx, double dy){
        if(this.velocity!= null){
            this.velocity.setDx(dx);
            this.velocity.setDy(dy);
        }
        this.velocity = new Velocity(dx,dy);
    }

    /**
     *Returns the velocity of this circle.
     *@return the velocity of this circle
     */
    public Velocity getVelocity(){
        return this.velocity;
    }

    /**
     *Moves the ball one step using the current velocity width, height and
     * offset.The ball bounces from the sides and top/bottom of the
     *surface if it hits it. The new position is set post-moving.
     */
    public void moveOneStep() {
        Point newPosition = this.getVelocity().applyToPoint(this.center);


        // Check if ball hits the left size or the right size
        if (newPosition.getX() + this.radius > WIDTH ||
                newPosition.getX() - this.radius < 0 ) {
            this.velocity = new Velocity(-this.velocity.getDx(),
                    this.velocity.getDy());
        }

        // Check if the ball hits the top or bottom of the screen
        if (newPosition.getY() - this.radius < 0 ||
                newPosition.getY() + this.radius > HEIGHT) {
            this.velocity = new Velocity(this.velocity.getDx(),
                    -this.velocity.getDy());
        }

        // Set new position
        this.center = this.getVelocity().applyToPoint(this.center);

    }

    /**
     *Moves the ball one step using the new given velocity width,height and
     *offset The ball bounces from the sides and top/bottom of the
     *surface if it hits it. The new position is set post-moving.
     *@param width the width of the screen
     *@param height the height of the screen
     *@param offset the offset from the edge of the screen
     */
    public void moveOneStep(int width, int height, int offset) {
        Point newPosition = this.getVelocity().applyToPoint(this.center);
        // Check if ball hits the left size or the right size

        double x = newPosition.getX();
        double y = newPosition.getY();

        if (x + this.radius > width + offset){
            this.velocity = new Velocity(-this.velocity.getDx(),
                    this.velocity.getDy());

            if(x < width + offset) {
                newPosition.setX((double) (width + offset - this.radius));
            }
            else{


                newPosition = this.getVelocity().applyToPoint(this.center);
            }
        }

        if (x - this.radius < offset){
            this.velocity = new Velocity(-this.velocity.getDx(),
                    this.velocity.getDy());

            if(x > offset) {
                newPosition.setX((double) (offset + this.radius));
            }
            else{


                newPosition = this.getVelocity().applyToPoint(this.center);
            }
        }

        // Check if the ball hits the top or bottom of the screen
        if(y + radius > height + offset){
            this.velocity = new Velocity(this.velocity.getDx(),
                    -this.velocity.getDy());

            if(y < height + offset){
                newPosition.setY((double)(height + offset - radius));
            }
            else{
                newPosition = this.getVelocity().applyToPoint(this.center);
            }
        }

        if(y - radius < offset){
            this.velocity = new Velocity(this.velocity.getDx(),
                    -this.velocity.getDy());

            if(y > offset){
                newPosition.setY((double)(offset + radius));
            }
            else{
                newPosition = this.getVelocity().applyToPoint(this.center);
            }
        }

        // Set new position
        this.center = newPosition;
    }

}
