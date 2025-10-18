package oasis.Week6;

import java.util.Objects;

public class Circle extends Shape {

    protected Point center;
    protected double radius;

    public Circle() {}

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    /**
     * constructor.
     */
    public Circle(Point center, double radius, String color, boolean filled) {
        super(color, filled);
        this.center = new Point(center.getPointX(), center.getPointY());
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
        return getRadius() * getRadius() * Math.PI;
    }

    @Override
    public double getPerimeter() {
        return 2 * getRadius() * Math.PI;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Circle other) {
            return center.equals(other.getCenter())
                    && Math.abs(getRadius() - other.getRadius()) <= 0.001;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }

    @Override
    public String toString() {
        return String.format(
                "Circle[center=%s,radius=%.1f,color=%s,filled=%b]",
                getCenter().toString(), getRadius(), getColor(), isFilled()
        );
    }
}
