package game;
// Nir Koren 316443902

/**
 * velocity of the ball.
 * can be changed and translate from angle to speed.
 */
public class Velocity {
    private Double dx = null;
    private Double dy = null;

    /**
     * translate a given angle and speed to Velocity ob.
     * @param angle double angle in degrees.
     * @param dx current dx
     * @param dy current dy
     * @return Velocity of the ball translated from the given parameters
     */
    public static Velocity fromAngleAndSpeed(double angle, double dx, double dy) {
        // pitagoras
        double speed = Math.sqrt(dx * dx + dy * dy);
        double newDx = speed * Math.sin(angle * Math.PI / 180);
        double newDy = -speed * Math.cos(angle * Math.PI / 180);
        return new Velocity(newDx, newDy);
    }

    /**
     * translate a given angle and speed to Velocity ob.
     * @param angle double angle in degrees.
     * @param speed speed desired
     * @return Velocity of the ball translated from the given parameters
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double newDx = speed * Math.sin(angle * Math.PI / 180);
        double newDy = -speed * Math.cos(angle * Math.PI / 180);
        return new Velocity(newDx, newDy);
    }

    /**
     * constructor.
     * @param dx dx speed desired
     * @param dy dy speed desired
     */
    public Velocity(double dx, double dy) {
        setValues(dx, dy);
    }

    /**
     * set dx and dy values.
     * @param dx dx speed desired
     * @param dy dy speed desired
     */
    public void setValues(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return dx
     */
    public Double getDx() {
        return dx;
    }
    /**
     * @return dy
     */
    public Double getDy() {
        return dy;
    }

    /**
     * add a point location the velocity.
     * @param p Point of the ob
     * @return a new Point with the addition of the velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}
