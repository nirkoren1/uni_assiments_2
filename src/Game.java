// 316443902 Nir Koren

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private int width = 800;
    private int height = 600;
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
        Ball ball = new Ball(new Point(10, 10), 5, java.awt.Color.BLACK, environment);
        ball.setVelocity(-2, 1);
        ball.addToGame(this);
        Rectangle rectangle1 = new Rectangle(new Point(0, 0), width, 0);
        Rectangle rectangle2 = new Rectangle(new Point(0, 0), 0, height);
        Rectangle rectangle3 = new Rectangle(new Point(width, 0), 0, height);
        Rectangle rectangle4 = new Rectangle(new Point(0, height), width, 0);
        List<Rectangle> rectangles = new ArrayList<>(Arrays.asList(rectangle1, rectangle2, rectangle3, rectangle4));
        for (int i = 0; i < 10; i++) {
            rectangles.add(new Rectangle(new Point(20 * (i + 1), 20), 19, 10));
        }
        for (Rectangle rectangle:rectangles) {
            Block block = new Block(rectangle, Color.BLUE);
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