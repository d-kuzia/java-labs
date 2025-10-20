package lab3;

import java.util.Comparator;

public class ColorComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return s1.shapeColor.compareTo(s2.shapeColor);
    }
}
