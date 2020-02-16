package com.spinyowl.spinygui.core.converter.css.property.flex;

import static com.spinyowl.spinygui.core.converter.css.Properties.FLEX_BASIS;

import com.spinyowl.spinygui.core.converter.css.Property;
import com.spinyowl.spinygui.core.converter.css.ValueExtractor;
import com.spinyowl.spinygui.core.converter.css.ValueExtractors;
import com.spinyowl.spinygui.core.style.types.length.Unit;
import java.util.Set;

public class FlexBasisProperty extends Property<Unit> {

    public static final String AUTO = "auto";
    public static final ValueExtractor<Unit> extractor = ValueExtractors.of(Unit.class);

    public FlexBasisProperty() {
        super(FLEX_BASIS, AUTO, !INHERITED, !ANIMATABLE,
            (s, v) -> s.getFlex().setFlexBasis(v), s -> s.getFlex().getFlexBasis(),
            extractor::extract, extractor::isValid);
    }

    @Override
    public Set<String> allowedValues() {
        return Set.of(INITIAL, INHERIT, AUTO);
    }

}
