// ID 311127120

/**
 * Velocity specifies the change in position on the `x` and the `y` axes, pixels per move.
 */
public class Velocity {
    // change in x axis
    private double dx;
    // change in y axis
    private double dy;


    /**
     * constructor.
     * @param dx change in x axis
     * @param dy change in y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * access change in x axis by velocity.
     * @return change in x axis
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * access change in y axis by velocity.
     * @return change in y axis
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * reverse velocity x-axis-movement direction.
     */
    public void reverseX() {
        this.dx *= -1;
    }

    /**
     * reverse velocity y-axis-movement direction.
     */
    public void reverseY() {
        this.dy *= -1;
    }

    /**
     * make a step, take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p is the source point of the move
     * @return the point reached after one step by velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * converts angle (degrees) and speed to velocity (dx and dy).
     * @param angle direction in degrees, 0 is up, 90 is right ETC...
     * @param speed pixels per move
     * @return velocity equivalent to angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double newDy = speed * Math.sin(radians);
        double newDx = speed * Math.cos(radians);
        return new Velocity(newDx, newDy);
    }

    /**
     * calc speed from dx and dy (pythagoras).
     * @return velocity speed
     */
    public double calcSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }
}