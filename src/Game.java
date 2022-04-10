// 316443902 Nir Koren

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        this.gui = new GUI("Game!", 800, 600);
        this.sprites = new SpriteCollection();
        this.sleeper = new Sleeper();
        this.environment = new GameEnvironment();
        this.keyboard = gui.getKeyboardSensor();
        int borderSize = 30;
        Ball ball1 = new Ball(new Point(400, 500), 5, Color.WHITE, environment);
        ball1.setVelocity(Velocity.fromAngleAndSpeed(100, 3));
        ball1.addToGame(this);
        Ball ball2 = new Ball(new Point(400, 500), 5, Color.WHITE, environment);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(60, 3));
        ball2.addToGame(this);
        Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(350, height - borderSize - 20), 100,
                19), Color.ORANGE);
        paddle.addToGame(this);
        Rectangle border1 = new Rectangle(new Point(0, 0), width, borderSize);
        Rectangle border2 = new Rectangle(new Point(0, borderSize), borderSize, height - borderSize);
        Rectangle border3 = new Rectangle(new Point(width - borderSize, borderSize), borderSize, height - borderSize);
        Rectangle border4 = new Rectangle(new Point(0, height - borderSize), width, borderSize);
        List<Rectangle> rectangles = new ArrayList<>(Arrays.asList(border1, border2, border3, border4));
        List<Block> blocks = new ArrayList<>();
        for (Rectangle rectangle:rectangles) {
            Block block = new Block(rectangle, Color.GRAY);
            blocks.add(block);
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12 - i; j++) {
                Rectangle rectangle = new Rectangle(new Point(width - borderSize - 50 * (j + 1), 150 + i * 18), 49, 16);
                Block block = new Block(rectangle, colors.get(i));
                blocks.add(block);
            }
        }
        for (Block block:blocks) {
            block.addToGame(this);
        }
    }

    // Run the game -- start the animation loop.
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, width, height);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}