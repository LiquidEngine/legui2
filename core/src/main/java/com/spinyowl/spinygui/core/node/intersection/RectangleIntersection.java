package com.spinyowl.spinygui.core.node.intersection;

import com.spinyowl.spinygui.core.node.Node;
import lombok.Data;
import org.joml.Vector2fc;

@Data
public class RectangleIntersection implements Intersection {

  /**
   * Intersection rule.
   *
   * @param node node to check intersection.
   * @param x    x coordinates of point to check intersection.
   * @param y    y coordinates of point to check intersection.
   * @return true if node intersected by point.
   */
  @Override
  public boolean intersects(Node node, float x, float y) {
    Vector2fc pos = node.position();
    Vector2fc size = node.size();
    return x >= pos.x()
        && x <= pos.x() + size.x()
        && y >= pos.y()
        && y <= pos.y() + size.y();
  }
}
