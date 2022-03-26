// Nir Koren 316443902

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

public class MultipleFramesBouncingBallsAnimation {
    public static final int FRAME1XSTART = 50;
    public static final int FRAME1YSTART = 50;
    public static final int FRAME1XEND = 500;
    public static final int FRAME1YEND = 500;
    public static final int FRAME2XSTART = 450;
    public static final int FRAME2YSTART = 450;
    public static final int FRAME2XEND = 600;
    public static final int FRAME2YEND = 600;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    static Ball addBall(int size, int xStart, int yStart, int xEnd, int yEnd) {
        Random random = new Random();
        Point start = new Point(random.nextInt(xStart, xEnd), random.nextInt(yStart, yEnd));
        Ball ball = new Ball(start, size, getRandomColor(), xStart, yStart, xEnd, yEnd);
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
            if (i <= balls.length / 2) {
                balls[i] = addBall(ballsSizes[balls.length - i - 1], FRAME1XSTART, FRAME1YSTART, FRAME1XEND,
                        FRAME1YEND);
                continue;
            }
            balls[i] = addBall(ballsSizes[balls.length - i - 1], FRAME2XSTART, FRAME2YSTART, FRAME2XEND, FRAME2YEND);
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle(FRAME1XSTART, FRAME1YSTART, FRAME1XEND - FRAME1XSTART, FRAME1YEND - FRAME1YSTART);
            d.setColor(Color.YELLOW);
            d.fillRectangle(FRAME2XSTART, FRAME2YSTART, FRAME2XEND - FRAME2XSTART, FRAME2YEND - FRAME2YSTART);
            for (int i = 0; i < ballsSizes.length; i++) {
                if (i <= balls.length / 2) {
                    balls[i].moveOneStep(FRAME1XSTART, FRAME1YSTART, FRAME1XEND, FRAME1YEND);
                } else {
                    balls[i].moveOneStep(FRAME2XSTART, FRAME2YSTART, FRAME2XEND, FRAME2YEND);
                }
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
        MultipleFramesBouncingBallsAnimation.drawAnimation(sizes);
    }
}
