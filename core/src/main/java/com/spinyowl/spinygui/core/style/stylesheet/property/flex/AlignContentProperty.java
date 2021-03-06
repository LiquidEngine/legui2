package com.spinyowl.spinygui.core.style.stylesheet.property.flex;

import static com.spinyowl.spinygui.core.style.stylesheet.Properties.ALIGN_CONTENT;
import com.spinyowl.spinygui.core.style.stylesheet.Property;
import com.spinyowl.spinygui.core.node.style.types.flex.AlignContent;

public class AlignContentProperty extends Property<AlignContent> {

  public AlignContentProperty() {
    super(ALIGN_CONTENT, "stretch", !INHERITED, !ANIMATABLE,
        (s, v) -> s.flex().alignContent(v), s -> s.flex().alignContent(),
        AlignContent::find, AlignContent::contains);
  }

}
