// ID 311127120

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;

/**
 * aligned with axes rectangle.
 */
public class Rectangle {
    // rectangle's upper left point
    private Point upperLeft;
    private double width;
    private double height;
    // list of lines the rectangle are made of
    private java.util.List<Line> rectangleLines;


    /**
     * constructor.
     * @param upperLeft upper left point of rectangle
     * @param width rectangle's width
     * @param height rectangle's height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
        this.rectangleLines = this.rectangleLines();
    }

    /**
     * auxilary method to make list of lines that constitute the rectangle.
     * @return list of rectangle's lines
     */
    private java.util.List<Line> rectangleLines() {
        ArrayList<Line> rectL = new ArrayList<>();
        Point downLeft = new Point(upperLeft.getX(), upperLeft.getY() + this.height);
        Point upperRight = new Point(upperLeft.getX() + this.width, upperLeft.getY());
        Point downRight = new Point(upperLeft.getX() + this.width, upperLeft.getY() + this.height);
        rectL.add(new Line(upperLeft, upperRight));
        rectL.add(new Line(upperRight, downRight));
        rectL.add(new Line(upperLeft, downLeft));
        rectL.add(new Line(downLeft, downRight));
        return rectL;
    }

    /**
     * method calculates again rectangle lines, in case of rectangle movement.
     */
    public void updateRectangleLines() {
        this.rectangleLines = rectangleLines();
    }

    /**
     * get rectangle width.
     * @return rectangle width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * get rectangle height.
     * @return rectangle height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * get rectangle's upper left point.
      * @return rectangle's upper left point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * set rectangle upper left point (change rectangle's location).
     * @param p new point to be rectangle's upper left point
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }

    /**
     * get list of rectangle's lines.
     * @return list of rectangle's lines
     */
    public java.util.List<Line> getRectangleLines() {
        return this.rectangleLines;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line to check intersection with
     * @return List of intersection points with the specified line (empty list if there's no intersection)
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> intersectP = new ArrayList<>();
        for (Line l : this.rectangleLines) {
            if (l.isIntersecting(line)) {
                intersectP.add(l.intersectionPoint(line));
            }
        }
        return intersectP;
    }

    /**
     * draw rectangle on given surface, fill rectangle with given color.
     * @param surface surface to draw rectangle on
     * @param color color to fill rectangle with
     */
    public void drawOn(DrawSurface surface, Color color) {
        surface.setColor(color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.getWidth(), (int) this.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }
}