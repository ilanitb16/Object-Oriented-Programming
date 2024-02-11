// 322453200, Ilanit Berditchevski
public class Line {
    Point start;
    Point end;

    /**
     * Constructor of a new Line with the start and end points.
     *
     * @param start starting point of the line.
     * @param end ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor of a new Line given x and y coordinates of both the
     * starting and ending point.
     *
     * @param x1 the x value of the starting point.
     * @param y1 the y value of the starting point.
     * @param x2 the x value of the ending point.
     * @param y2 the y value of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        // create new points
        this.start = new Point(x1,y1);
        this.end = new Point(x2,y2);
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        // calculate middles of each coordinate sum
        double x = (start.getX() + end.getX()) / 2;
        double y = (start.getY() + end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() { return this.start;}

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() { return this.end; }

    /**
     * Returns true if the lines intersect, false otherwise
     *
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // check if the value returned by the function is not null
        return intersectionWith(other) != null;
    }

    /**
     * Returns the intersection point if the lines intersect and null otherwise.
     *
     * @param other gets the other line
     * @return intersection point
     */
    public Point intersectionWith(Line other) {
       // starting point coordinates (x,y)
        double start1_X = start.getX();
        double start1_Y = start.getY();

        // ending point coordinates (x,y)
        double end1_X = end.getX();
        double end1_Y = end.getY();

        // starting point coordinates (x,y) of other line
        double start2_X = other.start().getX();
        double start2_Y = other.start().getY();

        // ending point coordinates (x,y) of other line
        double end2_X = other.end().getX();
        double end2_Y = other.end().getY();

        double m1, m2; // slopes
        if (end2_X == start2_X) { // line 2 is vertical
            m1 = (end1_Y - start1_Y) / (end1_X - start1_X);
            double x = start2_X;
            double y = start1_Y + m1 * (x - start1_X);
            return new Point(x, y);
        } else if (end1_X == start1_X) { // line 1 is vertical
            m2 = (end2_Y - start2_Y) / (end2_X - start2_X);
            double x = start1_X;
            double y = start2_Y + m2 * (x - start2_X);
            return new Point(x, y);
        } else {
            m2 = (end2_Y - start2_Y) / (end2_X - start2_X);
            m1 = (end1_Y - start1_Y) / (end1_X - start1_X);
            // determine if lines are parallel
            if (m1==m2) {
                return null; // lines are parallel
            }
            // get intersection point
            double b1= start1_Y-m1*start1_X;
            double b2 = start2_Y-m2*start2_X;

            double x = (b2-b1)/(m1-m2);
            double y = m1*x+b1;

            double maxX = Math.max(start1_X,end1_X);
            double maxY = Math.max(start1_Y,start2_Y);

            double minX = Math.min(start1_X,end1_X);
            double minY = Math.max(start1_Y,start2_Y);

            // check if the intersection is between the bounds of both lines
            if(isWithinBounds(x,start1_X,end1_X)&&isWithinBounds(x,start2_X,
                    end2_X) && isWithinBounds(y,start1_Y,end1_Y) &&
                    isWithinBounds(y,start2_Y,end2_Y)){
                return new Point(x, y);
            }
            return null;
        }
    }

    /**
     * Returns return true is the lines are equal, false otherwise
     *
     * @param other the other line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }

        // calculate incline
        double m1 = (end.getY() - start.getY())/(end.getX() -  start.getX());
        double m2 =
                (other.end().getY() - other.start().getY())/
                        (other.end().getX() - other.start().getX());

        // y = mx + b, calculate b
        double b1= m1*(-end.getX())+end.getY();
        double b2 = m2*(-other.end().getX())+other.end().getY();

        // compare both parameters for lins
        return m1 == m2 && b1 == b2;
    }

    /**
     * Returns true if a value is located between 2 other given values
     *
     * @param value the parameter we are checking the bounds of
     * @param  start starting bound value
     * @param  end ending bound value
     * @return true is the value is located between start and end, false
     * otherwise
     */
    public static boolean isWithinBounds(double value, double start, double end) {
        return value >= Math.min(start, end) && value <= Math.max(start, end);
    }
}
