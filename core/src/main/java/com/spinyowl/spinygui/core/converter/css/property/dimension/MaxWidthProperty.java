package com.spinyowl.spinygui.core.converter.css.property.dimension;

import com.spinyowl.spinygui.core.converter.css.Properties;
import com.spinyowl.spinygui.core.converter.css.Property;
import com.spinyowl.spinygui.core.converter.css.ValueExtractor;
import com.spinyowl.spinygui.core.converter.css.ValueExtractors;
import com.spinyowl.spinygui.core.style.NodeStyle;
import com.spinyowl.spinygui.core.style.types.length.Length;

public class MaxWidthProperty extends Property<Length> {

    public static final ValueExtractor<Length> extractor = ValueExtractors.of(Length.class);

    public MaxWidthProperty() {
        super(Properties.MAX_WIDTH, "none", !INHERITED, ANIMATABLE,
            NodeStyle::setMaxWidth, NodeStyle::getMaxWidth,
            value -> "none".equalsIgnoreCase(value) ?
                Length.pixel(Integer.MAX_VALUE)
                : extractor.extract(value),
            extractor::isValid);
    }
}