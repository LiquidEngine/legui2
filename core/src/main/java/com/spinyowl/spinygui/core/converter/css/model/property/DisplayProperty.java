package com.spinyowl.spinygui.core.converter.css.model.property;

import static com.spinyowl.spinygui.core.converter.css.Properties.DISPLAY;
import com.spinyowl.spinygui.core.converter.css.model.Property;
import com.spinyowl.spinygui.core.style.NodeStyle;
import com.spinyowl.spinygui.core.style.types.Display;

public class DisplayProperty extends Property<Display> {

  public DisplayProperty() {
    super(DISPLAY, Display.FLEX.name(), INHERITED, ANIMATABLE,
        NodeStyle::display, NodeStyle::display, Display::find, Display::contains);
  }

}