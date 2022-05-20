package sprites;

import biuoop.DrawSurface;
import listeners.Counter;
import game.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreIndicator implements Sprite {
    private Counter currentScore;
    private List<Color> colors = new ArrayList<>(Arrays.asList(Color.BLACK, Color.BLUE));
    private int animationCounter = 0;
    private int colorIndex = 0;
    private int fontSize = 20;
    public ScoreIndicator(Counter currentScore) {
        this.currentScore = currentScore;
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
        d.drawText(375, 23, "SCORE: " + currentScore.getValue(), fontSize);
        this.animationCounter += 1;
    }

    @Override
    public void timePassed() {

    }
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
