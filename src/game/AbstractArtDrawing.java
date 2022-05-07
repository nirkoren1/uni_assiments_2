//// Nir Koren 316443902
//
//import biuoop.GUI;
//import biuoop.DrawSurface;
//import java.util.Random;
//import java.awt.Color;
//
///**
// * This class draws a modern art using lines and circles.
// */
//public class AbstractArtDrawing {
//    public static final int WIDTH = 400;
//    public static final int HEIGHT  = 300;
//    public static final int NUMOFLINES  = 10;
//    public static final int RADIUS  = 3;
//    /**
//     * create a random line.
//     * @return the random line
//     */
//    public Line createRandomLine() {
//        Random rand = new Random();
//        return new Line(rand.nextInt(WIDTH), rand.nextInt(HEIGHT), rand.nextInt(WIDTH), rand.nextInt(HEIGHT));
//    }
//    /**
//     * draw a line to a surface.
//     * @param sur the surface which the line will be drawn on to
//     * @param line a Line object
//     */
//    public void drawLineToSurface(DrawSurface sur, Line line) {
//        sur.drawLine((int) line.start().getX(), (int) line.start().getY(), (int) line.end().getX(),
//                (int) line.end().getY());
//    }
//    /**
//     * draw the locations of the intersections and the middle points of the lines.
//     * @param sur the surface which the line will be drawn on to
//     * @param lines Line objects array
//     */
//    public void drawCircles(DrawSurface sur, Line[] lines) {
//        for (int i = 0; i < NUMOFLINES - 1; i++) {
//            Point mid = lines[i].middle();
//            sur.setColor(Color.BLUE);
//            sur.fillCircle((int) mid.getX(), (int) mid.getY(), RADIUS);
//            for (int j = i + 1; j < NUMOFLINES; j++) {
//                Point intersection = lines[i].intersectionWith(lines[j]);
//                if (intersection == null) {
//                    continue;
//                }
//                sur.setColor(Color.RED);
//                sur.fillCircle((int) intersection.getX(), (int) intersection.getY(), RADIUS);
//            }
//        }
//    }
//    /**
//     * create random lines than draw them to a surface and draw the intersection points and middle points of the lines.
//     */
//    public void drawRandomLines() {
//        GUI gui = new GUI("Abstract Art Drawing", WIDTH, HEIGHT);
//        DrawSurface sur = gui.getDrawSurface();
//        Line[] lines = new Line[NUMOFLINES];
//        for (int i = 0; i < NUMOFLINES; i++) {
//            lines[i] = createRandomLine();
//            drawLineToSurface(sur, lines[i]);
//        }
//        drawCircles(sur, lines);
//        gui.show(sur);
//    }
//    /**
//     * the main method create a new AbstractArtDrawing object and calling the drawRandomLines method.
//     * @param args String array provided by the cmd
//     */
//    public static void main(String[] args) {
//        AbstractArtDrawing gui = new AbstractArtDrawing();
//        gui.drawRandomLines();
//    }
//}
