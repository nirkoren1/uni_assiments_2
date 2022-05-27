package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

// Nir Koren 316443902
/**
 * decorator for the animations.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private Animation innerAnimation;
    private boolean stop;
    private String key;
    private boolean isAlreadyPressed = true;
    /**
     * Construct a new KeyPressStoppableAnimation.
     * @param k the keyboard sensor
     * @param innerAnimation the inner animation
     * @param key the key
     */
    public KeyPressStoppableAnimation(KeyboardSensor k, Animation innerAnimation, String key) {
        this.keyboard = k;
        this.innerAnimation = innerAnimation;
        this.stop = false;
        this.key = key;
    }

    /**
     * constructor.
     * @param k the keyboard sensor
     * @param key the key
     */
    public KeyPressStoppableAnimation(KeyboardSensor k, String key) {
        this.keyboard = k;
        this.stop = false;
        this.key = key;
    }
    /**
     * @param innerAnimation the inner animation
     */
    public void setInnerAnimation(Animation innerAnimation) {
        this.innerAnimation = innerAnimation;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.innerAnimation.doOneFrame(d);
        if (this.keyboard.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
                this.isAlreadyPressed = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
