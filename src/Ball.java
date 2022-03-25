// Nir Koren 316443902

import biuoop.DrawSurface;
import java.awt.Color;

public class Ball {
    private Integer radius = null;
    private Color color = null;
    private Point center = new Point(0, 0);
    private Velocity velocity = new Velocity(0, 0);
    public Ball(Point center, int r, java.awt.Color color) {
        this.center.setX((int) center.getX());
        this.center.setY((int) center.getY());
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
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }
    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), this.radius);
    };
}
