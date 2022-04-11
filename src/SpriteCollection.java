// 316443902 Nir Koren

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * this class holds all the sprites in the game.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<>();
    /**
     * add a Sprite to the Sprites List.
     * @param s the new Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite s:sprites) {
            s.timePassed();
        }
    }
    /**
     * call drawOn(d) on all sprites.
     * @param d DrawSurface that the sprites will be drawn on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s:sprites) {
            s.drawOn(d);
        }
    }
}