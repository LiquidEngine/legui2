package com.spinyowl.spinygui.core.converter.css;

import com.spinyowl.spinygui.core.converter.css.util.StyleUtils;
import com.spinyowl.spinygui.core.node.base.Element;
import com.spinyowl.spinygui.core.style.NodeStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Root class that describes property. Should be used to create new classes which implement {@link
 * Property#computeAndApply(Element, String)} and {@link Property#isValid(String)}.
 */
public abstract class Property<T> {

    public static final boolean ANIMATABLE = true;
    public static final boolean INHERITED = true;

    public static final String INHERIT = "inherit";
    public static final String INITIAL = "initial";

    private static final Logger LOGGER = LoggerFactory.getLogger(Property.class);

    /**
     * Name of css property.
     */
    protected final String name;

    /**
     * Default value of css property. Could not be null. This value used for next cases:
     * <ul>
     *     <li>element is root element in element tree.</li>
     *     <li>element value is {@link Property#INITIAL} </li>
     *     <li>there was an issue during computing value using value extractor - treats as no property specified in style.</li>
     * </ul>
     */
    protected final String defaultValue;

    /**
     * Defines if css property for element should be inherited from parent element.
     * <br>
     * When no value for an inherited property has been specified on an element, the element gets
     * the computed value of that property on its parent element. Only the root element of the
     * document gets the initial value given in the property's summary.
     * <br>
     * When no value for a non-inherited property has been specified on an element, the element gets
     * the initial value of that property (as specified in the property's summary).
     * <br>
     * The <b>inherit</b> keyword allows authors to explicitly specify inheritance. It works on both
     * inherited and non-inherited properties.
     */
    protected final boolean inherited;

    /**
     * Defines if css property could be animated.
     */
    protected final boolean animatable;


    protected final BiConsumer<NodeStyle, T> valueSetter;
    protected final Function<NodeStyle, T> valueGetter;
    protected final Function<String, T> valueExtractor;
    protected final Predicate<String> valueValidator;

    protected final T defaultComputedValue;

    /**
     * Constructor that should be used by implementations to initialize css property. All
     * implementations should provide at least no-argument constructor.
     *
     * @param name           name of css property.
     * @param defaultValue   default value of css property. Could be null if there is no default
     *                       value.
     * @param inherited      inheritance property. Defines if css property for element should be
     *                       inherited from parent element.
     *                       <br>
     *                       When no value for an inherited property has been specified on an
     *                       element, the element gets the computed value of that property on its
     *                       parent element. Only the root element of the document gets the initial
     *                       value given in the property's summary.
     *                       <br>
     *                       When no value for a non-inherited property has been specified on an
     *                       element, the element gets the initial value of that property (as
     *                       specified in the property's summary).
     *                       <br>
     *                       The <b>inherit</b> keyword allows authors to explicitly specify
     *                       inheritance. It works on both inherited and non-inherited properties.
     * @param animatable     defines if css property could be animated.
     * @param valueSetter    function that sets calculated css value to calculated style.
     * @param valueGetter    function that retrieves css value from calculated style.
     * @param valueExtractor function that calculates value from it's string representation.
     */
    protected Property(String name,
        String defaultValue,
        boolean inherited,
        boolean animatable,
        BiConsumer<NodeStyle, T> valueSetter,
        Function<NodeStyle, T> valueGetter,
        Function<String, T> valueExtractor,
        Predicate<String> valueValidator
    ) {
        this.name = Objects.requireNonNull(name);
        this.defaultValue = Objects.requireNonNull(defaultValue);
        this.inherited = inherited;
        this.animatable = animatable;
        this.valueSetter = Objects.requireNonNull(valueSetter);
        this.valueGetter = Objects.requireNonNull(valueGetter);
        this.valueExtractor = Objects.requireNonNull(valueExtractor);
        this.valueValidator = Objects.requireNonNull(valueValidator);

        T computedValue = valueExtractor.apply(defaultValue);

        // For now we skipping throwing exception on parsing default property value
        // TODO: make something with null values in properties
//        if (computedValue == null) {
//            throw new IllegalStateException("Failed to calculated default style.");
//        }
        this.defaultComputedValue = computedValue;
    }

    /**
     * Used to compute css style property value for specified element.
     *
     * @param element element to update calculated style.
     * @param value   string representation of css property value.
     */
    public void computeAndApply(Element element, String value) {
        Objects.requireNonNull(element);

        T computedValue;
        if (value == null) {
            computedValue = computeAbsent(element);
        } else {

            if (INITIAL.equalsIgnoreCase(value)) {
                computedValue = getDefaultComputedValue();
            } else if (INHERIT.equalsIgnoreCase(value)) {
                computedValue = getInheritedValue(element);
            } else {
                try {
                    computedValue = valueExtractor.apply(value);
                } catch (Exception t) {
                    LOGGER.error("Error during extracting value from '{}' with '{}' extractor. {}",
                        value, valueExtractor, t.getMessage());
                    computedValue = null;
                }
            }
            // in case if we have some exception or value extractor returned null - recalculating value.
            if (computedValue == null) {
                computedValue = computeAbsent(element);
            }
        }
        valueSetter.accept(element.getCalculatedStyle(), computedValue);
    }

    protected T computeAbsent(Element element) {
        return conditionalValue(isInherited(), element, this::getInheritedValue);
    }

    protected T getInheritedValue(Element element) {
        NodeStyle parentStyle = StyleUtils.getParentCalculatedStyle(element);
        return conditionalValue(parentStyle != null, parentStyle, valueGetter);
    }

    protected <Q> T conditionalValue(boolean condition, Q functionArg,
        Function<Q, T> valueProvider) {
        T computedValue;
        if (condition) {
            computedValue = valueProvider.apply(functionArg);
        } else {
            computedValue = getDefaultComputedValue();
        }
        return computedValue;
    }

    /**
     * Used to check if value is valid or not.
     *
     * @return true if value is valid. By default returns false.
     */
    public boolean isValid(String value) {
        return INHERIT.equalsIgnoreCase(value) || INITIAL.equalsIgnoreCase(value) ||
            valueValidator.test(value);
    }

    /**
     * Returns property name.
     *
     * @return property name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns property default value.
     *
     * @return property default value.
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Defines if css property for element should be inherited from parent element.
     * <br>
     * When no value for an inherited property has been specified on an element, the element gets
     * the computed value of that property on its parent element. Only the root element of the
     * document gets the initial value given in the property's summary.
     * <br>
     * When no value for a non-inherited property has been specified on an element, the element gets
     * the initial value of that property (as specified in the property's summary).
     * <br>
     * The <b>inherit</b> keyword allows authors to explicitly specify inheritance. It works on both
     * inherited and non-inherited properties.
     *
     * @return true if this property inherit value from parent by default.
     */
    public boolean isInherited() {
        return inherited;
    }

    /**
     * Returns true if css property could be animated.
     *
     * @return true if css property could be animated.
     */
    public boolean isAnimatable() {
        return animatable;
    }

    public T getDefaultComputedValue() {
        return defaultComputedValue;
    }
}