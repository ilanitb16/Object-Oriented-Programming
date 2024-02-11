// 322453200, Ilanit Berditchevski
public class Point {
    private double x;
    private double y;
    /**
     * Constructs a new Point with the given x and y coordinates.
     *
     * @param x  coordinate x
     * @param y  coordinate y
     */
    public Point(double x, double y) {
        this.x= x;
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
        double differenceX = Math.abs(x-other.getX());
        double differenceY = Math.abs(y-other.getY());

        return Math.sqrt(Math.pow(differenceX,2) + Math.pow(differenceY,2));
    }

    /**
     * Determine if this point is equal to the other point.
     *
     * @param other the other point
     * @return true if the points share the same coordinates, else return false
     */
    public boolean equals(Point other) {
        // validate
        if (other == null) {
            return false;
        }
        // if both x and y match, return true
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Returns the x value of this point.
     *
     * @return the x value
     */
    public double getX() {return this.x;}
    /**
     * Returns the y value of this point.
     *
     * @return the y value
     */
    public double getY() {return this.y;}
    /**
     * Setter of the x coordinate of the point.
     */
    public void setX(double x) { this.x = x; }
    /**
     * Setter of the y coordinate of the point.
     */
    public void setY(double y) { this.y = y; }
}
