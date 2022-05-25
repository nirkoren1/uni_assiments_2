// Nir koren 316443902
package listeners;

import animations.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * The Block remover.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Construct a new Block remover.
     * @param gameLevel          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.remainingBlocks = removedBlocks;
        this.gameLevel = gameLevel;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}