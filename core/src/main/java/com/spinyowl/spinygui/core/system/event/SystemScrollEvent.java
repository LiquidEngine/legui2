package com.spinyowl.spinygui.core.system.event;

import lombok.Data;

/**
 * Will be generated when a scrolling device is used, such as a mouse wheel or scrolling area of a
 * touchpad.
 */
@Data
public class SystemScrollEvent implements SystemEvent {

  /**
   * The window that received the event.
   */
  private final long window;

  /**
   * The scroll offset along the x-axis.
   */
  private final float offsetX;

  /**
   * The scroll offset along the y-axis.
   */
  private final float offsetY;


}
