// Nir Koren 316443902


import java.util.Objects;

public class Line {
    private Point start;
    private Point end;
    private Double m = null;
    private Double b = null;
    private Double bigY;
    private Double smallY;
    private Double bigX;
    private Double smallX;

    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        calculateArguments();
    }
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        calculateArguments();
    }

    public void calculateArguments() {
        bigX = Math.max(start.getX(), end.getX());
        bigY = Math.max(start.getY(), end.getY());
        smallX = Math.min(start.getX(), end.getX());
        smallY = Math.min(start.getY(), end.getY());
        if (start.getX() != end.getX()) {
            m = (start.getY() - end.getY()) / (start.getX() - end.getX());
            b = start.getY() - m * start().getX();
        }
    }

    // Return the length of the line
    public double length() {
        return start.distance(end);
    }

    // Returns the middle point of the line
    public Point middle() {
        return new Point((start.getX() + end().getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    // Returns the start point of the line
    public Point start() {
        return start;
    }

    // Returns the end point of the line
    public Point end() {
        return end;
    }

    // Returns true if the lines intersect, false otherwise
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

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
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

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (!Objects.equals(this.m, other.m) || !Objects.equals(this.b, other.b)) {
            return false;
        }
        return (this.middle().equals(other.middle()) && this.start.distance(this.end)
                == other.start.distance(other.end));
    }
}
