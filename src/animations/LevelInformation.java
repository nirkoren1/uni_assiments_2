package animations;
// Nir koren 316443903
import infos.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * @return the number of balls for the level
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed.
     * @return the speed
     */
    int paddleSpeed();

    /**
     * Paddle width.
     * @return the width
     */
    int paddleWidth();

    /**
     * Level name string.
     * @return the name
     */
    String levelName();

    /**
     * Gets background.
     * @return the background
     */
// Returns a sprite with the background of the level
    List<Sprite> getBackground();

    /**
     * @return the blocks list
     */
// The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();

    /**
     * @return Number of blocks to remove.
     */
// Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();
}
