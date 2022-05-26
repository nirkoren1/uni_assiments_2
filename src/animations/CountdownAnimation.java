package animations;
// Nir koren 316443902

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;
import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;
    private int countFrom;
    private boolean stop;
    private double timePerNumber;
    private int height;
    private int width;
    private int framesPerNum;
    private int counter = 0;
    private Sleeper sleeper = new Sleeper();
    /**
     * Construct a new Countdown animation.
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     * @param height the height
     * @param width the width
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, int width, int height) {
        this.countFrom = countFrom;
        this.timePerNumber = numOfSeconds / countFrom;
        this.gameScreen = gameScreen;
        this.height = height;
        this.width = width;
        this.framesPerNum = (int) (this.timePerNumber * 60);
    }
    private void drawNumber(DrawSurface d, int num) {
        d.setColor(Color.BLACK);
        d.fillCircle(this.width / 2 + 10, this.height / 2 - 12, 30);
        d.setColor(Color.WHITE);
        d.drawText(this.width / 2, this.height / 2, num + "", 40);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        //draws all the sprites.
        this.gameScreen.drawAllOn(d);
        this.drawNumber(d, this.countFrom - this.counter / this.framesPerNum);
        this.counter += 1;
    }
    @Override
    public boolean shouldStop() {
        return this.counter >= this.framesPerNum * this.countFrom;
    }
}
