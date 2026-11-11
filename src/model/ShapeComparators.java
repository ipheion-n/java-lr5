package model;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/** Компаратори для впорядкування фігур. */
public final class ShapeComparators {
    private ShapeComparators() {
    }

    /** За зростанням площі. */
    public static final Comparator<Shape> BY_AREA_ASC =
            Comparator.comparingDouble(Shape::calcArea);

    /** За кольором (алфавітний порядок з урахуванням української мови). */
    public static final Comparator<Shape> BY_COLOR =
            Comparator.comparing(Shape::getShapeColor, Collator.getInstance(Locale.forLanguageTag("uk-UA")));
}
