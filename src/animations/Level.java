package animations;
// Nir koren 316443902
import infos.Velocity;
import sprites.Block;
import sprites.Sprite;
import java.util.List;

/**
 * abstract class of a level.
 */
public class Level implements LevelInformation {
    private int numOfBalls;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocksList;
    private int blocksToRemove;

    /**
     * construct.
     * @param numOfBalls
     * @param velocities
     * @param paddleSpeed
     * @param paddleWidth
     * @param levelName
     * @param background
     * @param blocksList
     */
    public Level(int numOfBalls, List<Velocity> velocities, int paddleSpeed, int paddleWidth, String levelName,
                 Sprite background, List<Block> blocksList) {
        this.numOfBalls = numOfBalls;
        this.velocities = velocities;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = background;
        this.blocksList = blocksList;
    }

    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksToRemove;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocksList;
    }
}
