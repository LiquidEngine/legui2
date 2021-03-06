package com.spinyowl.spinygui.core.node.style.types;

import com.spinyowl.spinygui.core.node.style.types.length.Length;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Class container for border radius properties.
 */
@Data
@NoArgsConstructor
public class BorderRadius {

  /**
   * Top left border radius.
   */
  @NonNull
  private Length<?> topLeft = Length.ZERO;
  /**
   * Top right border radius.
   */
  @NonNull
  private Length<?> topRight = Length.ZERO;
  /**
   * Bottom right border radius.
   */
  @NonNull
  private Length<?> bottomRight = Length.ZERO;
  /**
   * Bottom left border radius.
   */
  @NonNull
  private Length<?> bottomLeft = Length.ZERO;

  /**
   * Used to create border radius.
   *
   * @param radius radius to set. Sets border radius to all corners.
   */
  public BorderRadius(Length<?> radius) {
    topLeft = topRight = bottomRight = bottomLeft = radius;
  }

  /**
   * Used to create border radius.
   *
   * @param topLeftBottomRight top left and bottom right radius.
   * @param topRightBottomLeft top right and bottom left radius.
   */
  public BorderRadius(Length<?> topLeftBottomRight, Length<?> topRightBottomLeft) {
    topLeft = bottomRight = topLeftBottomRight;
    topRight = bottomLeft = topRightBottomLeft;
  }

  /**
   * Used to create border radius.
   *
   * @param topLeft            top left radius.
   * @param bottomRight        bottom right radius.
   * @param topRightBottomLeft top right and bottom left radius.
   */
  public BorderRadius(Length<?> topLeft, Length<?> topRightBottomLeft, Length<?> bottomRight) {
    this.topLeft = topLeft;
    this.topRight = this.bottomLeft = topRightBottomLeft;
    this.bottomRight = bottomRight;
  }

  /**
   * Used to create border radius.
   *
   * @param topLeft     top left radius.
   * @param topRight    top right radius.
   * @param bottomRight bottom right radius.
   * @param bottomLeft  bottom left radius.
   */
  public BorderRadius(Length<?> topLeft, Length<?> topRight, Length<?> bottomRight, Length<?> bottomLeft) {
    this.topLeft = topLeft;
    this.topRight = topRight;
    this.bottomRight = bottomRight;
    this.bottomLeft = bottomLeft;
  }

}
