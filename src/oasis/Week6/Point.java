package oasis.Week6;

import java.util.Objects;

public class Point {

    private double pointX;
    private double pointY;

    public Point(double pointX, double pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    /**
     * distance().
     */
    public double distance(Point newPoint) {
        double dx = getPointX() - newPoint.getPointX();
        double dy = getPointY() - newPoint.getPointY();

        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Point other) {
            return Math.abs(getPointX() - other.getPointX()) <= 0.001
                   && Math.abs(getPointY() - other.getPointY()) <= 0.001;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointX, pointY);
    }

    @Override
    public String toString() {
        return String.format("(%.1f,%.1f)", getPointX(), getPointY());
    }
}