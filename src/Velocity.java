// Nir Koren 316443902

public class Velocity {
    private Double dx = null;
    private Double dy = null;
    public static Velocity fromAngleAndSpeed(double angle, double dx, double dy) {
        double speed = Math.sqrt(dx * dx + dy * dy);
        double newDx = speed * Math.sin(angle * Math.PI / 180);
        double newDy = -speed * Math.cos(angle * Math.PI / 180);
        return new Velocity(newDx, newDy);
    }
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double newDx = speed * Math.sin(angle * Math.PI / 180);
        double newDy = -speed * Math.cos(angle * Math.PI / 180);
        return new Velocity(newDx, newDy);
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
