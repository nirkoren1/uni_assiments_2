// Nir Koren 316443902

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class BouncingBallAnimation {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing Ball!", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start, 30, java.awt.Color.BLACK, WIDTH, HEIGHT);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep(WIDTH, HEIGHT);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    public static void main(String[] args) {
        Point start = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        BouncingBallAnimation.drawAnimation(start, Integer.parseInt(args[2]), Integer.parseInt(args[3]));
    }

}
