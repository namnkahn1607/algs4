package oasis.Week7;

public class Circle implements GeometricObject {
    public static final double PI = 3.14;
    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return PI * getRadius() * getRadius();
    }

    @Override
    public double getPerimeter() {
        return 2 * getRadius() * PI;
    }

    @Override
    public String getInfo() {
        return String.format(
                "oasis.Week7.Circle[(%.2f,%.2f),r=%.2f]",
                getCenter().getPointX(), getCenter().getPointY(), getRadius()
        );
    }
}
