// Nir Koren 316443902

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

public class MultipleBouncingBallsAnimation {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    static Ball addBall(int size) {
        Random random = new Random();
        Point start = new Point(random.nextInt(WIDTH), random.nextInt(HEIGHT));
        Ball ball = new Ball(start, size, getRandomColor(), 0, 0, WIDTH, HEIGHT);
        double speed = Math.max(-(size / 6.) + 10, -(50 / 6.) + 10);
        Velocity vel = Velocity.fromAngleAndSpeed(random.nextInt(360), speed);
        ball.setVelocity(vel.getDx(), vel.getDy());
        return ball;
    }
    static Color getRandomColor() {
        Random random = new Random();
        Color[] colors = {Color.RED, Color.BLACK, Color.orange, Color.GREEN, Color.BLUE, Color.MAGENTA};
        return colors[random.nextInt(colors.length)];
    }
    static private void drawAnimation(int[] ballsSizes) {
        Arrays.sort(ballsSizes);
        System.out.println(ballsSizes[0]);
        GUI gui = new GUI("Bouncing Balls!", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball[] balls = new Ball[ballsSizes.length];
        for (int i = 0; i < ballsSizes.length; i++) {
            balls[i] = addBall(ballsSizes[balls.length - i - 1]);
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < ballsSizes.length; i++) {
                balls[i].moveOneStep(0, 0, WIDTH, HEIGHT);
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    public static void main(String[] args) {
        int[] sizes = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            sizes[i] = Integer.parseInt(args[i]);
        }
        MultipleBouncingBallsAnimation.drawAnimation(sizes);
    }
}
