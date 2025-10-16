package oasis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Layer {
    private List<Shape> shapes = new ArrayList<>();

    /**
     * addShape().
     */
    public void addShape(Shape shape) {
        if (shape == null) {
            return;
        }

        shapes.add(shape);
    }

    public void removeCircles() {
        shapes.removeIf(shape -> shape instanceof Circle);
    }

    /**
     * getInfo().
     */
    public String getInfo() {
        StringBuilder res = new StringBuilder("oasis.Layer of crazy shapes:\n");

        for (Shape shape : shapes) {
            res.append(shape.toString());
            res.append('\n');
        }

        return res.toString();
    }

    /**
     * removeDuplicates().
     */
    public void removeDuplicates() {
        ArrayList<Shape> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for (Shape shape : shapes) {
            if (!set.contains(shape.hashCode())) {
                set.add(shape.hashCode());
                res.add(shape);
            }
        }

        shapes = res;
    }
}