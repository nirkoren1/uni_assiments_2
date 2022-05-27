package animations;
// Nir Koren 316443902
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import listeners.Counter;
import java.util.List;

/**
 * this class controls the levels.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;
    private Counter score = new Counter();
    private KeyPressStoppableAnimation spaceKeyPressStoppableAnimation;
    /**
     * constructor.
     */
    public GameFlow() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.animationRunner = new AnimationRunner(60, gui, new Sleeper());
        this.keyboardSensor = gui.getKeyboardSensor();
        this.spaceKeyPressStoppableAnimation = new KeyPressStoppableAnimation(this.keyboardSensor, "space");
    }
    /**
     * run the game.
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(spaceKeyPressStoppableAnimation);

            level.initialize(levelInfo, this.keyboardSensor, animationRunner, this.score);
            level.run();

            if (level.getRemainingBalls().getValue() == 0) {
                this.spaceKeyPressStoppableAnimation.setInnerAnimation(new EndScreen(this.keyboardSensor,
                        this.score, "YOU LOSE"));
                animationRunner.run(this.spaceKeyPressStoppableAnimation);
                this.gui.close();
                break;
            }
        }
        this.spaceKeyPressStoppableAnimation.setInnerAnimation(new EndScreen(this.keyboardSensor, this.score,
                "You Win"));
        animationRunner.run(this.spaceKeyPressStoppableAnimation);
        this.gui.close();
    }
}

