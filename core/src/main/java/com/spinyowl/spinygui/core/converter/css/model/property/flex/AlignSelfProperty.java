package com.spinyowl.spinygui.core.converter.css.model.property.flex;

import static com.spinyowl.spinygui.core.converter.css.Properties.ALIGN_SELF;
import com.spinyowl.spinygui.core.converter.css.model.Property;
import com.spinyowl.spinygui.core.style.types.flex.AlignSelf;

public class AlignSelfProperty extends Property<AlignSelf> {

  public AlignSelfProperty() {
    super(ALIGN_SELF, "auto", !INHERITED, !ANIMATABLE,
        (s, v) -> s.flex().alignSelf(v), s -> s.flex().alignSelf(),
        AlignSelf::find, AlignSelf::contains);
  }

}