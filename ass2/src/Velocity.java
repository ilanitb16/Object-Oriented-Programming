// 322453200, Ilanit Berditchevski
public class Velocity {
    private double dx;
    private double dy;
    /**
     *Constructor for Velocity with the given dx and dy values.
     *@param dx the width of the screen
     *@param dy the height of the screen
     */
    public Velocity(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    /**
     *  Returns a new Point object that created after applying Velocity
     *  to a Point.
     * @param p the Point to apply the Velocity to
     * @return a new Point with the applied velocity
     */
    public Point applyToPoint(Point p){
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        return new Point(newX, newY);    }

    /**
     * Returns the dx value of this point.
     *
     * @return the dx value
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the dy value of this point.
     *
     * @return the dy value
     */
    public double getDy() {
        return dy;
    }

    /**
     * Setter of the dx value of the point.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Setter of the dy value of the point.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Returns a new Velocity calculated by the angle and speed.
     * The angle is in degrees.
     * @param angle the angle in degrees
     * @param speed the speed of the Velocity
     * @return a new Velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        // cos a = dx/hypotenuse
        double dx = speed * Math.cos(Math.toRadians(angle));

        // sin a = dy/ hypotenuse
        double dy = speed * Math.sin(Math.toRadians(angle));

        return new Velocity(dx, dy);
    }
}
