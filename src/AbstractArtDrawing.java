// Nir Koren 316443902

import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class AbstractArtDrawing {
    public static final int WIDTH = 400;
    public static final int HEIGHT  = 300;

    public void createRandomLine() {
        Random rand = new Random();

    }

    public void drawLineToSurface(DrawSurface sur) {

    }

    public void drawRandomLines() {

    }

    public static void main(String[] args) {
        AbstractArtDrawing gui = new AbstractArtDrawing();
        gui.drawRandomLines();
    }
}
