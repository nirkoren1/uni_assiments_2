package game;
// 316443902 Nir Koren

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * this class can create a new paddle and ball game and run it.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private KeyboardSensor keyboard;
    private int width = 800;
    private int height = 600;
    private List<Color> colors = new ArrayList<>(Arrays.asList(Color.RED, Color.MAGENTA, Color.YELLOW, Color.black,
            Color.CYAN, Color.GREEN));

    /**
     * add a Collidable to the environment Collidables list.
     * @param c the Collidable ob
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a Sprite ob to the sprites list.
     * @param s Sprite ob
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
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

        //create a new Ball objects, which will be used to break the blocks.
        Ball ball1 = new Ball(new Point(400, 500), 5, Color.WHITE, environment);
        ball1.setVelocity(Velocity.fromAngleAndSpeed(100, 3));
        ball1.addToGame(this);
        Ball ball2 = new Ball(new Point(400, 500), 5, Color.WHITE, environment);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(60, 3));
        ball2.addToGame(this);
        //create a new Paddle object, which will be used to bounce the ball.
        Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(350, height - borderSize - 16), 100,
                15), Color.ORANGE);
        paddle.addToGame(this);
        //create a new Block objects, which will be used to create the borders of the game.
        Rectangle border1 = new Rectangle(new Point(0, 0), width, borderSize);
        Rectangle border2 = new Rectangle(new Point(0, borderSize), borderSize, height - borderSize);
        Rectangle border3 = new Rectangle(new Point(width - borderSize, borderSize), borderSize,
                height - borderSize);
        Rectangle border4 = new Rectangle(new Point(0, height - borderSize), width, borderSize);
        List<Rectangle> rectangles = new ArrayList<>(Arrays.asList(border1, border2, border3, border4));
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
            }
        }
        for (Block block:blocks) {
            block.addToGame(this);
        }
    }
    /**
     * the main animation loop of the game.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            //creates a new DrawSurface object, which is the object that we can draw on.
            DrawSurface d = gui.getDrawSurface();
            //draws the background of the game.
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, width, height);
            //draws all the sprites.
            this.sprites.drawAllOn(d);
            //shows the drawing.
            gui.show(d);
            //notifies all the sprites that time has passed.
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            //waits for a short period of time.
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     * create new game, initialize it and runs it.
     * @param args from cmd
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}