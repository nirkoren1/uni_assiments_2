// Nir koren 316443902
package listeners;
import game.Game;
import sprites.Ball;
import sprites.Block;

/**
 * The Ball remover.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Construct a new Ball remover.
     * @param game         the game
     * @param removedBalls the removed balls
     */
    public BallRemover(Game game, Counter removedBalls) {
        this.remainingBalls = removedBalls;
        this.game = game;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
