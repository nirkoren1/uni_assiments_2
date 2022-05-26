package sprites;
// Nir Koren 316443902

import biuoop.DrawSurface;
import geometry_primitives.Point;
import java.awt.Color;

/**
 * this class represents a Circle Sprite.
 */
public class CircleSprite implements Sprite {
    private Integer radius;
    private Point center;
    private Color color;
    /**
     * the constructor.
     * @param center Point the center point of the circle to start
     * @param r radius
     * @param color java.awt.Color color
     */
    public CircleSprite(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle((int) center.getX(), (int) center.getY(), radius);
    }

    @Override
    public void timePassed() {

    }
}
