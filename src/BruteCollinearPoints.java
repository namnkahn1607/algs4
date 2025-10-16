import java.util.ArrayList;

public class BruteCollinearPoints {

    private ArrayList<LineSegment> lines;
    private int segmentCount;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("point array is null");
        }

        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("a point is null");
            }
        }

        lines = new ArrayList<>();
        segmentCount = 0;

        for (int a = 0; a < points.length - 3; ++a) {
            for (int b = a + 1; b < points.length - 2; ++b) {
                for (int c = b + 1; c < points.length - 1; ++c) {
                    for (int d = c + 1; d < points.length; ++d) {
                        Point P = points[a];
                        Point Q = points[b];
                        Point R = points[c];
                        Point S = points[d];

                        if (P.equals(Q) || P.equals(R) || P.equals(S) ||
                            Q.equals(R) || Q.equals(S) || R.equals(S)) {
                            throw new IllegalArgumentException("array contain duplicate points");
                        }

                        if (isCollinear(P, Q, R, S)) {
                            ++segmentCount;
                            lines.add(new LineSegment(P, S));
                        }
                    }
                }
            }
        }
    }

    private boolean isCollinear(Point P, Point Q, Point R, Point S) {
        double PtoQ = P.slopeTo(Q);
        double PtoR = P.slopeTo(R);
        double PtoS = P.slopeTo(S);

        return PtoQ == PtoR && PtoR == PtoS;
    }

    public int numberOfSegments() {
        return segmentCount;
    }

    public LineSegment[] segments() {
        return lines.toArray(new LineSegment[0]);
    }
}