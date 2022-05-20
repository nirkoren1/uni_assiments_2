// Nir koren 316443902
package listeners;

import sprites.Ball;
import sprites.Block;

/**
 * The Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Construct a new Score tracking listener.
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }
}
