package sprites;
// Nir Koren 316443902

import biuoop.DrawSurface;
import geometry_primitives.Point;
import java.awt.Color;

/**
 * this class represents a filled circle sprite.
 */
public class FillCircleSprite implements Sprite {
    private Integer radius;
    private Point center;
    private Color color;
    /**
     * the constructor.
     * @param center Point the center point of the circle to start
     * @param r radius
     * @param color java.awt.Color color
     */
    public FillCircleSprite(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    @Override
    public void timePassed() {

    }
}
