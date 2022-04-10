import java.awt.*;
import java.util.Arrays;
import java.util.List;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
public class tester {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing Ball!", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        GameEnvironment env = new GameEnvironment();
        Rectangle rectangle1 = new Rectangle(new Point(100, 20), 25, 30);
        Rectangle rectangle2 = new Rectangle(new Point(20, 30), 25, 30);
        Rectangle rectangle3 = new Rectangle(new Point(15, 100), 25, 30);
        Rectangle rectangle4 = new Rectangle(new Point(90, 37), 25, 30);
        Rectangle rectangle5 = new Rectangle(new Point(0, 0), WIDTH, 0);
        Rectangle rectangle6 = new Rectangle(new Point(0, 0), 0, HEIGHT);
        Rectangle rectangle7 = new Rectangle(new Point(WIDTH, 0), 0, HEIGHT);
        Rectangle rectangle8 = new Rectangle(new Point(0, HEIGHT), WIDTH, 0);
        Block block1 = new Block(rectangle1, Color.RED);
        Block block2 = new Block(rectangle2, Color.RED);
        Block block3 = new Block(rectangle3, Color.RED);
        Block block4 = new Block(rectangle4, Color.RED);
        Block block5 = new Block(rectangle5, Color.RED);
        Block block6 = new Block(rectangle6, Color.RED);
        Block block7 = new Block(rectangle7, Color.RED);
        Block block8 = new Block(rectangle8, Color.RED);
        List<Block> blocks = Arrays.asList(block1, block2, block3, block4, block5, block6, block7, block8);
        for (Block block:blocks) {
            env.addCollidable(block);
        }
        Ball ball = new Ball(start, 5, java.awt.Color.BLACK, env);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            for (Block block: blocks) {
                block.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(10);  // wait for 50 milliseconds.
        }
    }
    public static void main(String[] args) {
        tester.drawAnimation(new Point(10, 10), 1, -1);
    }
}
