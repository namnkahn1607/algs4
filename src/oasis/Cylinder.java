package oasis;

public class Cylinder extends _Circle {

    private double height;

    public Cylinder() {
        super();
    }

    public Cylinder(double height) {
        this.height = height;
    }

    public Cylinder(double radius, double height) {
        super(radius);
        this.height = height;
    }

    public Cylinder(double radius, double height, String color) {
        super(radius, color);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getVolume() {
        return super.getArea() * getHeight();
    }

    @Override
    public String toString() {
        return String.format("oasis.Cylinder[%s,height=%f]", super.toString(), getHeight());
    }

    @Override
    public double getArea() {
        return 2 * super.getArea() + 2 * getRadius() * getHeight();
    }
}