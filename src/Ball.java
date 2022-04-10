// Nir Koren 316443902

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * this class represents a ball.
 * a ball have Velocity, a center point, size (radius) and color.
 */
public class Ball implements Sprite {
    private Integer radius = null;
    private Color color = null;
    private Point center = new Point(0, 0);
    private Velocity velocity = new Velocity(0, 0);
    private GameEnvironment environment;
    /**
     * the constructor.
     * the constructor assign all the members of the Ball.
     * it prevent the ball from starting outside the frame boundaries.
     * @param center Point the center point of the ball to start
     * @param r radius
     * @param color java.awt.Color color
     * @param environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        this.environment = environment;
        this.center.setX(center.getX());
        this.center.setY(center.getY());
        this.radius = r;
        this.color = color;
    }

    /**
     * @return center x value
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * @return center y value
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * @return size value
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * @return java.awt.Color color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * set velocity from Velocity object.
     * using the setVelocity method
     * @param v Velocity object
     */
    public void setVelocity(Velocity v) {
        this.velocity.setValues(v.getDx(), v.getDy());
    }

    /**
     * set velocity from dx and dy values.
     * @param dx x-axis speed
     * @param dy y-axis speed
     */
    public void setVelocity(double dx, double dy) {
        this.velocity.setValues(dx, dy);
    }

    /**
     * @return Velocity object of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * change the direction of the speed if the ball hits the frame boundaries.
     */
    public void moveOneStep() {
        Point endPoint = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, endPoint);
        CollisionInfo collisionInfo = this.environment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        Point collision = collisionInfo.collisionPoint();
        this.center = new Point(-Math.signum(this.velocity.getDx()) + collision.getX(),
                -Math.signum(this.velocity.getDy()) + collision.getY());
        this.velocity = collisionInfo.collisionObject().hit(collision, this.velocity);

//        this.center = this.getVelocity().applyToPoint(this.center);
//        if (this.getX() + this.velocity.getDx() + this.radius >= xEnd
//                || this.getX() + this.velocity.getDx() <= this.radius + xStart) {
//            this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
//        }
//        if (this.getY() + this.velocity.getDy() + this.radius >= yEnd
//                || this.getY() + this.velocity.getDy() <= this.radius + yStart) {
//            this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
//        }
    }

    /**
     * draw ball of a surface.
     * @param surface a surface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
