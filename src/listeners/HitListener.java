// Nir koren 316443902
package listeners;

import sprites.Ball;
import sprites.Block;

/**
 * The interface Hit listener.
 */
public interface HitListener {

    /**
     * Hit event.
     * called whenever the beingHit object is hit
     * @param beingHit the being hit
     * @param hitter   the hitter (Ball)
     */
    void hitEvent(Block beingHit, Ball hitter);
}
