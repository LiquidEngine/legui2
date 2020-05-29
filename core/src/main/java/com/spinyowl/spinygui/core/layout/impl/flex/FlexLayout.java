package com.spinyowl.spinygui.core.layout.impl.flex;

import static com.spinyowl.spinygui.core.layout.impl.flex.FlexUtils.setAlignItems;
import static com.spinyowl.spinygui.core.layout.impl.flex.FlexUtils.setAlignSelf;
import static com.spinyowl.spinygui.core.layout.impl.flex.FlexUtils.setFlexDirection;
import static com.spinyowl.spinygui.core.layout.impl.flex.FlexUtils.setFlexWrap;
import static com.spinyowl.spinygui.core.layout.impl.flex.FlexUtils.setJustifyContent;
import static com.spinyowl.spinygui.core.layout.impl.flex.FlexUtils.setLength;
import static com.spinyowl.spinygui.core.layout.impl.flex.FlexUtils.setMargin;
import static com.spinyowl.spinygui.core.layout.impl.flex.FlexUtils.setPadding;
import static com.spinyowl.spinygui.core.layout.impl.flex.FlexUtils.setUnit;
import static org.lwjgl.util.yoga.Yoga.YGDirectionLTR;
import static org.lwjgl.util.yoga.Yoga.YGDisplayFlex;
import static org.lwjgl.util.yoga.Yoga.YGEdgeBottom;
import static org.lwjgl.util.yoga.Yoga.YGEdgeLeft;
import static org.lwjgl.util.yoga.Yoga.YGEdgeRight;
import static org.lwjgl.util.yoga.Yoga.YGEdgeTop;
import static org.lwjgl.util.yoga.Yoga.YGNodeFree;
import static org.lwjgl.util.yoga.Yoga.YGNodeInsertChild;
import static org.lwjgl.util.yoga.Yoga.YGNodeLayoutGetHeight;
import static org.lwjgl.util.yoga.Yoga.YGNodeLayoutGetLeft;
import static org.lwjgl.util.yoga.Yoga.YGNodeLayoutGetTop;
import static org.lwjgl.util.yoga.Yoga.YGNodeLayoutGetWidth;
import static org.lwjgl.util.yoga.Yoga.YGNodeNew;
import static org.lwjgl.util.yoga.Yoga.YGNodeStyleSetDisplay;
import static org.lwjgl.util.yoga.Yoga.YGNodeStyleSetFlexGrow;
import static org.lwjgl.util.yoga.Yoga.YGNodeStyleSetFlexShrink;
import static org.lwjgl.util.yoga.Yoga.YGNodeStyleSetHeight;
import static org.lwjgl.util.yoga.Yoga.YGNodeStyleSetPositionType;
import static org.lwjgl.util.yoga.Yoga.YGNodeStyleSetWidth;
import static org.lwjgl.util.yoga.Yoga.YGPositionTypeAbsolute;
import static org.lwjgl.util.yoga.Yoga.YGPositionTypeRelative;
import static org.lwjgl.util.yoga.Yoga.nYGNodeCalculateLayout;
import com.spinyowl.spinygui.core.event.ChangePositionEvent;
import com.spinyowl.spinygui.core.event.ChangeSizeEvent;
import com.spinyowl.spinygui.core.event.ElementTreeUpdateEvent;
import com.spinyowl.spinygui.core.event.processor.EventProcessor;
import com.spinyowl.spinygui.core.layout.Layout;
import com.spinyowl.spinygui.core.node.Element;
import com.spinyowl.spinygui.core.node.Node;
import com.spinyowl.spinygui.core.style.NodeStyle;
import com.spinyowl.spinygui.core.style.types.Position;
import com.spinyowl.spinygui.core.style.types.flex.Flex;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.joml.Vector2f;
import org.lwjgl.util.yoga.Yoga;

/**
 * @author Aliaksandr_Shcherbin.
 */
@Getter
@RequiredArgsConstructor
public class FlexLayout implements Layout {

  public static final float THRESHOLD = 0.0001f;
  private final EventProcessor eventProcessor;

  /**
   * Used to lay out child components for parent component.
   *
   * @param parent component to lay out.
   */
  @Override
  public void layout(Element parent) {
    // initialize
    long rootNode = YGNodeNew();
    prepareParentNode(parent, rootNode);
    YGNodeStyleSetDisplay(rootNode, YGDisplayFlex);

    List<Long> childNodes = new ArrayList<>();
    List<Element> components = parent.children().stream()
        .filter(Node::visible).collect(Collectors.toList());
    for (Element component : components) {
      long childNode = YGNodeNew();
      prepareNode(component, childNode);
      YGNodeInsertChild(rootNode, childNode, childNodes.size());
      childNodes.add(childNode);
    }

    // calculate
    nYGNodeCalculateLayout(rootNode, parent.size().x(), parent.size().y(),
        YGDirectionLTR);

    // apply to components
    for (int i = 0; i < components.size(); i++) {
      Node childComponent = components.get(i);
      Long yogaNode = childNodes.get(i);

      Vector2f newPos = new Vector2f(YGNodeLayoutGetLeft(yogaNode),
          YGNodeLayoutGetTop(yogaNode));
      Vector2f oldPos = new Vector2f(childComponent.position());
      childComponent.position(newPos);

      Vector2f newSize = new Vector2f(YGNodeLayoutGetWidth(yogaNode),
          YGNodeLayoutGetHeight(yogaNode));
      Vector2f oldSize = new Vector2f(childComponent.size());
      childComponent.size(newSize);

      generateEvents(childComponent, newPos, oldPos, newSize, oldSize);
    }

    // free mem
    for (Long childNode : childNodes) {
      YGNodeFree(childNode);
    }

    YGNodeFree(rootNode);
  }

  private void generateEvents(Node childComponent, Vector2f newPos, Vector2f oldPos,
      Vector2f newSize, Vector2f oldSize) {
    if (childComponent instanceof Element) {
      Element e = (Element) childComponent;
      boolean invalidateTree = false;
      if (!oldPos.equals(newPos, THRESHOLD)) {
        eventProcessor.pushEvent(new ChangePositionEvent<>(e, oldPos, newPos));
        invalidateTree = true;
      }
      if (!oldSize.equals(newSize, THRESHOLD)) {
        eventProcessor.pushEvent(new ChangeSizeEvent<>(e, oldSize, newSize));
        invalidateTree = true;
      }
      if (invalidateTree) {
        eventProcessor.pushEvent(new ElementTreeUpdateEvent());
      }
    }
  }

  private void prepareParentNode(Element parent, long rootNode) {
    prepareNode(parent, rootNode);
    YGNodeStyleSetWidth(rootNode, parent.size().x());
    YGNodeStyleSetHeight(rootNode, parent.size().y());
  }

  /**
   * Used to prepare root node.
   *
   * @param component parent component associated to root node.
   * @param node      root yoga node.
   */
  private void prepareNode(Element component, long node) {
    NodeStyle style = component.style();
    Flex flex = style.flex();
    setFlexDirection(node, flex.flexDirection());
    setJustifyContent(node, flex.justifyContent());
    setAlignItems(node, flex.alignItems());
    setAlignSelf(node, flex.alignSelf());

    setLength(style.minWidth(), node, Yoga::YGNodeStyleSetMinWidth,
        Yoga::YGNodeStyleSetMinWidthPercent);
    setLength(style.minHeight(), node, Yoga::YGNodeStyleSetMinHeight,
        Yoga::YGNodeStyleSetMinHeightPercent);

    setLength(style.maxWidth(), node, Yoga::YGNodeStyleSetMaxWidth,
        Yoga::YGNodeStyleSetMaxWidthPercent);
    setLength(style.maxHeight(), node, Yoga::YGNodeStyleSetMaxHeight,
        Yoga::YGNodeStyleSetMaxHeightPercent);

    setUnit(style.width(), node, Yoga::YGNodeStyleSetWidthAuto, Yoga::YGNodeStyleSetWidth,
        Yoga::YGNodeStyleSetWidthPercent);
    setUnit(style.height(), node, Yoga::YGNodeStyleSetHeightAuto, Yoga::YGNodeStyleSetHeight,
        Yoga::YGNodeStyleSetHeightPercent);

    setUnit(style.top(), node, YGEdgeTop, Yoga::YGNodeStyleSetPosition,
        Yoga::YGNodeStyleSetPositionPercent);
    setUnit(style.bottom(), node, YGEdgeBottom, Yoga::YGNodeStyleSetPosition,
        Yoga::YGNodeStyleSetPositionPercent);
    setUnit(style.right(), node, YGEdgeRight, Yoga::YGNodeStyleSetPosition,
        Yoga::YGNodeStyleSetPositionPercent);
    setUnit(style.left(), node, YGEdgeLeft, Yoga::YGNodeStyleSetPosition,
        Yoga::YGNodeStyleSetPositionPercent);

    setUnit(flex.flexBasis(), node,
        Yoga::YGNodeStyleSetFlexBasisAuto, Yoga::YGNodeStyleSetFlexBasis,
        Yoga::YGNodeStyleSetFlexBasisPercent);

    setPadding(node, style);
    setMargin(node, style);

    setFlexWrap(node, flex.flexWrap());
    YGNodeStyleSetPositionType(node,
        style.position() == Position.RELATIVE ? YGPositionTypeRelative
            : YGPositionTypeAbsolute);

    YGNodeStyleSetFlexGrow(node, flex.flexGrow());
    YGNodeStyleSetFlexShrink(node, flex.flexShrink());
  }
}
