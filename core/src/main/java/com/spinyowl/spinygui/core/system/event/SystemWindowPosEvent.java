package com.spinyowl.spinygui.core.system.event;

import lombok.Data;

/**
 * Will be generated when the specified window moves.
 */
@Data
public class SystemWindowPosEvent implements SystemEvent {

  /**
   * The window that was moved.
   */
  private final long window;

  /**
   * The new x-coordinate, in screen coordinates, of the upper-left corner of the content area of
   * the window.
   */
  private final int posX;

  /**
   * The new y-coordinate, in screen coordinates, of the upper-left corner of the content area of
   * the window.
   */
  private final int posY;


}
