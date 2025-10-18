package oasis.Week6;

import java.util.Objects;

public class Rectangle extends Shape {

    protected Point topLeft;
    protected double width;
    protected double length;

    public Rectangle() {}

    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    /**
     * constructor.
     */
    public Rectangle(double width, double length, String color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }

    /**
     * constructor.
     */
    public Rectangle(Point topLeft, double width, double length, String color, boolean filled) {
        super(color, filled);
        this.topLeft = new Point(topLeft.getPointX(), topLeft.getPointY());
        this.width = width;
        this.length = length;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public double getArea() {
        return getWidth() * getLength();
    }

    @Override
    public double getPerimeter() {
        return 2 * (getWidth() + getLength());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Rectangle other) {
            return getTopLeft().equals(other.getTopLeft())
                   && Math.abs(getWidth() - other.getWidth()) <= 0.001
                   && Math.abs(getLength() - other.getLength()) <= 0.001;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, width, length);
    }

    @Override
    public String toString() {
        return String.format(
                "oasis.Week6.Rectangle[topLeft=%s,width=%.1f,length=%.1f,color=%s,filled=%b]",
                getTopLeft().toString(), getWidth(), getLength(), getColor(), isFilled()
        );
    }
}