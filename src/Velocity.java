// Nir Koren 316443902

public class Velocity {
    private Double dx = null;
    private Double dy = null;
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(angle + 90);
        double dy = -speed * Math.sin(angle + 90);
        return new Velocity(dx, dy);
    }
    public Velocity(double dx, double dy) {
        setValues(dx, dy);
    }
    public void setValues(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    public Double getDx() {
        return dx;
    }
    public Double getDy() {
        return dy;
    }
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}
