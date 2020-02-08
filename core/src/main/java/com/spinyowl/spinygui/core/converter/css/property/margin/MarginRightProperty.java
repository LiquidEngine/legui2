package com.spinyowl.spinygui.core.converter.css.property.margin;

import com.spinyowl.spinygui.core.converter.css.Properties;
import com.spinyowl.spinygui.core.converter.css.Property;
import com.spinyowl.spinygui.core.converter.css.ValueExtractor;
import com.spinyowl.spinygui.core.converter.css.ValueExtractors;
import com.spinyowl.spinygui.core.node.base.Element;
import com.spinyowl.spinygui.core.style.types.length.Auto;
import com.spinyowl.spinygui.core.style.types.length.Unit;

public class MarginRightProperty extends Property {

    private ValueExtractor<Unit> unitValueExtractor = ValueExtractors.getInstance().getValueExtractor(Unit.class);

    public MarginRightProperty() {
        super(Properties.MARGIN_RIGHT, "0", false, true);
    }

    public MarginRightProperty(String value) {
        this();
        setValue(value);
    }

    /**
     * Used to update calculated node style of specified element.
     *
     * @param element element to update calculated style.
     */
    @Override
    protected void updateNodeStyle(Element element) {
        update(element, Auto.AUTO,
                (s, v) -> s.getMargin().setRight(v),
                s -> s.getMargin().getRight(),
                unitValueExtractor::extract);
    }

    /**
     * Used to check if value is valid or not.
     *
     * @return true if value is valid. By default returns false.
     */
    @Override
    public boolean isValid() {
        return super.isValid() || unitValueExtractor.isValid(value);
    }
}
