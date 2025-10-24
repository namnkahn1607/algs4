package oasis.Week7;

import java.util.List;

public class ShapeUtil {
    /**
     * printInfo().
     */
    public String printInfo(List<GeometricObject> shapes) {
        StringBuilder res = new StringBuilder();
        res.append("oasis.Week7.Circle:\n");

        for (GeometricObject shape : shapes) {
            if (shape instanceof Circle) {
                res.append(shape.getInfo());
                res.append("\n");
            }
        }

        res.append("oasis.Week7.Triangle:\n");

        for (GeometricObject shape : shapes) {
            if (shape instanceof Triangle) {
                res.append(shape.getInfo());
                res.append("\n");
            }
        }

        return res.toString();
    }
}
