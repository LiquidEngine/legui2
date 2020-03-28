package com.spinyowl.spinygui.core.converter.css.property.padding;

import static com.spinyowl.spinygui.core.converter.css.Properties.PADDING_TOP;

import com.spinyowl.spinygui.core.converter.css.Property;
import com.spinyowl.spinygui.core.converter.css.extractor.ValueExtractor;
import com.spinyowl.spinygui.core.converter.css.extractor.ValueExtractors;
import com.spinyowl.spinygui.core.style.types.length.Length;

public class PaddingTopProperty extends Property<Length> {

    public static final ValueExtractor<Length> extractor = ValueExtractors.of(Length.class);

    public PaddingTopProperty() {
        super(PADDING_TOP, "0", !INHERITED, ANIMATABLE,
            (s, v) -> s.getPadding().setTop(v), s -> s.getPadding().getTop(),
            extractor::extract, extractor::isValid);
    }
}