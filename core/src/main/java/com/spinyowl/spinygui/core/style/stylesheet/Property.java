package com.spinyowl.spinygui.core.style.stylesheet;

import com.spinyowl.spinygui.core.node.Element;
import com.spinyowl.spinygui.core.node.style.NodeStyle;
import com.spinyowl.spinygui.core.style.stylesheet.util.StyleUtils;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Root class that describes property. Should be used to create new classes which implement {@link
 * Property#compute(Element, String)}}.
 */
@Slf4j
@Getter
public class Property<T> {

  public static final String INHERIT = "inherit";
  public static final String INITIAL = "initial";

  public static final boolean ANIMATABLE = true;
  public static final boolean INHERITED = true;

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
   * When no value for an inherited property has been specified on an element, the element gets the
   * computed value of that property on its parent element. Only the root element of the document
   * gets the initial value given in the property's summary.
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

  /**
   * Used to set calculated value to node style.
   */
  protected final BiConsumer<NodeStyle, T> valueSetter;

  /**
   * Used to get calculated value from node style.
   */
  protected final Function<NodeStyle, T> valueGetter;

  /**
   * Used to extract and compute value from string representation.
   */
  protected final Function<String, T> valueExtractor;

  /**
   * Used to validate string value before extraction.
   */
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
   *                       When no value for an inherited property has been specified on an element,
   *                       the element gets the computed value of that property on its parent
   *                       element. Only the root element of the document gets the initial value
   *                       given in the property's summary.
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

    this.defaultComputedValue = valueExtractor.apply(defaultValue);
  }

  /**
   * Used to compute css style property value for specified element.
   *
   * @param element element to update calculated style.
   * @param value   string representation of css property value.
   */
  public final void computeAndApply(Element element, String value) {
    valueSetter.accept(element.calculatedStyle(), compute(element, value));
  }

  /**
   * Used to compute css style property value for specified element.
   *
   * @param element element to update calculated style.
   * @param value   string representation of css property value.
   * @return computed value.
   */
  public T compute(Element element, String value) {
    Objects.requireNonNull(element);

    T computedValue = null;
    if (value == null) {
      computedValue = computeAbsent(element);
    } else {

      if (INITIAL.equalsIgnoreCase(value)) {
        computedValue = defaultComputedValue();
      } else if (INHERIT.equalsIgnoreCase(value)) {
        computedValue = inheritedValue(element);
      } else if (valueValidator.test(value)) {
        try {
          computedValue = valueExtractor.apply(value);
        } catch (Exception t) {
          log.error("Error during extracting value from '{}' with '{}' extractor. {}",
              value, valueExtractor, t.getMessage());
          computedValue = null;
        }
      }
      // in case if we have some exception or value extractor returned null - recalculating value.
      if (computedValue == null) {
        computedValue = computeAbsent(element);
      }
    }
    return computedValue;
  }

  public T computeAbsent(Element element) {
    return inherited() ? this.inheritedValue(element) : defaultComputedValue();
  }

  public T inheritedValue(Element element) {
    NodeStyle parentStyle = StyleUtils.getParentCalculatedStyle(element);
    return parentStyle != null ? valueGetter.apply(parentStyle) : defaultComputedValue();
  }
}
