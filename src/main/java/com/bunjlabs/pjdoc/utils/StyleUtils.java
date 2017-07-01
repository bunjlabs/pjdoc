package com.bunjlabs.pjdoc.utils;

import com.bunjlabs.pjdoc.layout.attributes.Border;
import com.bunjlabs.pjdoc.layout.attributes.LineStyle;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class StyleUtils {

    private static final Pattern UNIT_PATTERN = Pattern.compile("(\\d+(\\.\\d+)?)([A-Za-z]*)");

    public static float unit(String value) {
        float unitValue = 0.0F;
        Matcher matcher = UNIT_PATTERN.matcher(value);
        if (matcher.matches()) {
            float v = Float.parseFloat(matcher.group(1));
            String t = matcher.group(3).toLowerCase();
            if ("mm".equals(t)) {
                unitValue = UnitUtils.mm(v);
            } else if ("cm".equals(t)) {
                unitValue = UnitUtils.cm(v);
            } else {
                unitValue = v;
            }
        }
        return unitValue;
    }

    public static float[] unitArray(String value) {
        String[] values = value.split(" ");
        float[] unitValues = new float[values.length];
        for (int i = 0; i < unitValues.length; i++) {
            unitValues[i] = unit(values[i]);
        }
        return unitValues;
    }

    public static float[] unitArrayIndent(String value) {
        float[] uv = unitArray(value);
        switch (uv.length) {
            case 4:
                return uv;
            case 3:
                return new float[]{uv[0], uv[1], uv[2], uv[1]};
            case 2:
                return new float[]{uv[0], uv[1], uv[0], uv[1]};
            case 1:
                return new float[]{uv[0], uv[0], uv[0], uv[0]};
            default:
                return new float[]{0, 0, 0, 0};
        }
    }

    public static Border border(String value) {
        String[] split = value.split(" ");

        Border border = new Border();
        if (split.length >= 1) {
            border.setWidth(unit(split[0]));
        }
        if (split.length >= 2) {
            border.setLineStyle(LineStyle.valueOf(split[1].toUpperCase()));
        }
        if (split.length >= 3) {
            border.setColor(Color.decode(split[2]));
        }
        return border;
    }
}
