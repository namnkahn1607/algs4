package oasis.Week7;

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
     * distance.
     */
    public double distance(Point newPoint) {
        double dx = getPointX() - newPoint.getPointX();
        double dy = getPointY() - newPoint.getPointY();

        return Math.sqrt(dx * dx + dy * dy);
    }
}