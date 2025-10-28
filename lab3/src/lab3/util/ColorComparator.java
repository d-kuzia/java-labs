package lab3.util;

import java.util.Comparator;
import lab3.model.Shape;

public class ColorComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return s1.getColor().compareTo(s2.getColor());
    }
}
