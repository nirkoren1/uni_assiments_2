package geometry_primitives;
// Nir Koren 316443902

/**
 * class point have x and y value, and can compare between two points and measure distance.
 */
public class Point {
    private double x;
    private double y;
    /**
     * @param x - x value
     * @param y - y value
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * distance -- return the distance of this point to the other point.
     * @param other - the other dot
     * @return the distance between the two dots using pitagoras
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
    /**
     * equals -- return true is the points are equal, false otherwise.
     * @param other - the other point
     * @return true - equal, false - not equal
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }
    /**
     * @return the x value of the point
     */
    public double getX() {
        return this.x;
    }
    /**
     * @return the y value of the point
     */
    public double getY() {
        return this.y;
    }
    /**
     * set the x of the point.
     * @param x x desired
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * set the y of the point.
     * @param y y desired
     */
    public void setY(double y) {
        this.y = y;
    }
}