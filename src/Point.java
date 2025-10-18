import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }

    @Override
    public int compareTo(Point that) {
        int compareY = Integer.compare(y, that.y);

        if (compareY == 0) {
            return Integer.compare(x, that.x);
        }

        return compareY;
    }

    public double slopeTo(Point that) {
        if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
        if (x == that.x) return Double.POSITIVE_INFINITY;
        if (y == that.y) return 0;

        return 1.0 * (that.y - y) / (that.x - x);
    }

    public Comparator<Point> slopeOrder() {
        return new BySlope(this);
    }

    private static class BySlope implements Comparator<Point> {
        Point origin;

        BySlope(Point origin) {
            this.origin = origin;
        }

        @Override
        public int compare(Point u, Point v) {
            return Double.compare(origin.slopeTo(u), origin.slopeTo(v));
        }
    }
}