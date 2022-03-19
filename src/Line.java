// Nir Koren 316443902


import java.util.Objects;

/**
 * class Line: line segment determent by two points.
 * can calculate length, middle point, if two lines intersect and the point they intersect.
 */
public class Line {
    private Point start;
    private Point end;
    private Double m = null;
    private Double b = null;
    private Double bigY;
    private Double smallY;
    private Double bigX;
    private Double smallX;
    /**
     * constructor.
     * @param start first point of the line segment
     * @param end second point of the line segment
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        calculateArguments();
    }
    /**
     * constructor.
     * @param x1 x value of the first point of the line segment
     * @param y1 y value of the first point of the line segment
     * @param x2 x value of the second point of the line segment
     * @param y2 y value of the second point of the line segment
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        calculateArguments();
    }
    /**
     * calculate the slope and bias of the line, as well as the big, small y and x values.
     * all are required for the intersection calculations.
     */
    public void calculateArguments() {
        bigX = Math.max(start.getX(), end.getX());
        bigY = Math.max(start.getY(), end.getY());
        smallX = Math.min(start.getX(), end.getX());
        smallY = Math.min(start.getY(), end.getY());
        if (start.getX() != end.getX()) {             // not parallel to y-axis
            m = (start.getY() - end.getY()) / (start.getX() - end.getX());
            b = start.getY() - m * start().getX();
        }
    }

    /**
     * @return the distance between the start and end points of the line (length of the line segment)
     */
    public double length() {
        return start.distance(end);
    }
    /**
     * @return middle Point of the line segment using average
     */
    public Point middle() {
        return new Point((start.getX() + end().getX()) / 2, (start.getY() + end.getY()) / 2);
    }
    /**
     * @return start point of the line
     */
    public Point start() {
        return start;
    }
    /**
     * @return end point of the line
     */
    public Point end() {
        return end;
    }
    /**<p>
     * Returns true if the lines intersect, false otherwise.
     * </p><p>
     * divide this problem to 6 different situations:
     * 1) the two lines are parallel to y-axis
     * 2) the first line is parallel to y-axis
     * 3) the second line is parallel to y-axis
     * 4) the two lines (rays) overlap
     * 5) the two lines (rays) parallel but not overlap
     * 6) the two lines (rays) cross somewhere - not the above.
     * </p><p>
     * the method calculate the intersection point of the two lines and checks if the point is in the two lines
     * </p>
     * @param other the other line segment.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.m == null && other.m == null) {
            if (this.start.getX() != other.start.getX()) {
                return false;
            } else {
                return (other.smallY <= this.bigY && this.bigY <= other.bigY) || (other.smallY <= this.smallY
                        && this.smallY <= other.bigY);
            }
        } else if (this.m == null) {
            Double y = other.m * this.start.getX() + other.b;
            return (this.smallY <= y && y <= this.bigY) && (other.smallX <= this.start.getX() && this.start.getX()
                    <= other.bigX);
        } else if (other.m == null) {
            Double y = this.m * other.start.getX() + this.b;
            return (other.smallY <= y && y <= other.bigY) && (this.smallX <= other.start.getX() && other.start.getX()
                    <= this.bigX);
        } else if (this.m.equals(other.m) && this.b.equals(other.b)) {
            return (this.smallX <= other.bigX && other.bigX <= this.bigX) || (this.smallX <= other.smallX
                    && other.smallX <= this.bigX);
        } else if (this.m.equals(other.m)) {
            return false;
        } else {
            Double x = (other.b - this.b) / (this.m - other.m);
            return (this.smallX <= x && x <= this.bigX) && (other.smallX <= x && x <= other.bigX);
        }
    }
    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     * <p></p>
     * if one of the lines is parallel to y-axis: y = m*x+b (x = some x value of the parallel line).
     * else: x = (b1 - b2) / (m2 - m1) and y = m*x+b
     * @param other the other line segment
     * @return Point of the intersection, if not intersect - null
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        if (this.m == null) {
            double y = other.m * this.start.getX() + other.b;
            return new Point(this.start.getX(), y);
        } else if (other.m == null) {
            double y = this.m * other.start.getX() + this.b;
            return new Point(other.start.getX(), y);
        } else {
            double x = (other.b - this.b) / (this.m - other.m);
            double y = this.m * x + this.b;
            return new Point(x, y);
        }
    }
    /**
     * Returns true if the two line segments are the same, false if not.
     * uses the middle point and the length of the lines
     * @param other the other line segment
     * @return true if the two line segments are the same, false if not.
     */
    public boolean equals(Line other) {
        if (!Objects.equals(this.m, other.m) || !Objects.equals(this.b, other.b)) {
            return false;
        }
        return (this.middle().equals(other.middle()) && this.length() == other.length());
    }
}
