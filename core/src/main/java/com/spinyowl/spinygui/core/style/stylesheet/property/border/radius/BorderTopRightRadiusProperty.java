package com.spinyowl.spinygui.core.style.stylesheet.property.border.radius;

import static com.spinyowl.spinygui.core.style.stylesheet.Properties.BORDER_TOP_RIGHT_RADIUS;
import com.spinyowl.spinygui.core.style.stylesheet.extractor.ValueExtractor;
import com.spinyowl.spinygui.core.style.stylesheet.extractor.ValueExtractors;
import com.spinyowl.spinygui.core.style.stylesheet.Property;
import com.spinyowl.spinygui.core.node.style.types.length.Length;

public class BorderTopRightRadiusProperty extends Property<Length> {

  private static final ValueExtractor<Length> extractor = ValueExtractors.of(Length.class);

  public BorderTopRightRadiusProperty() {
    super(BORDER_TOP_RIGHT_RADIUS, "0", !INHERITED, ANIMATABLE,
        (s, l) -> s.borderRadius().topRight(l),
        s -> s.borderRadius().topRight(),
        extractor::extract, extractor::isValid);
  }
}
