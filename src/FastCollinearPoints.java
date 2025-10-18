import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private static final int MINIMUM_COLLINEAR_POINTS = 4;
    private final List<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
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

        if (N < MINIMUM_COLLINEAR_POINTS) {
            return;
        }

        for (int i = 0; i < N; i++) {
            Point origin = points[i];

            Point[] others = new Point[N - 1];
            int idx = 0;

            for (int j = 0; j < N; j++) {
                if (j != i) {
                    others[idx++] = points[j];
                }
            }

            Arrays.sort(others, origin.slopeOrder());

            int start = 0;

            while (start < others.length) {
                double slope = origin.slopeTo(others[start]);
                int end = start + 1;

                while (end < others.length && origin.slopeTo(others[end]) == slope) {
                    end++;
                }

                int collinearCount = end - start + 1;
                if (collinearCount >= MINIMUM_COLLINEAR_POINTS) {
                    boolean isMinPoint = true;

                    for (int k = start; k < end; k++) {
                        if (origin.compareTo(others[k]) > 0) {
                            isMinPoint = false;
                            break;
                        }
                    }

                    if (isMinPoint) {
                        Point maxPoint = others[end - 1];

                        for (int k = start; k < end - 1; k++) {
                            if (others[k].compareTo(maxPoint) > 0) {
                                maxPoint = others[k];
                            }
                        }

                        segments.add(new LineSegment(origin, maxPoint));
                    }
                }

                start = end;
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
}