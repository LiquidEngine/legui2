package com.spinyowl.spinygui.core.layout.impl.flex;


import com.spinyowl.spinygui.core.node.base.Element;
import com.spinyowl.spinygui.core.style.NodeStyle;
import com.spinyowl.spinygui.core.style.types.flex.AlignItems;
import com.spinyowl.spinygui.core.style.types.flex.AlignSelf;
import com.spinyowl.spinygui.core.style.types.flex.FlexDirection;
import com.spinyowl.spinygui.core.style.types.flex.JustifyContent;
import com.spinyowl.spinygui.core.style.types.length.Length;
import com.spinyowl.spinygui.core.style.types.length.Unit;
import org.lwjgl.util.yoga.Yoga;

/**
 * @author Aliaksandr_Shcherbin.
 */
final class FlexUtils {

    private FlexUtils() {
    }

    //@formatter:off

    public static void setJustifyContent(long node, JustifyContent justifyContent, Element element) {
        JustifyContent toUse = justifyContent;
        for (Element lp = element; toUse == JustifyContent.INHERIT; lp = lp.getParent()) {
            if (lp != null) {
                toUse = lp.getStyle().getJustifyContent();
            } else {
                toUse = JustifyContent.FLEX_START;
                break;
            }
        }
        if (toUse == JustifyContent.INITIAL || toUse == JustifyContent.FLEX_START) {
            Yoga.YGNodeStyleSetJustifyContent(node, Yoga.YGJustifyFlexStart);
        } else if (toUse == JustifyContent.CENTER) {
            Yoga.YGNodeStyleSetJustifyContent(node, Yoga.YGJustifyCenter);
        } else if (toUse == JustifyContent.FLEX_END) {
            Yoga.YGNodeStyleSetJustifyContent(node, Yoga.YGJustifyFlexEnd);
        } else if (toUse == JustifyContent.SPACE_AROUND) {
            Yoga.YGNodeStyleSetJustifyContent(node, Yoga.YGJustifySpaceAround);
        } else if (toUse == JustifyContent.SPACE_BETWEEN) {
            Yoga.YGNodeStyleSetJustifyContent(node, Yoga.YGJustifySpaceBetween);
        } else if (toUse == JustifyContent.SPACE_EVENLY) {
            Yoga.YGNodeStyleSetJustifyContent(node, Yoga.YGJustifySpaceEvenly);
        }
    }

    public static void setFlexDirection(long rootNode, FlexDirection flexDirection) {
        if (flexDirection == FlexDirection.ROW) {
            Yoga.YGNodeStyleSetFlexDirection(rootNode, Yoga.YGFlexDirectionRow);
        } else if (flexDirection == FlexDirection.COLUMN) {
            Yoga.YGNodeStyleSetFlexDirection(rootNode, Yoga.YGFlexDirectionColumn);
        } else if (flexDirection == FlexDirection.ROW_REVERSE) {
            Yoga.YGNodeStyleSetFlexDirection(rootNode, Yoga.YGFlexDirectionRowReverse);
        } else if (flexDirection == FlexDirection.COLUMN_REVERSE) {
            Yoga.YGNodeStyleSetFlexDirection(rootNode, Yoga.YGFlexDirectionColumnReverse);
        }
    }

    public static void setAlignItems(long node, AlignItems alignItems, Element component) {
        AlignItems toUse = alignItems;
        for (Element lp = component; toUse == AlignItems.INHERIT; lp = lp.getParent()) {
            if (lp != null) {
                toUse = lp.getStyle().getAlignItems();
            } else {
                toUse = AlignItems.STRETCH;
                break;
            }
        }
        if (toUse == AlignItems.FLEX_END) {
            Yoga.YGNodeStyleSetAlignItems(node, Yoga.YGAlignFlexEnd);
        } else if (toUse == AlignItems.CENTER) {
            Yoga.YGNodeStyleSetAlignItems(node, Yoga.YGAlignCenter);
        } else if (toUse == AlignItems.FLEX_START) {
            Yoga.YGNodeStyleSetAlignItems(node, Yoga.YGAlignFlexStart);
        } else if (toUse == AlignItems.STRETCH) {
            Yoga.YGNodeStyleSetAlignItems(node, Yoga.YGAlignStretch);
        } else if (toUse == AlignItems.BASELINE) {
            Yoga.YGNodeStyleSetAlignItems(node, Yoga.YGAlignBaseline);
        } else if (toUse == AlignItems.AUTO) {
            Yoga.YGNodeStyleSetAlignItems(node, Yoga.YGAlignAuto);
        }
    }

    public static void setAlignSelf(long node, AlignSelf alignItems, Element component) {
        AlignSelf toUse = alignItems;
        for (Element lp = component; toUse == AlignSelf.INHERIT; lp = lp.getParent()) {
            if (lp != null) {
                toUse = lp.getStyle().getAlignSelf();
            } else {
                toUse = AlignSelf.STRETCH;
                break;
            }
        }
        if (toUse == AlignSelf.FLEX_END) {
            Yoga.YGNodeStyleSetAlignSelf(node, Yoga.YGAlignFlexEnd);
        } else if (toUse == AlignSelf.CENTER) {
            Yoga.YGNodeStyleSetAlignSelf(node, Yoga.YGAlignCenter);
        } else if (toUse == AlignSelf.FLEX_START) {
            Yoga.YGNodeStyleSetAlignSelf(node, Yoga.YGAlignFlexStart);
        } else if (toUse == AlignSelf.STRETCH) {
            Yoga.YGNodeStyleSetAlignSelf(node, Yoga.YGAlignStretch);
        } else if (toUse == AlignSelf.BASELINE) {
            Yoga.YGNodeStyleSetAlignSelf(node, Yoga.YGAlignBaseline);
        } else if (toUse == AlignSelf.AUTO) {
            Yoga.YGNodeStyleSetAlignSelf(node, Yoga.YGAlignAuto);
        }
    }

    public static void setPadding(long node, NodeStyle style) {
        Length paddingLeft = style.getPaddingLeft();
        if (paddingLeft != null) {
            applyPadding(node, Yoga.YGEdgeLeft, paddingLeft);
        }
        Length paddingTop = style.getPaddingTop();
        if (paddingTop != null) {
            applyPadding(node, Yoga.YGEdgeTop, paddingTop);
        }
        Length paddingRight = style.getPaddingRight();
        if (paddingRight != null) {
            applyPadding(node, Yoga.YGEdgeRight, paddingRight);
        }
        Length paddingBottom = style.getPaddingBottom();
        if (paddingBottom != null) {
            applyPadding(node, Yoga.YGEdgeBottom, paddingBottom);
        }
    }

    public static void setMargin(long node, NodeStyle style) {
        Unit marginLeft = style.getMarginLeft();
        if (marginLeft != null) {
            applyMargin(node, Yoga.YGEdgeLeft, marginLeft);
        }
        Unit marginTop = style.getMarginTop();
        if (marginTop != null) {
            applyMargin(node, Yoga.YGEdgeTop, marginTop);
        }
        Unit marginRight = style.getMarginRight();
        if (marginRight != null) {
            applyMargin(node, Yoga.YGEdgeRight, marginRight);
        }
        Unit marginBottom = style.getMarginBottom();
        if (marginBottom != null) {
            applyMargin(node, Yoga.YGEdgeBottom, marginBottom);
        }
    }

    private static void applyMargin(long node, int edge, Unit margin) {
        if (margin.isAuto()) {
            Yoga.YGNodeStyleSetMarginAuto(node, edge);

        } else if (margin.isLength()) {
            Length m = margin.asLength();
            if (Length.Type.PERCENT.equals(m.type())) {
                Yoga.YGNodeStyleSetMarginPercent(node, edge, Length.Type.PERCENT.type().cast(m.get()));
            } else if (Length.Type.PIXEL.equals(m.type())) {
                Yoga.YGNodeStyleSetMargin(node, edge, Length.Type.PIXEL.type().cast(m.get()));
            }
        }
    }

    private static void applyPadding(long node, int edge, Length padding) {
        if (Length.Type.PIXEL.equals(padding.type())) {
            Yoga.YGNodeStyleSetPadding(node, edge, Length.Type.PIXEL.type().cast(padding.get()));
        } else if (Length.Type.PERCENT.equals(padding.type())) {
            Yoga.YGNodeStyleSetPaddingPercent(node, edge, Length.Type.PERCENT.type().cast(padding.get()));
        }
    }

}
