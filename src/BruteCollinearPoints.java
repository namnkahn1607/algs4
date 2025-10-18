import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private final List<LineSegment> segments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException(
                    "argument to BruteCollinearPoints is null"
            );
        }

        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException(
                        "argument element to BruteCollinearPoints is null"
                );
            }
        }

        Point[] clone = points.clone();
        Arrays.sort(clone);

        for (int i = 1; i < clone.length; ++i) {
            if (clone[i].compareTo(clone[i - 1]) == 0) {
                throw new IllegalArgumentException(
                        "argument to BruteCollinearPoints contains repeated point"
                );
            }
        }

        discoverSegments(clone);
    }

    private void discoverSegments(Point[] points) {
        final int N = points.length;

        for (int a = 0; a < N - 3; ++a) {
            Point p = points[a];

            for (int b = a + 1; b < N - 2; ++b) {
                Point q = points[b];

                for (int c = b + 1; c < N - 1; ++c) {
                    Point r = points[c];

                    for (int d = c + 1; d < N; ++d) {
                        Point s = points[d];

                        if (validSegment(p, q, r, s)) {
                            segments.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }
    }

    private boolean validSegment(Point p, Point q, Point r, Point s) {
        double pToq = p.slopeTo(q);
        double pTor = p.slopeTo(r);
        double pTos = p.slopeTo(s);

        return Double.compare(pToq, pTor) == 0 && Double.compare(pTor, pTos) == 0;
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
}