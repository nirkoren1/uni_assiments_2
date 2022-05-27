// Nir Koren 316443902
package sprites;

import biuoop.DrawSurface;
import listeners.Counter;
import animations.GameLevel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;
    private List<Color> colors = new ArrayList<>(Arrays.asList(Color.BLACK, Color.BLUE));
    private int animationCounter = 0;
    private int colorIndex = 0;
    private int fontSize = 20;
    private String levelName;

    /**
     * Construct a new Score indicator.
     * @param currentScore the current score
     * @param levelName the level name
     */
    public ScoreIndicator(Counter currentScore, String levelName) {
        this.currentScore = currentScore;
        this.levelName = levelName;
    }
    @Override
    public void drawOn(DrawSurface d) {
        // adding color change for fun
        if (animationCounter % 20 == 0) {
            colorIndex += 1;
            fontSize = 21;
            if (colorIndex == colors.size()) {
                colorIndex = 0;
                fontSize = 20;
            }
        }
        d.setColor(this.colors.get(colorIndex));
        d.drawText(350, 23, "SCORE: " + currentScore.getValue(), fontSize);
        d.setColor(Color.BLACK);
        d.drawText(560, 23, "Level Name: " + this.levelName, 20);
        this.animationCounter += 1;
    }

    @Override
    public void timePassed() {

    }

    /**
     * Add to game.
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
