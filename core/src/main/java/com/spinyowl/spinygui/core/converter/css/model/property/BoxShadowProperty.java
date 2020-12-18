package com.spinyowl.spinygui.core.converter.css.model.property;

import static com.spinyowl.spinygui.core.converter.css.Properties.BOX_SHADOW;
import com.spinyowl.spinygui.core.converter.css.extractor.ValueExtractor;
import com.spinyowl.spinygui.core.converter.css.extractor.ValueExtractors;
import com.spinyowl.spinygui.core.converter.css.model.Property;
import com.spinyowl.spinygui.core.style.NodeStyle;
import com.spinyowl.spinygui.core.style.types.BoxShadow;
import com.spinyowl.spinygui.core.style.types.Color;
import com.spinyowl.spinygui.core.style.types.length.Length;

public class BoxShadowProperty extends Property<BoxShadow> {

  private static final ValueExtractor<Length> lengthValueExtractor =
      ValueExtractors.of(Length.class);
  private static final ValueExtractor<Color> colorValueExtractor =
      ValueExtractors.of(Color.class);

  private static final String INSET = "INSET";
  private static final String NONE = "none";

  public BoxShadowProperty() {
    super(BOX_SHADOW, NONE, !INHERITED, ANIMATABLE,
        NodeStyle::boxShadow, NodeStyle::boxShadow,
        BoxShadowProperty::extract, BoxShadowProperty::validate);
  }

  private static boolean validate(String s) {
    if(s.equals("none")) {
      return true;
    }

    String[] args = s.split("\\s+");
    if (args.length < 2 || args.length > 6) {
      return false;
    }

    int lengthArgs = 0;
    int insetArgs = 0;
    int colorArgs = 0;

    for (String arg : args) {
      boolean isInset = INSET.equals(arg);
      boolean isLength = !isInset && lengthValueExtractor.isValid(arg);
      boolean isColor = !isInset && !isLength && colorValueExtractor.isValid(arg);
      if (!isInset && !isColor && !isLength) {
        return false;
      }
      //@formatter:off
      if(isInset) insetArgs ++;
      if(isColor) colorArgs ++;
      if(isLength) lengthArgs ++;
      //@formatter:on
    }

    return lengthArgs >= 2 && lengthArgs <= 4 && insetArgs <= 1 && colorArgs <= 1;
  }

  private static BoxShadow extract(String s) {
    if(NONE.equals(s)) return new BoxShadow();

    String[] args = s.split("\\s+");

    BoxShadow shadow = new BoxShadow();
    Float[] shadowArgs = new Float[4];
    int shadowArgIndex = -1;
    Color color = null;
    Boolean inset = null;

    for (String arg : args) {
      if (INSET.equals(arg)) {
        inset = Boolean.TRUE;
      } else if (lengthValueExtractor.isValid(arg)) {
        shadowArgs[++shadowArgIndex] = lengthValueExtractor.extract(arg).get().floatValue();
      } else if (colorValueExtractor.isValid(arg)) {
        color = colorValueExtractor.extract(arg);
      }
    }
    //@formatter:off
    if (color == null) color = Color.BLACK;
    if (inset == null) inset = Boolean.FALSE;
    //@formatter:on
    shadow.hOffset(shadowArgs[0]);
    shadow.vOffset(shadowArgs[1]);
    shadow.blur(shadowArgs[2] == null ? 0f : shadowArgs[2]);
    shadow.spread(shadowArgs[3]);
    shadow.color(color);
    shadow.inset(inset);

    return shadow;
  }
}
