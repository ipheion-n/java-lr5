package model;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;


public final class ShapeComparators {
    private ShapeComparators() {
    }

   
    public static final Comparator<Shape> BY_AREA_ASC =
            Comparator.comparingDouble(Shape::calcArea);

 
    public static final Comparator<Shape> BY_COLOR =
            Comparator.comparing(Shape::getShapeColor, Collator.getInstance(Locale.forLanguageTag("uk-UA")));
}
