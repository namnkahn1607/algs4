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
        StdDraw.line(x, y, that.x, that.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public double slopeTo(Point that) {
        int dy = y - that.y;
        int dx = x - that.x;

        if (dx == 0 && dy == 0)
            return Double.NEGATIVE_INFINITY;

        if (dx == 0)
            return Double.POSITIVE_INFINITY;

        if (dy == 0)
            return +0.0;

        return (double)dy / dx;
    }

    @Override
    public int compareTo(Point that) {
        if (y != that.y) {
            return Integer.compare(y, that.y);
        }

        return Integer.compare(x, that.x);
    }

    public Comparator<Point> slopeOrder() {
        return new bySlope(this);
    }

    private static class bySlope implements Comparator<Point> {

        Point origin;

        public bySlope(Point p) {
            origin = p;
        }

        @Override
        public int compare(Point v, Point w) {
            return Double.compare(origin.slopeTo(v), origin.slopeTo(w));
        }
    }
}