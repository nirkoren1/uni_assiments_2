// Nir Koren 316443902

import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class AbstractArtDrawing {
    public static final int WIDTH = 400;
    public static final int HEIGHT  = 300;
    public static final int NUMOFLINES  = 20;

    public Line createRandomLine() {
        Random rand = new Random();

    }

    public void drawLineToSurface(DrawSurface sur, Line line) {
        sur.drawLine((int) line.start().getX(), (int) line.start().getY(), (int) line.end().getX(),
                (int) line.end().getY());
        sur.drawCircle();
        sur.drawCircle();
        sur.drawCircle();
    }

    public void drawIntersections ()

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
