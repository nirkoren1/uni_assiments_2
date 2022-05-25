package game;
// Nir koren 316443902

import biuoop.DrawSurface;

/**
 * The interface game.Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     * @param d the DrawSurface ob
     */
    void doOneFrame(DrawSurface d);

    /**
     * return true if the animation should stop else: false.
     * @return the boolean
     */
    boolean shouldStop();
}