// Nir Koren 316443902

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

/**
 * this class create two frames and multiple Balls, then bounce them in the two frames.
 * this class contains the frames boundaries and the width and height of the window.
 */
public class MultipleFramesBouncingBallsAnimation {
    public static final Frame FRAME1 = new Frame(50, 50, 500, 500, Color.GRAY);
    public static final Frame FRAME2 = new Frame(450, 450, 600, 600, Color.YELLOW);
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    /**
     * create a new Ball.
     * calculating the speed of the ball according to his size (bigger are slower, slowest are 50+)
     * @param size the size of the ball (radius)
     * @param xStart start x of the frame
     * @param yStart start y of the frame
     * @param xEnd end x of the frame
     * @param yEnd end y of the frame
     * @return Ball object
     */
    static Ball addBall(int size, int xStart, int yStart, int xEnd, int yEnd) {
        Random random = new Random();
        Point start = new Point(random.nextInt(xStart, xEnd), random.nextInt(yStart, yEnd));
        Ball ball = new Ball(start, size, getRandomColor(), xStart, yStart, xEnd, yEnd);
        double speed = Math.max(-(size / 6.) + 10, -(50 / 6.) + 10);
        Velocity vel = Velocity.fromAngleAndSpeed(random.nextInt(360), speed);
        ball.setVelocity(vel.getDx(), vel.getDy());
        return ball;
    }

    /**
     * crete a random color.
     * @return java.awt.Color obj
     */
    static Color getRandomColor() {
        Random random = new Random();
        Color[] colors = {Color.RED, Color.BLACK, Color.orange, Color.GREEN, Color.BLUE, Color.MAGENTA};
        return colors[random.nextInt(colors.length)];
    }

    /**
     * create an array of balls, then move all of them in a loop.
     * drawing two frames, the first half of the balls bounce in the first frame and the second half in the second
     * frame.
     * @param ballsSizes int array, the sizes of the balls
     */
    private static void drawAnimation(int[] ballsSizes) {
        Arrays.sort(ballsSizes, 0, ballsSizes.length / 2);   // sorting so that smaller balls are on front
        Arrays.sort(ballsSizes, ballsSizes.length / 2, ballsSizes.length); // sorting so that smaller balls are on front
        GUI gui = new GUI("Bouncing Balls With Frames!", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball[] balls = new Ball[ballsSizes.length];
        for (int i = 0; i < ballsSizes.length; i++) {
            if (i < balls.length / 2) {
                balls[i] = addBall(ballsSizes[balls.length / 2 - i - 1], FRAME1.getxStart(), FRAME1.getyStart(),
                        FRAME1.getxEnd(), FRAME1.getyEnd());
                continue;
            }
            balls[i] = addBall(ballsSizes[balls.length / 2 - i - 1 + balls.length], FRAME2.getxStart(),
                    FRAME2.getyStart(), FRAME2.getxEnd(), FRAME2.getyEnd());
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            FRAME1.drawON(d);
            FRAME2.drawON(d);
            for (int i = 0; i < ballsSizes.length; i++) {
                if (i < balls.length / 2) {
                    balls[i].moveOneStep(FRAME1.getxStart(), FRAME1.getyStart(), FRAME1.getxEnd(),
                            FRAME1.getyEnd());  //first half
                } else {
                    balls[i].moveOneStep(FRAME2.getxStart(), FRAME2.getyStart(), FRAME2.getxEnd(),
                            FRAME2.getyEnd());  //second half
                }
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * this main function takes balls sizes from the cmd and calls the drawAnimation method.
     * @param args String array - the sizes of the balls
     */
    public static void main(String[] args) {
        int[] sizes = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            sizes[i] = Integer.parseInt(args[i]);
        }
        MultipleFramesBouncingBallsAnimation.drawAnimation(sizes);
    }
}
