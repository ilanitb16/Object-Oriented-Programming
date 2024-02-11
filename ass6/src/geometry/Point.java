package geometry;

// 322453200, Ilanit Berditchevski

/** Class representing the point.
 */
public class Point {
    private double x;
    private double y;
    /**
     * Constructs a new geometry.Point with the given x and y coordinates.
     *
     * @param x  coordinate x
     * @param y  coordinate y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    // screen size as set in example
    /**
     * calculates distance between current and other point.
     *
     * @param other the other point
     * @return dintance
     */
    public double distance(Point other) {
        double differenceX = Math.abs(x - other.getX());
        double differenceY = Math.abs(y - other.getY());

        return Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));
    }

    /**
     * Returns the x value of this point.
     *
     * @return the x value
     */
    public double getX() {
        return this.x;
    }
    /**
     * Returns the y value of this point.
     *
     * @return the y value
     */
    public double getY() {
        return this.y;
    }
    /**
     * Setter of the x coordinate of the point.
     * @param x new value for x
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Setter of the y coordinate of the point.
     * @param y new value for y
     */
    public void setY(double y) {
        this.y = y;
    }
}
