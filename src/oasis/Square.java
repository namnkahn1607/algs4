package oasis;

import java.util.Objects;

public class Square extends Rectangle {

    public Square() {}

    public Square(double side) {
        super(side, side);
    }

    public Square(double side, String color, boolean filled) {
        super(side, side, color, filled);
    }

    public Square(Point topLeft, double side, String color, boolean filled) {
        super(topLeft, side, side, color, filled);
    }

    public double getSide() {
        return width;
    }

    public void setSide(double side) {
        width = side;
        length = side;
    }

    @Override
    public void setWidth(double side) {
        width = side;
        length = side;
    }

    @Override
    public void setLength(double side) {
        width = side;
        length = side;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Square other) {
            return getTopLeft().equals(other.getTopLeft())
                   && Math.abs(getSide() - other.getSide()) <= 0.001;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, getSide(), color);
    }

    @Override
    public String toString() {
        return String.format(
                "oasis.Square[topLeft=%s,side=%.1f,color=%s,filled=%b]",
                getTopLeft().toString(), getSide(), getColor(), isFilled()
        );
    }
}