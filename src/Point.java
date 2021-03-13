// ID 311127120

/**
 * A point contains X and Y coordinates.
 * The class measures distance between 2 points and checks 2 points equality
 */
public class Point {
    // X coordinate
    private double x;
    // Y coordinate
    private double y;

    /**
     * constructor.
     * @param x is X coordinate
     * @param y is Y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * get point's x value.
     * @return point's x value
     */
    public double getX() {
        return this.x;
    }

    /**
     * get point's y value.
     * @return point's y value
     */
    public double getY() {
        return this.y;
    }

    /**
     * return the distance of this point to the other point.
     * @param other point to measure distance to
     * @return distance between this point and other
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * return true if the points are equal, false otherwise.
     * @param other other point to compare
     * @return true if both equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this.x != other.getX() || this.y != other.getY()) {
            return false;
        }
        return true;
    }
}
