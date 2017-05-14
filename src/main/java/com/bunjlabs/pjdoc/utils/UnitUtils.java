package com.bunjlabs.pjdoc.utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class UnitUtils {

    private static final String PANGRAM_STR = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    private static final Pattern UNIT_PATTERN = Pattern.compile("(\\d+(\\.\\d+)?)([A-Za-z]*)");

    public static float mm(float mm) {
        return (mm / 25.4f) * 72f;
    }

    public static float cm(float cm) {
        return (cm / 2.54f) * 72f;
    }

    public static float unit(String value) {
        float unitValue = 0f;

        Matcher matcher = UNIT_PATTERN.matcher(value);

        if (matcher.matches()) {
            float v = Float.parseFloat(matcher.group(1));
            String t = matcher.group(3).toLowerCase();

            if ("mm".equals(t)) {
                unitValue = mm(v);
            } else if ("cm".equals(t)) {
                unitValue = cm(v);
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

    public static String pangram() {
        return PANGRAM_STR.substring(0, PANGRAM_STR.length());
    }

    public static String pangram(int length) {
        return PANGRAM_STR.substring(0, length);
    }

    public static float lineWidth(String line, PDFont font, float fontSize) throws IOException {
        return fontSize * font.getStringWidth(line) / 1000f;
    }
}
