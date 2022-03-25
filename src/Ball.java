// Nir Koren 316443902

import biuoop.DrawSurface;
import java.awt.Color;

public class Ball {
    private Integer radius = null;
    private Color color = null;
    private Point center = new Point(0, 0);
    private Velocity velocity = new Velocity(0, 0);
    public Ball(Point center, int r, java.awt.Color color, int width, int height) {
        int x = Math.min(Math.max((int) center.getX(), r), width - r);
        int y = Math.min(Math.max((int) center.getY(), r), height - r);
        this.center.setX(x);
        this.center.setY(y);
        this.radius = r;
        this.color = color;
    }

    public int getX() {
        return (int) this.center.getX();
    };
    public int getY() {
        return (int) this.center.getY();
    }
    public int getSize() {
        return this.radius;
    }
    public java.awt.Color getColor() {
        return this.color;
    }
    public void setVelocity(Velocity v) {
        this.velocity.setValues(v.getDx(), v.getDy());
    }
    public void setVelocity(double dx, double dy) {
        this.velocity.setValues(dx, dy);
    }
    public Velocity getVelocity() {
        return this.velocity;
    }
    public void moveOneStep(int width, int height) {
        this.center = this.getVelocity().applyToPoint(this.center);
        if (this.getX() + this.velocity.getDx() + this.radius >= width
                || this.getX() + this.velocity.getDx() <= this.radius) {
            this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
        }
        if (this.getY() + this.velocity.getDy() + this.radius >= height
                || this.getY() + this.velocity.getDy() <= this.radius) {
            this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
        }
    }
    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), this.radius);
    };
}
