package com.spinyowl.spinygui.core.style.css.extractor;

import com.spinyowl.spinygui.core.style.css.ValueExtractor;
import com.spinyowl.spinygui.core.style.types.length.Auto;
import com.spinyowl.spinygui.core.style.types.length.Length;
import com.spinyowl.spinygui.core.style.types.length.Unit;

public class UnitValueExtractor implements ValueExtractor<Unit> {

    public static final String PERCENTAGE_REGEX = "-?(\\d+(\\.\\d*)?|\\.\\d+)%";
    public static final String PIXEL_REGEX = "-?\\d+px";
    public static final String AUTO_REGEX = "auto";

    public static Length<Float> getLength(String value) {
        if (value.matches(PIXEL_REGEX)) {
            String pixelValue = value.substring(0, value.length() - 2);
            return Length.pixel(Float.parseFloat(pixelValue));
        }

        if (value.matches(PERCENTAGE_REGEX)) {
            String percentageValue = value.substring(0, value.length() - 1);
            return Length.percent(Float.parseFloat(percentageValue));
        }
        return null;
    }

    @Override
    public boolean isValid(String value) {
        return value.matches(PERCENTAGE_REGEX) || value.matches(PIXEL_REGEX) || value.matches(AUTO_REGEX);
    }

    @Override
    public Unit extract(String value) {
        if (value.matches(AUTO_REGEX)) {
            return Auto.AUTO;
        }

        return getLength(value);
    }
}