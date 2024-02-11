package geometry;
import java.util.ArrayList;

/** Class representing the blocks on the screen.
 */
public class Rectangle {
    // the upper left corner of the rectangle
    private Point upperLeft;
    private double width;
    private double height;

    /**Creates a new rectangle with location and width/height.
     @param x The x coordinate of the upper left point of the rectangle.
     @param y The y coordinate of the upper left point of the rectangle.
     @param width The width of the rectangle.
     @param height The height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**Creates a new rectangle with upper left point, width and height.
     @param upperLeft The upper left point of the rectangle.
     @param width The width of the rectangle.
     @param height The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**Return a (possibly empty) List of intersection points
     * with the specified line.
     @param line List of lines
     @return list of point
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        double rectStartX = this.upperLeft.getX();
        double rectStartY = this.upperLeft.getY();

        double rectEndX = rectStartX + this.width;
        double rectEndY = rectStartY + this.height;

        Point upperRight = new Point(rectEndX, rectStartY);
        Point lowerRight = new Point(rectEndX, rectEndY);
        Point lowerLeft = new Point(rectStartX, rectEndY);

        Line[] lines = new Line[4];
        java.util.List<Point> intersections = null;

        // lines representing the edges of the rectangle
        lines[0] = new Line(this.upperLeft, upperRight);
        lines[1] = new Line(this.upperLeft, lowerLeft);
        lines[2] = new Line(lowerRight, lowerLeft);
        lines[3] = new Line(lowerRight, upperRight);

        for (int i = 0; i < lines.length; i++) {
            // get intersection point
            Point p = lines[i].intersectionWith(line);
            if (p != null) {
                // add intersection
                if (intersections == null) {
                    intersections = new ArrayList<>();
                    intersections.add(p);
                } else {
                    intersections.add(p);
                }
            }
        }
        return intersections;
    }

    /**Returns the width and height of the rectangle.
     @return The width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**Returns the height and height of the rectangle.
     @return The height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**Moves the rectangle vertically by an increment.
     @param inc The increment by which to move the rectangle.
     */
    public void incrementX(double inc) {
        upperLeft.setX(upperLeft.getX() + inc);
    }

    /**Moves the rectangle horizontal by an increment.
     @param inc The increment by which to move the rectangle.
     */
    public void incrementY(double inc) {
        upperLeft.setY(upperLeft.getY() + inc);
    }

    /** Returns the upper-left point of the rectangle.
     * @return upper left point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

}
