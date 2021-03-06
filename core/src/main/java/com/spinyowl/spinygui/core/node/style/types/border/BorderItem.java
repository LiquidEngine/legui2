package com.spinyowl.spinygui.core.node.style.types.border;

import com.spinyowl.spinygui.core.node.style.types.Color;
import com.spinyowl.spinygui.core.node.style.types.length.Length;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class BorderItem {

  @NonNull
  private Color color = Color.TRANSPARENT;
  @NonNull
  private BorderStyle style = BorderStyle.NONE;
  @NonNull
  private Length<?> width = Length.ZERO;

}
