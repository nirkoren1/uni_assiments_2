package game;
// 316443902 Nir Koren

import biuoop.DrawSurface;

/**
 * this interface is for the Sprite classes.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d DrawSurface of the game
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}