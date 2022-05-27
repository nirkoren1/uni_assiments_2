package animations;
// Nir Koren 316443902
import geometry_primitives.Line;
import geometry_primitives.Point;
import geometry_primitives.Rectangle;
import infos.Velocity;
import sprites.Block;
import sprites.FillCircleSprite;
import sprites.LineSprite;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Level 4.
 */
public class Level4 extends Level implements LevelInformation {
    // creating a level
    private static List<Block> blocksList = Level4.createBlocks();
    private static List<Sprite> spritesList = Level4.createBackGround();
    private static List<Velocity> velocities = Level4.createVelocities();
    /**
     * constructor.
     */
    public Level4() {
        super(3,
                velocities,
                5,
                100,
                "Final Four",
                spritesList,
                blocksList);
    }
    /**
     * create blocks.
     * @return the list
     */
    public static List<Block> createBlocks() {
        List<Block> blocks = new ArrayList<Block>();
        int height = 22;
        int width = 52;
        // creating 7 rows of blocks with different colors and stick together
        for (int i = 0; i < 7; i++) {
            for (int j = 14; j > 0; j--) {
                Point center = new Point(j * width - 16, i * height + 100);
                Rectangle rect = new Rectangle(center, width, height);
                if (i == 0) {
                    Block block = new Block(rect, Color.GRAY);
                    blocks.add(block);
                } else if (i == 6) {
                    Block block = new Block(rect, Color.GREEN);
                    blocks.add(block);
                } else {
                    Block block = new Block(rect, Color.getHSBColor(i * 0.2f, 1, 1));
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }
    private static List<Sprite> createBackGround() {
        List<Sprite> sprites = new ArrayList<Sprite>();
        sprites.add(new Block(new Rectangle(new Point(0, 0), 800, 600), Color.CYAN));
        // adding 7 white lines coming down from the cloud
        sprites.add(new LineSprite(new Line(new Point(100, 400), new Point(80, 570)), Color.WHITE));
        sprites.add(new LineSprite(new Line(new Point(120, 400), new Point(100, 570)), Color.WHITE));
        sprites.add(new LineSprite(new Line(new Point(110, 400), new Point(90, 570)), Color.WHITE));
        sprites.add(new LineSprite(new Line(new Point(130, 400), new Point(110, 570)), Color.WHITE));
        sprites.add(new LineSprite(new Line(new Point(140, 400), new Point(120, 570)), Color.WHITE));
        sprites.add(new LineSprite(new Line(new Point(150, 400), new Point(130, 570)), Color.WHITE));
        sprites.add(new LineSprite(new Line(new Point(160, 400), new Point(140, 570)), Color.WHITE));
        // adding a cloud to the background from 5 circles
        sprites.add(new FillCircleSprite(new Point(100, 400), 25, new Color(200, 200, 200)));
        sprites.add(new FillCircleSprite(new Point(120, 380), 28, new Color(160, 160, 160)));
        sprites.add(new FillCircleSprite(new Point(110, 390), 30, new Color(140, 140, 140)));
        sprites.add(new FillCircleSprite(new Point(130, 400), 31, new Color(120, 120, 120)));
        sprites.add(new FillCircleSprite(new Point(150, 400), 25, new Color(100, 100, 100)));
        int increase = 500;
        // adding 7 white lines coming down from the cloud
        sprites.add(new LineSprite(new Line(new Point(100 + increase, 400), new Point(80 + increase, 570)),
                Color.WHITE));
        sprites.add(new LineSprite(new Line(new Point(120 + increase, 400), new Point(100 + increase, 570)),
                Color.WHITE));
        sprites.add(new LineSprite(new Line(new Point(110 + increase, 400), new Point(90 + increase, 570)),
                Color.WHITE));
        sprites.add(new LineSprite(new Line(new Point(130 + increase, 400), new Point(110 + increase, 570)),
                Color.WHITE));
        sprites.add(new LineSprite(new Line(new Point(140 + increase, 400), new Point(120 + increase, 570)),
                Color.WHITE));
        sprites.add(new LineSprite(new Line(new Point(150 + increase, 400), new Point(130 + increase, 570)),
                Color.WHITE));
        sprites.add(new LineSprite(new Line(new Point(160 + increase, 400), new Point(140 + increase, 570)),
                Color.WHITE));
        // adding a cloud to the background from 5 circles
        sprites.add(new FillCircleSprite(new Point(100 + increase, 400), 25, new Color(200, 200, 200)));
        sprites.add(new FillCircleSprite(new Point(120 + increase, 380), 28, new Color(160, 160, 160)));
        sprites.add(new FillCircleSprite(new Point(110 + increase, 390), 30, new Color(140, 140, 140)));
        sprites.add(new FillCircleSprite(new Point(130 + increase, 400), 31, new Color(120, 120, 120)));
        sprites.add(new FillCircleSprite(new Point(150 + increase, 400), 25, new Color(100, 100, 100)));
        return sprites;
    }
    private static List<Velocity> createVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        // create a list of velocities
        for (int i = 0; i < 3; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(i * 30 - 15, 3));
        }
        return velocities;
    }
}


