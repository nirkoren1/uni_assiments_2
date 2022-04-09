// 316443902 Nir Koren

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rectangle {
    private final Point upperLeft = new Point(0, 0);
    private final Double width;
    private final Double height;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft.setX(upperLeft.getX());
        this.upperLeft.setY(upperLeft.getY());
        this.width = width;
        this.height = height;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
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

    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}