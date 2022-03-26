// Nir Koren 316443902

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * this class create an animation of a bouncing ball.
 */
public class BouncingBallAnimation {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    /**
     * create a Ball object, set a velocity and run a loop that moves the ball to the next point.
     * @param start Point the start point of the ball to start
     * @param dx the x-axis speed of the ball
     * @param dy the y-axis speed of the ball
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing Ball!", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start, 30, java.awt.Color.BLACK, 0, 0, WIDTH, HEIGHT);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep(0, 0, WIDTH, HEIGHT);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * this main method creates a start Point for the ball, then call drawAnimation method.
     * @param args String provided by the cmd, includes: xStart, yStart, dx, dy
     */
    public static void main(String[] args) {
        Point start = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        BouncingBallAnimation.drawAnimation(start, Integer.parseInt(args[2]), Integer.parseInt(args[3]));
    }
}
