package oasis.Week7;

public class Triangle implements GeometricObject {
    private final Point p1;
    private final Point p2;
    private final Point p3;

    /**
     * oasis.Week7.Triangle.
     */
    public Triangle(Point p1, Point p2, Point p3) throws RuntimeException {
        if (p1 == p2 || p2 == p3 || p3 == p1) {
            throw new RuntimeException("There's duplicate oasis.Week7.Point!");
        }

        double dist12 = p1.distance(p2);
        double dist23 = p2.distance(p3);
        double dist31 = p3.distance(p1);

        if (dist12 + dist23 == dist31 || dist23 + dist31 == dist12 || dist12 + dist31 == dist23) {
            throw new RuntimeException("Collinear Points!");
        }

        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Point getP3() {
        return p3;
    }

    @Override
    public double getArea() {
        return Math.abs(p1.getPointX() * (p2.getPointY() - p3.getPointY())
                + p2.getPointX() * (p3.getPointY() - p1.getPointY())
                + p3.getPointX() * (p1.getPointY() - p2.getPointY())
        ) / 2;
    }

    @Override
    public double getPerimeter() {
        return p1.distance(p2) + p2.distance(p3) + p3.distance(p1);
    }

    @Override
    public String getInfo() {
        return String.format(
                "oasis.Week7.Triangle[(%.2f,%.2f),(%.2f,%.2f),(%.2f,%.2f)]",
                p1.getPointX(), p1.getPointY(),
                p2.getPointX(), p2.getPointY(),
                p3.getPointX(), p3.getPointY()
        );
    }
}