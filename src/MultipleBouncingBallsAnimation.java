// Nir Koren 316443902

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Random;

public class MultipleBouncingBallsAnimation {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    static Ball addBall(int size) {
        Random random = new Random();
        Point start = new Point(random.nextInt(WIDTH), random.nextInt(HEIGHT));
        Ball ball = new Ball(start, size, java.awt.Color.BLACK, WIDTH, HEIGHT);
        double speed = Math.max(-(size / 6.) + 10, -(50 / 6.) + 10);
        Velocity vel = Velocity.fromAngleAndSpeed(random.nextInt(360), speed);
        ball.setVelocity(vel.getDx(), vel.getDy());
        return ball;
    }
    static private void drawAnimation(int[] ballsSizes, double dx, double dy) {
        GUI gui = new GUI("Bouncing Balls!", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball[] balls = new Ball[ballsSizes.length];
        for (int i = 0; i < ballsSizes.length; i++) {
            balls[i] = addBall(ballsSizes[i]);
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < ballsSizes.length; i++) {
                balls[i].moveOneStep(WIDTH, HEIGHT);
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    public static void main(String[] args) {
        Point start = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        MultipleBouncingBallsAnimation.drawAnimation(, Integer.parseInt(args[2]), Integer.parseInt(args[3]));
    }
}
