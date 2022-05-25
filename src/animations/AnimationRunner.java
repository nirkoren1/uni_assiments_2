package animations;
// nir koren 316443902

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The Class game.Animation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor.
     * @param framesPerSecond the frame per second
     * @param gui the gui used
     * @param sleeper the sleeper
     */
    public AnimationRunner(int framesPerSecond, GUI gui, Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = sleeper;
    }

    /**
     * Run the animation.
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}