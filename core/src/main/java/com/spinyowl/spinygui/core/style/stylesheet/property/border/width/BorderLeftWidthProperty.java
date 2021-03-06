package com.spinyowl.spinygui.core.style.stylesheet.property.border.width;

import static com.spinyowl.spinygui.core.style.stylesheet.Properties.BORDER_LEFT_WIDTH;
import static com.spinyowl.spinygui.core.style.stylesheet.property.border.width.BorderWidthProperty.MEDIUM;
import com.spinyowl.spinygui.core.style.stylesheet.Property;
import com.spinyowl.spinygui.core.node.style.types.length.Length;

public class BorderLeftWidthProperty extends Property<Length> {

  public BorderLeftWidthProperty() {
    super(BORDER_LEFT_WIDTH, MEDIUM, !INHERITED, ANIMATABLE,
        (s, v) -> s.border().left().width(v),
        s -> s.border().left().width(),
        BorderWidthProperty::extractOne, BorderWidthProperty::testOne);
  }
}
