package oasis;

public class _Circle {

    private double radius;
    private String color;
    protected static final double PI = Math.PI;

    public _Circle() {
        super();
    }

    public _Circle(double radius) {
        this.radius = radius;
    }

    public _Circle(double radius, String color) {
        this.radius = radius;
        this.color = color;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getArea() {
        return radius * radius * PI;
    }

    @Override
    public String toString() {
        return String.format("oasis.Circle[radius=%f,color=%s]", getRadius(), getColor());
    }
}