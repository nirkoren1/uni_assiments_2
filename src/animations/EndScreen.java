package animations;
// Nir Koren 316443902
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import listeners.Counter;

import java.awt.Color;

/**
 * The class EndScreen.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private String message;
    /**
     * Construct a new Pause screen.
     * @param k the keyboard sensor
     * @param score the score counter
     * @param message "You Win" or "Game Over"
     */
    public EndScreen(KeyboardSensor k, Counter score, String message) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
        this.message = message;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.message.equals("You Win")) {
            d.setColor(Color.BLACK);
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
        } else {
            d.setColor(Color.RED);
            d.drawText(10, d.getHeight() / 2, "Game Over! Your score is " + this.score.getValue(), 32);
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
