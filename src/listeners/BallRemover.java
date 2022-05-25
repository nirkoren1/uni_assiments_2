// Nir koren 316443902
package listeners;
import animations.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * The Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Construct a new Ball remover.
     * @param gameLevel         the game
     * @param removedBalls the removed balls
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.remainingBalls = removedBalls;
        this.gameLevel = gameLevel;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}
