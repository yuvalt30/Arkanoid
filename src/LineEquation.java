// ID 311127120

/**
 * Line equation of infinite line in slope-intercept form (Y = mX + n).
 * class can
 */

public class LineEquation {
    // X's factor (line's slope), or intersection with X axis in case of verticality
    private double slope;
    // free factor (Y value of line's intersection point with Y axis)
    private double crossYAxis;
    // indicate if line's vertical
    private boolean isVertical;

    /**
     * constructor.
     * @param x1 is line's start point's X value
     * @param y1 is line's start point's Y value
     * @param x2 is line's end point's X value
     * @param y2 is line's end point's Y value
     */
    public LineEquation(double x1, double y1, double x2, double y2) {
        // case line is vertical
        if (x1 == x2) {
            this.slope = x1;
            this.isVertical = true;
            this.crossYAxis = 0;
        } else {
            double s = (y1 - y2) / (x1 - x2);
            this.slope = s;
            this.crossYAxis = y1 - s * x1;
            this.isVertical = false;
        }
    }

    /**
     * constructor.
     * @param start is line's start point
     * @param end is line's end point
     */
    public LineEquation(Point start, Point end) {
        // case line is vertical
        if (start.getX() == end.getX()) {
            this.slope = start.getX();
            this.isVertical = true;
            this.crossYAxis = 0;
        } else {
            double s = (start.getY() - end.getY()) / (start.getX() - end.getX());
            this.slope = s;
            this.crossYAxis = start.getY() - s * start.getX();
            this.isVertical = false;
        }
    }

    /**
     * check if line is vertical.
     * @return true if line is vertical, false otherwise
     */
    public boolean isVertical() {
        return this.isVertical;
    }

    /**
     * get slope (X's factor).
     * @return line's slope
     */
    public double getSlope() {
        return this.slope;
    }

    /**
     * get free factor (Y value of line's intersection point with Y axis).
     * @return free factor
     */
    public double getCrossYAxis() {
        return this.crossYAxis;
    }

    /**
     * calculate X value of 2 infinite lines' intersection point.
     * assumption: at least one line isn't vertical, so 2 lines must intersect
     * @param other line of intersection
     * @return X value of intersection point
     */
    public double intersectionX(LineEquation other) {
        if (this.isVertical) {
            return this.slope;
        }
        if (other.isVertical()) {
            return other.getSlope();
        }
        return (other.getCrossYAxis() - this.getCrossYAxis()) / (this.slope - other.getSlope());
    }

    /**
     * check if 2 line's equations are equal.
     * @param other line's equation to compare
     * @return true if both are equal, false otherwise
     */
    public boolean isEqual(LineEquation other) {
        if ((this.isVertical != other.isVertical()) || (this.crossYAxis != other.getCrossYAxis())
                || (this.slope != other.getSlope())) {
            return false;
        }
        return true;
    }
}
