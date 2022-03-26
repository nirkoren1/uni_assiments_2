// Nir Koren 316443902

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * this class represents a frame (rectangle).
 */
public class Frame {
    private final Integer xStart;
    private final Integer yStart;
    private final Integer xEnd;
    private final Integer yEnd;
    private final Color color;
    /**
     * the constructor.
     * @param xStart start x of the frame
     * @param yStart start y of the frame
     * @param xEnd end x of the frame
     * @param yEnd end y of the frame
     * @param color Color of the rectangle
     */
    public Frame(int xStart, int yStart, int xEnd, int yEnd, Color color) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.color = color;
    }
    /**
     * @return end x
     */
    public Integer getxEnd() {
        return this.xEnd;
    }
    /**
     * @return end y
     */
    public Integer getyEnd() {
        return this.yEnd;
    }
    /**
     * @return start x
     */
    public Integer getxStart() {
        return this.xStart;
    }
    /**
     * @return start y
     */
    public Integer getyStart() {
        return this.yStart;
    }
    /**
     * draw the frame (rectangle) to a surface.
     * @param surface DrawSurface to draw on
     */
    public void drawON(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle(this.xStart, this.yStart, this.xEnd - this.xStart, this.yEnd - this.yStart);
    }
}
