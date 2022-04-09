import java.util.List;

public class tester {
    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(new Point(100, 20), 25, 30);
        Rectangle rectangle2 = new Rectangle(new Point(20, 30), 25, 30);
        Rectangle rectangle3 = new Rectangle(new Point(15, 100), 25, 30);
        Rectangle rectangle4 = new Rectangle(new Point(90, 37), 25, 30);
        Line l = new Line(10, 20, 80, 70);
        List<Point> points = (rectangle1.intersectionPoints(l));
        System.out.println(points);
        for (Point point : points) {
            if (point != null) {
                System.out.println(point.getX() + " " + point.getY());
            }
        }
//        Point p = l.closestIntersectionToStartOfLine(rectangle1);
//        System.out.println(p.getX() + " " + p.getY());
        Block block = new Block(rectangle1);
        Velocity velocity = new Velocity(5, 6);
//        velocity = block.hit(p, velocity);
//        System.out.println(velocity.getDx() + " " + velocity.getDy());
        GameEnvironment env = new GameEnvironment();
        env.addCollidable(block);
        CollisionInfo info = env.getClosestCollision(l);
        System.out.println(info);
        System.out.println(info.collisionPoint().getX() + " " + info.collisionPoint().getY() + " " + info.collisionObject().getCollisionRectangle().getUpperLeft().getX());
    }
}
