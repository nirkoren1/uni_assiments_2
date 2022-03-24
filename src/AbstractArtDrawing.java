// Nir Koren 316443902

import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class AbstractArtDrawing {
    public static final int WIDTH = 400;
    public static final int HEIGHT  = 300;
    public static final int NUMOFLINES  = 20;
    public static final int RADIUS  = 3;

    public Line createRandomLine() {
        Random rand = new Random();

    }

    public void drawLineToSurface(DrawSurface sur, Line line) {
        sur.drawLine((int) line.start().getX(), (int) line.start().getY(), (int) line.end().getX(),
                (int) line.end().getY());
    }

    public void drawCircles(DrawSurface sur, Line[] lines) {
        for (int i = 0; i < NUMOFLINES; i++) {
            Point mid = lines[i].middle();
            sur.drawCircle((int) mid.getX(), (int) mid.getY(), RADIUS);
            for (int j = 0; j < NUMOFLINES; j++) {
                if (j == i) {
                    continue;
                }
            }
        }
    };

    public void drawRandomLines() {
        GUI gui = new GUI("Random Circles Example", WIDTH, HEIGHT);
        DrawSurface sur = gui.getDrawSurface();
        Line[] lines = new Line[NUMOFLINES];
        for (int i = 0; i < NUMOFLINES; i++) {
            lines[i] = createRandomLine();
            drawLineToSurface(sur, lines[i]);
        }
        gui.show(sur);
    }

    public static void main(String[] args) {
        AbstractArtDrawing gui = new AbstractArtDrawing();
        gui.drawRandomLines();
    }
}
