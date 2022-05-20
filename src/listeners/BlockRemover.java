// Nir koren 316443902
package listeners;

import game.Game;
import sprites.Ball;
import sprites.Block;

/**
 * The Block remover.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Construct a new Block remover.
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.remainingBlocks = removedBlocks;
        this.game = game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}