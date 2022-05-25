package game;
// Nir koren 316443902

import biuoop.DrawSurface;
import sprites.SpriteCollection;

import java.awt.*;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    SpriteCollection gameScreen;
    int countFrom;
    private boolean stop;
    double timePerNumber;
    /**
     * Construct a new Countdown animation.
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.timePerNumber = numOfSeconds / countFrom;
        this.gameScreen = gameScreen;
    }
    public void doOneFrame(DrawSurface d) {
        // background currently
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, width, height);
        //draws all the sprites.
        this.gameScreen.drawAllOn(d);

    }
    public boolean shouldStop() { ... }
}
