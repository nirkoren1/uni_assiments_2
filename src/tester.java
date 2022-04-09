import java.util.List;

public class tester {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(new Point(10, 20), 25, 30);
        Line l = new Line(1, 20, 80, 70);
        List<Point> points = (rectangle.intersectionPoints(l));
        System.out.println(points);
        for (Point point : points) {
            if (point != null) {
                System.out.println(point.getX() + " " + point.getY());
            }
        }
        Point p = l.closestIntersectionToStartOfLine(rectangle);
        System.out.println(p.getX() + " " + p.getY());
    }
}
