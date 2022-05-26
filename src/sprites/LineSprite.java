package sprites;
// Nir Koren 316443902

import biuoop.DrawSurface;
import geometry_primitives.Line;
import java.awt.Color;

/**
 * this class represents a Line Sprite.
 */
public class LineSprite implements Sprite {
    private Line line;
    private Color color;
    /**
     * the constructor.
     * @param line Line the line to draw
     * @param color Color the color of the line
     */
    public LineSprite(Line line, Color color) {
        this.line = line;
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.line.start().getX(), (int) this.line.start().getY(),
                (int) this.line.end().getX(), (int) this.line.end().getY());
    }

    @Override
    public void timePassed() {

    }
}
