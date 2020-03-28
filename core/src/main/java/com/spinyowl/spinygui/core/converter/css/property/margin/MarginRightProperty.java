package com.spinyowl.spinygui.core.converter.css.property.margin;

import static com.spinyowl.spinygui.core.converter.css.Properties.MARGIN_RIGHT;

import com.spinyowl.spinygui.core.converter.css.Property;
import com.spinyowl.spinygui.core.converter.css.extractor.ValueExtractor;
import com.spinyowl.spinygui.core.converter.css.extractor.ValueExtractors;
import com.spinyowl.spinygui.core.style.types.length.Unit;

public class MarginRightProperty extends Property<Unit> {

    public static final ValueExtractor<Unit> extractor = ValueExtractors.of(Unit.class);

    public MarginRightProperty() {
        super(MARGIN_RIGHT, "0", !INHERITED, ANIMATABLE,
            (s, v) -> s.getMargin().setRight(v), s -> s.getMargin().getRight(),
            extractor::extract, extractor::isValid);
    }
}
