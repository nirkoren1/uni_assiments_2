// Nir Koren 316443902

public class Ball {
    private Integer x = null;
    private Integer y = null;
    private Integer radius = null;
    public Ball(Point center, int r, java.awt.Color color) {

    }

    public int getX();
    public int getY();
    public int getSize();
    public java.awt.Color getColor();

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface);
}
