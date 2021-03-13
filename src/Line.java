// ID 311127120

import java.util.List;

/**
 * Line between 2 points (line segmentation).
 * Class can check 2 lines' equality, calculate line's length, line's middle point, 2 lines' intersection point,
 * check if X value in line, if Y value in line, if 2 line intersect, if 2 lines are parallel
 */
public class Line {
    // start point
    private Point start;
    // end point
    private Point end;
    // line's equation (Y = mX + n) as infinite line
    private LineEquation equation;

    /**
     * constructor.
     * @param start start point
     * @param end end point
     */
    public Line(Point start, Point end) {
        if (start.equals(end)) {
            return;
        }
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
        this.equation = new LineEquation(start, end);
    }

    /**
     * constructor.
     * @param x1 start point's X value
     * @param y1 start point's Y value
     * @param x2 end point's X value
     * @param y2 end point's Y value
     */
    public Line(double x1, double y1, double x2, double y2) {
        if (x1 == x2 && y1 == y2) {
            return;
        }
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
            this.equation = new LineEquation(x1, y1, x2, y2);
    }

    /**
     * get line's start point.
     * @return line's start point
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * get line's end point.
     * @return line's end point
     */
    public Point getEnd() {
        return this.end;
    }

    /**
     * get line's equation.
     * @return line's equation
     */
    public LineEquation getEquation() {
        return this.equation;
    }

    /**
     * checks if 2 lines are equal.
     * @param other line to check equality
     * @return true if lines are equal, false otherwise
     */
    public boolean isEqual(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return false;
    }

    /**
     * calculate line's length.
     * @return line's length
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * calculate line's middle point.
     * @return line's middle point
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2.0, (this.start.getY() + this.end.getY()) / 2.0);
    }

    /**
     * checks if given X value is in line segment.
     * @param x given value to check if in line segment
     * @return true if x is in line segment, false otherwise
     */
    public boolean isXInLineSeg(double x) {
        if (Math.max(this.start.getX(), this.end.getX()) >= x
                && Math.min(this.start.getX(), this.end.getX()) <= x) {
            return true;
        }
        return false;
    }

    /**
     * checks if given Y value is in line segment.
     * @param y given value to check if in line segment
     * @return true if y is in line segment, false otherwise
     */
    public boolean isYInLineSeg(double y) {
        if (Math.max(this.start.getY(), this.end.getY()) >= y
                && Math.min(this.start.getY(), this.end.getY()) <= y) {
            return true;
        }
        return false;
    }

    /**
     * check if given point is in line segment.
     * @param p given point to check if in line segment
     * @return true if point is in line segment, false otherwise
     */
    public boolean isPointInLineSeg(Point p) {
        if (this.equation.isVertical()) { // case of vertical line
            if (p.getX() == this.equation.getSlope()) {
                return true;
            }
        }
        // case of non vertical line
        if (p.getY() == p.getX() * this.equation.getSlope() + this.equation.getCrossYAxis()) {
            return true;
        }
        return false;
    }

    /**
     * checks if 2 lines intersect.
     * @param other line to check intersection with
     * @return true if 2 lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // in case lines are parallel
        if (this.isParallel(other)) {
            if (this.start.equals(other.getStart()) || this.end.equals(other.getEnd())
                    || this.start.equals(other.getEnd()) || this.end.equals(other.getStart())) {
                // lines have exactly one common point (intersection in lines' edges)
                return true;
            }
            // lines don't intersect or have more than 1 common point
             return false;
        }
        // calculates the X value of possible intersection point
        // for now we know at least one line isn't parallel so we can use method intersectionX
        double x = this.equation.intersectionX(other.equation);
        // checks if intersection point is part of both lines (by X value)
        if (!this.isXInLineSeg(x) || !other.isXInLineSeg(x)) {
            return false;
        }
        // if one of the lines is vertical checks if it intersects
        if (this.equation.isVertical() || other.getEquation().isVertical()) {
            // calculates the Y value of possible intersection point
            double y;
            if (!this.equation.isVertical()) {
                y = this.equation.getSlope() * x + this.equation.getCrossYAxis();
            } else {
                y = other.getEquation().getSlope() * x + other.getEquation().getCrossYAxis();
            }
            // checks if intersection point is part of both lines (absolutely)
            if (!this.isYInLineSeg(y) || !other.isYInLineSeg(y)) {
                return false;
            }
        }
        return true;
    }

    /**
     * calculate intersection point of 2 lines.
     * @param other line of intersection
     * @return Point of intersection if lines intersect, otherwise NULL
     */
    public Point intersectionPoint(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        // if "this" intersect by it's start return it's start, otherwise return it's end
        if (this.equation.isVertical() && other.getEquation().isVertical()) {
            if (this.start.equals(other.getStart()) || this.start.equals(other.getEnd())) {
                return this.start;
            } else {
                return this.end;
            }
        }
        // calculate intersection point's X value
        double x = this.equation.intersectionX(other.equation);
        // calculate intersection point's Y value
        double y;
        // at least one line isn't vertical, calculate Y with the not-vertical line's equation
        if (!this.equation.isVertical()) {
            y = this.equation.getSlope() * x + this.equation.getCrossYAxis();
        } else {
            y = other.getEquation().getSlope() * x + other.getEquation().getCrossYAxis();
        }
        return new Point(x, y);
    }

    /**
     * checks if lines are parallel.
      * @param other line to check parallelity
     * @return true if lines parallel, false otherwise
     */
    public boolean isParallel(Line other) {
        // if both lines are vertical in particular lines sre parallel
        if (this.equation.isVertical() && other.getEquation().isVertical()) {
            return true;
        }
        // if both lines aren't vertical, determined by lines' slopes
        if (!this.equation.isVertical() && !other.getEquation().isVertical()) {
            if (this.equation.getSlope() == other.getEquation().getSlope()) {
                return true;
            }
        }
        // if only one of the lines is vertical, they aren't parallel
        return false;
    }

    /**
     * return the closest point of intersection with rectangle to line's start.
     * if line doesn't intersect with rectangle return null
     * @param rect rectangle to check intersection with
     * @return closest intersection point to line's start' if doesn't intersect return null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectP = rect.intersectionPoints(this);
        if (intersectP.size() == 0) {
            return null;
        }
        Point closest = intersectP.get(0);
        for (Point p : intersectP) {
            if (this.start.distance(p) < this.start.distance(closest)) {
                closest = p;
            }
        }
        return closest;
    }
}
