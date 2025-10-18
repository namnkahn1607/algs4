package module6;

public class LineSegment {
    private final Point p;
    private final Point q;

    public LineSegment(Point p, Point q) {
        if (p == null || q == null) {
            throw new IllegalArgumentException(
                    "argument to module6.LineSegment constructor is null"
            );
        }

        if (p.equals(q)) {
            throw new IllegalArgumentException(
                    "both arguments to module6.LineSegment constructor are the same point: " + p
            );
        }

        this.p = p;
        this.q = q;
    }

    public void draw() {
        p.drawTo(q);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", p, q);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException(
                "hashCode() is not supported"
        );
    }
}