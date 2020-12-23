package com.spinyowl.spinygui.core.converter.css.model.property.position;

import static com.spinyowl.spinygui.core.converter.css.Properties.LEFT;
import com.spinyowl.spinygui.core.converter.css.model.Property;
import com.spinyowl.spinygui.core.converter.css.extractor.ValueExtractor;
import com.spinyowl.spinygui.core.converter.css.extractor.ValueExtractors;
import com.spinyowl.spinygui.core.style.NodeStyle;
import com.spinyowl.spinygui.core.style.types.length.Unit;

public class LeftProperty extends Property<Unit> {

  private static final ValueExtractor<Unit> extractor = ValueExtractors.of(Unit.class);

  public LeftProperty() {
    super(LEFT, "auto", !INHERITED, ANIMATABLE,
        NodeStyle::left, NodeStyle::left,
        extractor::extract, extractor::isValid);
  }
}