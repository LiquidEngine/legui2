package com.spinyowl.spinygui.core.style.stylesheet.property;

import static com.spinyowl.spinygui.core.style.stylesheet.Properties.COLOR;
import com.spinyowl.spinygui.core.style.stylesheet.extractor.ValueExtractor;
import com.spinyowl.spinygui.core.style.stylesheet.extractor.ValueExtractors;
import com.spinyowl.spinygui.core.node.style.NodeStyle;
import com.spinyowl.spinygui.core.style.stylesheet.Property;
import com.spinyowl.spinygui.core.node.style.types.Color;

public class ColorProperty extends Property<Color> {

  private static final ValueExtractor<Color> extractor = ValueExtractors.of(Color.class);

  public ColorProperty() {
    super(COLOR, "black", INHERITED, ANIMATABLE,
        NodeStyle::color, NodeStyle::color,
        extractor::extract, extractor::isValid);
  }
}
