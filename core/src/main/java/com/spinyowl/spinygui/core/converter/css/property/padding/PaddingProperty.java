package com.spinyowl.spinygui.core.converter.css.property.padding;

import com.spinyowl.spinygui.core.converter.css.Properties;
import com.spinyowl.spinygui.core.converter.css.Property;
import com.spinyowl.spinygui.core.converter.css.extractor.ValueExtractor;
import com.spinyowl.spinygui.core.converter.css.extractor.ValueExtractors;
import com.spinyowl.spinygui.core.converter.css.util.StyleUtils;
import com.spinyowl.spinygui.core.style.NodeStyle;
import com.spinyowl.spinygui.core.style.types.Padding;
import com.spinyowl.spinygui.core.style.types.length.Length;

public class PaddingProperty extends Property<Padding> {

    private static ValueExtractor<Length> lengthValueExtractor = ValueExtractors.of(Length.class);

    public PaddingProperty() {
        super(Properties.PADDING, "0", !INHERITED, ANIMATABLE,
            NodeStyle::setPadding, NodeStyle::getPadding,
            PaddingProperty::extract, PaddingProperty::test);
    }

    public static Padding extract(String value) {
        if (value == null) {
            return null;
        }
        String[] v = value.split("\\s+");
        //@formatter:off
        switch (v.length) {
            case 0:          return null;
            case 1:          return new Padding(x(v[0]));
            case 2:          return new Padding(x(v[0]), x(v[1]));
            case 3:          return new Padding(x(v[0]), x(v[1]), x(v[2]));
            case 4: default: return new Padding(x(v[0]), x(v[1]), x(v[2]), x(v[3]));
        }
        //@formatter:on
    }

    private static Length x(String value1) {
        return lengthValueExtractor.extract(value1);
    }

    public static boolean test(String value) {
        return StyleUtils.testOneFourValue(value, lengthValueExtractor::isValid);
    }
}
