package com.spinyowl.spinygui.core.style.stylesheet.property.position;

import static com.spinyowl.spinygui.core.style.stylesheet.Properties.TOP;
import com.spinyowl.spinygui.core.style.stylesheet.extractor.ValueExtractor;
import com.spinyowl.spinygui.core.style.stylesheet.extractor.ValueExtractors;
import com.spinyowl.spinygui.core.node.style.NodeStyle;
import com.spinyowl.spinygui.core.style.stylesheet.Property;
import com.spinyowl.spinygui.core.node.style.types.length.Unit;

public class TopProperty extends Property<Unit> {

  private static final ValueExtractor<Unit> extractor = ValueExtractors.of(Unit.class);

  public TopProperty() {
    super(TOP, "auto", !INHERITED, ANIMATABLE,
        NodeStyle::top, NodeStyle::top,
        extractor::extract, extractor::isValid);
  }
}
