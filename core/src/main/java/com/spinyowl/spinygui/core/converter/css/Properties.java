package com.spinyowl.spinygui.core.converter.css;

import com.spinyowl.spinygui.core.converter.css.property.*;
import com.spinyowl.spinygui.core.converter.css.property.background.BackgroundColorProperty;
import com.spinyowl.spinygui.core.converter.css.property.border.*;
import com.spinyowl.spinygui.core.converter.css.property.border.radius.*;
import com.spinyowl.spinygui.core.converter.css.property.dimension.*;
import com.spinyowl.spinygui.core.converter.css.property.flex.*;
import com.spinyowl.spinygui.core.converter.css.property.margin.*;
import com.spinyowl.spinygui.core.converter.css.property.padding.*;
import com.spinyowl.spinygui.core.converter.css.property.position.BottomProperty;
import com.spinyowl.spinygui.core.converter.css.property.position.LeftProperty;
import com.spinyowl.spinygui.core.converter.css.property.position.RightProperty;
import com.spinyowl.spinygui.core.converter.css.property.position.TopProperty;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public final class Properties {
    public static final String COLOR = "color";

    //    public static final String BACKGROUND            = "background";
    public static final String BACKGROUND_COLOR = "background-color";
    public static final String BACKGROUND_IMAGE = "background-image";
//    public static final String BACKGROUND_POSITION   = "background-position";
//    public static final String BACKGROUND_SIZE       = "background-size";
//    public static final String BACKGROUND_REPEAT     = "background-repeat";
//    public static final String BACKGROUND_ORIGIN     = "background-origin";
//    public static final String BACKGROUND_CLIP       = "background-clip";
//    public static final String BACKGROUND_ATTACHMENT = "background-attachment";

    public static final String BORDER_RADIUS              = "border-radius";
    public static final String BORDER_BOTTOM_LEFT_RADIUS  = "border-bottom-left-radius";
    public static final String BORDER_BOTTOM_RIGHT_RADIUS = "border-bottom-right-radius";
    public static final String BORDER_TOP_LEFT_RADIUS     = "border-top-left-radius";
    public static final String BORDER_TOP_RIGHT_RADIUS    = "border-top-right-radius";

    public static final String PADDING        = "padding";
    public static final String PADDING_TOP    = "padding-top";
    public static final String PADDING_RIGHT  = "padding-right";
    public static final String PADDING_BOTTOM = "padding-bottom";
    public static final String PADDING_LEFT   = "padding-left";

    public static final String MARGIN        = "margin";
    public static final String MARGIN_TOP    = "margin-top";
    public static final String MARGIN_RIGHT  = "margin-right";
    public static final String MARGIN_BOTTOM = "margin-bottom";
    public static final String MARGIN_LEFT   = "margin-left";

    public static final String BORDER       = "border";
    public static final String BORDER_WIDTH = "border-width";
    public static final String BORDER_COLOR = "border-color";
    public static final String BORDER_STYLE = "border-style";

    public static final String BORDER_LEFT   = "border-left";
    public static final String BORDER_RIGHT  = "border-right";
    public static final String BORDER_TOP    = "border-top";
    public static final String BORDER_BOTTOM = "border-bottom";

    public static final String BORDER_LEFT_WIDTH   = "border-left-width";
    public static final String BORDER_RIGHT_WIDTH  = "border-right-width";
    public static final String BORDER_TOP_WIDTH    = "border-top-width";
    public static final String BORDER_BOTTOM_WIDTH = "border-bottom-width";

    public static final String BORDER_LEFT_COLOR   = "border-left-color";
    public static final String BORDER_RIGHT_COLOR  = "border-right-color";
    public static final String BORDER_TOP_COLOR    = "border-top-color";
    public static final String BORDER_BOTTOM_COLOR = "border-bottom-color";

    public static final String BORDER_LEFT_STYLE   = "border-left-style";
    public static final String BORDER_RIGHT_STYLE  = "border-right-style";
    public static final String BORDER_TOP_STYLE    = "border-top-style";
    public static final String BORDER_BOTTOM_STYLE = "border-bottom-style";

    public static final String ALIGN_CONTENT   = "align-content";
    public static final String ALIGN_ITEMS     = "align-items";
    public static final String ALIGN_SELF      = "align-self";
    public static final String FLEX_BASIS      = "flex-basis";
    public static final String FLEX_DIRECTION  = "flex-direction";
    public static final String FLEX_GROW       = "flex-grow";
    public static final String FLEX_SHRINK     = "flex-shrink";
    public static final String FLEX_WRAP       = "flex-wrap";
    public static final String JUSTIFY_CONTENT = "justify-content";

    public static final String WIDTH  = "width";
    public static final String HEIGHT = "height";

    public static final String MIN_WIDTH  = "min-width";
    public static final String MIN_HEIGHT = "min-height";

    public static final String MAX_WIDTH  = "max-width";
    public static final String MAX_HEIGHT = "max-height";

    public static final String DISPLAY  = "display";
    public static final String POSITION = "position";

    public static final String TOP    = "top";
    public static final String BOTTOM = "bottom";
    public static final String RIGHT  = "right";
    public static final String LEFT   = "left";

    public static final String WHITE_SPACE = "white-space";

    private List<String> supportedProperties = new ArrayList<>();

    private Map<String, Supplier<Property>> propertySupplierMap = new ConcurrentHashMap<>();

    /**
     * Hidden constructor.
     */
    private Properties() {
        _addProperty(COLOR, ColorProperty::new);
        _addProperty(BACKGROUND_COLOR, BackgroundColorProperty::new);

        _addProperty(PADDING, PaddingProperty::new);
        _addProperty(PADDING_TOP, PaddingTopProperty::new);
        _addProperty(PADDING_RIGHT, PaddingRightProperty::new);
        _addProperty(PADDING_BOTTOM, PaddingBottomProperty::new);
        _addProperty(PADDING_LEFT, PaddingLeftProperty::new);

        _addProperty(MARGIN, MarginProperty::new);
        _addProperty(MARGIN_TOP, MarginTopProperty::new);
        _addProperty(MARGIN_RIGHT, MarginRightProperty::new);
        _addProperty(MARGIN_BOTTOM, MarginBottomProperty::new);
        _addProperty(MARGIN_LEFT, MarginLeftProperty::new);

        _addProperty(WIDTH, WidthProperty::new);
        _addProperty(HEIGHT, HeightProperty::new);

        _addProperty(MIN_WIDTH, MinWidthProperty::new);
        _addProperty(MIN_HEIGHT, MinHeightProperty::new);

        _addProperty(MAX_WIDTH, MaxWidthProperty::new);
        _addProperty(MAX_HEIGHT, MaxHeightProperty::new);

        _addProperty(TOP, TopProperty::new);
        _addProperty(RIGHT, RightProperty::new);
        _addProperty(BOTTOM, BottomProperty::new);
        _addProperty(LEFT, LeftProperty::new);

        _addProperty(DISPLAY, DisplayProperty::new);
        _addProperty(POSITION, PositionProperty::new);

        _addProperty(WHITE_SPACE, WhiteSpaceProperty::new);

        _addProperty(BORDER_WIDTH, BorderWidthProperty::new);
        _addProperty(BORDER_LEFT_WIDTH, BorderLeftWidthProperty::new);
        _addProperty(BORDER_TOP_WIDTH, BorderTopWidthProperty::new);
        _addProperty(BORDER_BOTTOM_WIDTH, BorderBottomWidthProperty::new);
        _addProperty(BORDER_RIGHT_WIDTH, BorderRightWidthProperty::new);

        _addProperty(BORDER_COLOR, BorderColorProperty::new);
        _addProperty(BORDER_LEFT_COLOR, BorderLeftColorProperty::new);
        _addProperty(BORDER_TOP_COLOR, BorderTopColorProperty::new);
        _addProperty(BORDER_BOTTOM_COLOR, BorderBottomColorProperty::new);
        _addProperty(BORDER_RIGHT_COLOR, BorderRightColorProperty::new);

        _addProperty(BORDER_STYLE, BorderStyleProperty::new);
        _addProperty(BORDER_LEFT_STYLE, BorderLeftStyleProperty::new);
        _addProperty(BORDER_TOP_STYLE, BorderTopStyleProperty::new);
        _addProperty(BORDER_BOTTOM_STYLE, BorderBottomStyleProperty::new);
        _addProperty(BORDER_RIGHT_STYLE, BorderRightStyleProperty::new);

        _addProperty(BORDER, BorderProperty::new);
        _addProperty(BORDER_LEFT, BorderLeftProperty::new);
        _addProperty(BORDER_RIGHT, BorderRightProperty::new);
        _addProperty(BORDER_TOP, BorderTopProperty::new);
        _addProperty(BORDER_BOTTOM, BorderBottomProperty::new);

        _addProperty(BORDER_RADIUS, BorderRadiusProperty::new);
        _addProperty(BORDER_BOTTOM_LEFT_RADIUS, BorderBottomLeftRadiusProperty::new);
        _addProperty(BORDER_BOTTOM_RIGHT_RADIUS, BorderBottomRightRadiusProperty::new);
        _addProperty(BORDER_TOP_LEFT_RADIUS, BorderTopLeftRadiusProperty::new);
        _addProperty(BORDER_TOP_RIGHT_RADIUS, BorderTopRightRadiusProperty::new);

        _addProperty(ALIGN_CONTENT, AlignContentProperty::new);
        _addProperty(ALIGN_ITEMS, AlignItemsProperty::new);
        _addProperty(ALIGN_SELF, AlignSelfProperty::new);
        _addProperty(FLEX_BASIS, FlexBasisProperty::new);
        _addProperty(FLEX_GROW, FlexGrowProperty::new);
        _addProperty(FLEX_DIRECTION, FlexDirectionProperty::new);
        _addProperty(FLEX_SHRINK, FlexShrinkProperty::new);
        _addProperty(FLEX_WRAP, FlexWrapProperty::new);
        _addProperty(JUSTIFY_CONTENT, JustifyContentProperty::new);
    }

    //@formatter:off
    /** Getter for instance. */
    private static Properties getInstance() { return PropertiesHolder.INSTANCE; }
    //@formatter:on

    public static Supplier<Property> getPropertySupplier(String propertyName) {
        return getInstance()._getPropertySupplier(propertyName);
    }

    /**
     * Used to create {@link Property}.
     * Returns {@link UnsupportedProperty} in next cases:
     * <ul>
     *     <li>property not listed in supported properties ({@link Properties#getSupportedProperties()}</li>
     *     <li>property supplier not exist</li>
     *     <li>property supplier creates null property</li>
     * </ul>
     *
     * @param propertyName  property name which should be used to find appropriate property supplier.
     * @param propertyValue property value which should be used to fill created property.
     * @return new created property instance or instance of {@link UnsupportedProperty} if property not supported.
     */
    public static Property createProperty(String propertyName, String propertyValue) {
        return getInstance()._createProperty(propertyName, propertyValue);
    }

    /**
     * Used to add property support.
     *
     * @param property         property to support.
     * @param propertySupplier property supplier which will be used to create new {@link Property} instance.
     */
    public static void addProperty(String property, Supplier<Property> propertySupplier) {
        getInstance()._addProperty(property, propertySupplier);
    }

    public static void removeSupportedProperty(String property) {
        getInstance()._removeSupportedProperty(property);
    }

    /**
     * Returns unmodifiable list of supported properties.
     *
     * @return unmodifiable list of supported properties.
     */
    public static List<String> getSupportedProperties() {
        return getInstance()._getSupportedProperties();
    }

    private Supplier<Property> _getPropertySupplier(String propertyName) {
        return propertySupplierMap.get(propertyName);
    }

    /**
     * Used to create {@link Property}.
     * Returns {@link UnsupportedProperty} in next cases:
     * <ul>
     *     <li>property not listed in supported properties ({@link Properties#_getSupportedProperties()}</li>
     *     <li>property supplier not exist</li>
     *     <li>property supplier creates null property</li>
     * </ul>
     *
     * @param propertyName  property name which should be used to find appropriate property supplier.
     * @param propertyValue property value which should be used to fill created property.
     * @return new created property instance or instance of {@link UnsupportedProperty} if property not supported.
     */
    private Property _createProperty(String propertyName, String propertyValue) {
        Objects.requireNonNull(propertyName);
        Property property;
        if (supportedProperties.contains(propertyName)) {
            Supplier<Property> propertySupplier = propertySupplierMap.get(propertyName);
            if (propertySupplier != null) {
                property = propertySupplier.get();
                if (property == null) {
                    property = new UnsupportedProperty(propertyName);
                }
            } else {
                property = new UnsupportedProperty(propertyName);
            }
        } else {
            property = new UnsupportedProperty(propertyName);
        }
        property.setValue(propertyValue);
        return property;
    }

    /**
     * Used to add property support.
     *
     * @param property         property to support.
     * @param propertySupplier property supplier which will be used to create new {@link Property} instance.
     */
    private void _addProperty(String property, Supplier<Property> propertySupplier) {
        Objects.requireNonNull(property);
        Objects.requireNonNull(propertySupplier);

        supportedProperties.add(property);
        propertySupplierMap.put(property, propertySupplier);
    }

    private void _removeSupportedProperty(String property) {
        Objects.requireNonNull(property);
        supportedProperties.remove(property);
        propertySupplierMap.remove(property);
    }

    /**
     * Returns unmodifiable list of supported properties.
     *
     * @return unmodifiable list of supported properties.
     */
    private List<String> _getSupportedProperties() {
        return Collections.unmodifiableList(supportedProperties);
    }

    /**
     * Instance holder.
     */
    private static class PropertiesHolder {
        private static final Properties INSTANCE = new Properties();
    }
}
