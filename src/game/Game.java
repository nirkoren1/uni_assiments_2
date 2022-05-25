package game;
// 316443902 Nir Koren

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;
import geometry_primitives.Point;
import geometry_primitives.Rectangle;
import infos.Velocity;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.Counter;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.Collidable;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * this class can create a new paddle and ball game and run it.
 */
public class Game implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private KeyboardSensor keyboard;
    private int width = 800;
    private int height = 600;
    // blocks counter and remover
    private Counter remainingBlocks = new Counter();
    private BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
    // balls counter and remover
    private Counter remainingBalls = new Counter();
    private BallRemover ballRemover = new BallRemover(this, remainingBalls);
    // score Counter and listener
    private Counter currentScore = new Counter();
    private ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(currentScore);
    // color list for regular use
    private List<Color> colors = new ArrayList<>(Arrays.asList(Color.RED, Color.MAGENTA, Color.YELLOW, Color.black,
            Color.CYAN, Color.GREEN));
    private AnimationRunner runner;
    private boolean running;

    /**
     * add a Collidable to the environment Collidables list.
     *
     * @param c the Collidable ob
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a Sprite ob to the sprites list.
     *
     * @param s Sprite ob
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, width, height);
        //draws all the sprites.
        this.sprites.drawAllOn(d);
        //notifies all the sprites that time has passed.
        this.sprites.notifyAllTimePassed();
        if (remainingBlocks.getValue() == 0) {
            currentScore.increase(100);
            gui.close();
            this.running = false;
        }
        if (remainingBalls.getValue() == 0) {
            gui.close();
            this.running = false;
        }
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle).
     * and add them to the game.
     */
    public void initialize() {
        //create a new GUI object, which is the window that will display the game.
        this.gui = new GUI("Game!", 800, 600);
        //create a new SpriteCollection object, which will hold all the sprites in the game.
        this.sprites = new SpriteCollection();
        //create a new Sleeper object, which will be used to control the frames per second.
        this.sleeper = new Sleeper();
        //create a new GameEnvironment object, which will hold all the collidables in the game.
        this.environment = new GameEnvironment();
        //create a new KeyboardSensor object, which will be used to control the paddle.
        this.keyboard = gui.getKeyboardSensor();
        int borderSize = 30;
        // create the animation runner
        this.runner = new AnimationRunner(60, this.gui, this.sleeper);

        //create a new Ball objects, which will be used to break the blocks.
        Ball ball1 = new Ball(new Point(400, 500), 5, Color.WHITE, environment);
        ball1.setVelocity(Velocity.fromAngleAndSpeed(100, 3));
        ball1.addToGame(this);
        Ball ball2 = new Ball(new Point(400, 500), 5, Color.WHITE, environment);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(60, 3));
        ball2.addToGame(this);
        Ball ball3 = new Ball(new Point(400, 500), 5, Color.WHITE, environment);
        ball3.setVelocity(Velocity.fromAngleAndSpeed(40, 3));
        ball3.addToGame(this);


        //create a new Paddle object, which will be used to bounce the ball.
        Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(350, height - borderSize + 15), 100,
                15), Color.ORANGE);
        paddle.addToGame(this);

        //create a new Block objects, which will be used to create the borders of the game.
        Rectangle border1 = new Rectangle(new Point(0, 0), width, borderSize);
        Rectangle border2 = new Rectangle(new Point(0, borderSize), borderSize, height - borderSize);
        Rectangle border3 = new Rectangle(new Point(width - borderSize, borderSize), borderSize,
                height - borderSize);

        // create and assign the deathBorder to Ball remover
        Rectangle deathBorder = new Rectangle(new Point(-100, height - borderSize + 40), width + 100, borderSize);
        Block deathBorderBlock = new Block(deathBorder, Color.GRAY);
        deathBorderBlock.addHitListener(ballRemover);
        deathBorderBlock.addToGame(this);

        // add the borders to a blocks list
        List<Rectangle> rectangles = new ArrayList<>(Arrays.asList(border1, border2, border3));
        List<Block> blocks = new ArrayList<>();
        for (Rectangle rectangle:rectangles) {
            Block block = new Block(rectangle, Color.GRAY);
            blocks.add(block);
        }

        //create a new Block objects, which will be used to create the blocks that the ball will break.
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12 - i; j++) {
                Rectangle rectangle = new Rectangle(new Point(width - borderSize - 50 * (j + 1), 150 + i * 18),
                        50, 18);
                Block block = new Block(rectangle, colors.get(i));
                blocks.add(block);
                block.addHitListener(this.blockRemover);
                block.addHitListener(this.scoreTrackingListener);
                this.remainingBlocks.increase(1);
            }
        }
        for (Block block:blocks) {
            block.addToGame(this);
        }

        // add the score sprite
        ScoreIndicator scoreIndicator = new ScoreIndicator(currentScore);
        scoreIndicator.addToGame(this);
    }

    /**
     * the main animation loop of the game.
     */
    public void run() {
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Remove collidable.
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.addToRemoveList(s);
    }

    /**
     * Gets remaining balls.
     * @return the remaining balls counter
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * create new game, initialize it and runs it.
     *
     * @param args from cmd
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}