// Nir Koren 316443902

import biuoop.DrawSurface;
import java.awt.Color;

public class Ball {
    private Integer x = null;
    private Integer y = null;
    private Integer radius = null;
    private Color color = null;
    public Ball(Point center, int r, java.awt.Color color) {
        this.x = (int) center.getX();
        this.y = (int) center.getY();
        this.radius = r;
        this.color = color;
    }

    public int getX() {
        return this.x;
    };
    public int getY() {
        return this.y;
    }
    public int getSize() {
        return this.radius;
    }
    public java.awt.Color getColor() {
        return this.color;
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.x, this.y, this.radius);
    };
}
