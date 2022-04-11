// 316443902 Nir Koren

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * a rectangle shape class.
 * can calculate intersection points with a line.
 */
public class Rectangle {
    private Point upperLeft = new Point(0, 0);
    private final Double width;
    private final Double height;

    /**
     * Create a new rectangle with location and width/height.
     * @param height height desired.
     * @param upperLeft the upper left Point of the rectangle
     * @param width width desired
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft.setX(upperLeft.getX());
        this.upperLeft.setY(upperLeft.getY());
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * creates 4 lines, each representing one of the rectangle's sides.
     * checks if the given line intersects with each of the rectangle's sides.
     * If it does, it adds the intersection point to a list.
     * returns the list of intersection points.
     * @param line the line probably intersected with
     * @return List of the all intersection Points (can be empty)
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line l1 = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + this.width, upperLeft.getY());
        Line l2 = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + this.height);
        Line l3 = new Line(upperLeft.getX(), upperLeft.getY() + this.height, upperLeft.getX() + width,
                upperLeft.getY() + this.height);
        Line l4 = new Line(upperLeft.getX() + this.width, upperLeft.getY(), upperLeft.getX() + width,
                upperLeft.getY() + this.height);
        List<Line> lines = new ArrayList<>(Arrays.asList(l1, l2, l3, l4));
        List<Point> pointsOfIntersection = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (line.isIntersecting(lines.get(i))) {
                pointsOfIntersection.add(line.intersectionWith(lines.get(i)));
            }
        }
        return pointsOfIntersection;
    }
    /**
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper left Point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * set the upper left Point of the rectangle.
     * @param x the x position of the upper left Point
     * @param y the y position of the upper left Point
     */
    public void setUpperLeft(double x, double y) {
        this.upperLeft = new Point(x, y);
    }
}