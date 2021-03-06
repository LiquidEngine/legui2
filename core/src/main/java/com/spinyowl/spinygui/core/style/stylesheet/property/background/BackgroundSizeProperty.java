package com.spinyowl.spinygui.core.style.stylesheet.property.background;

import static com.spinyowl.spinygui.core.style.stylesheet.Properties.BACKGROUND_SIZE;
import static com.spinyowl.spinygui.core.style.stylesheet.util.StyleUtils.testMultipleValues;
import com.spinyowl.spinygui.core.style.stylesheet.extractor.ValueExtractor;
import com.spinyowl.spinygui.core.style.stylesheet.extractor.ValueExtractors;
import com.spinyowl.spinygui.core.style.stylesheet.Property;
import com.spinyowl.spinygui.core.node.style.types.background.BackgroundSize;
import com.spinyowl.spinygui.core.node.style.types.length.Unit;
import java.util.List;

public class BackgroundSizeProperty extends Property<BackgroundSize> {

  private static final ValueExtractor<Unit> extractor = ValueExtractors.of(Unit.class);
  private static final String COVER = "cover";
  private static final String CONTAIN = "contain";
  private static final List<String> values = List.of(COVER, CONTAIN);

  public BackgroundSizeProperty() {
    super(BACKGROUND_SIZE, "auto", !INHERITED, ANIMATABLE,
        (s, c) -> s.background().size(c), nodeStyle -> nodeStyle.background().size(),
        BackgroundSizeProperty::extract, BackgroundSizeProperty::test);
  }

  private static BackgroundSize extract(String value) {
    switch (value) {
      case COVER:
        return BackgroundSize.cover();
      case CONTAIN:
        return BackgroundSize.contain();
      default:
        String[] v = value.split("\\s+");
        if (v.length == 1) {
          return BackgroundSize.size(extractor.extract(v[0]));
        } else {
          return BackgroundSize.size(extractor.extract(v[0]), extractor.extract(v[1]));
        }
    }
  }

  private static boolean test(String value) {
    return values.contains(value) || testMultipleValues(value, "\\s+", 1, 2, extractor::isValid);
  }
}
