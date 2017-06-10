package com.bunjlabs.pjdoc.layout.attributes;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public enum Attribute {
    COLOR,
    BACKGROUND_COLOR,
    FONT_FAMILY,
    FONT_SIZE,
    FONT_WEIGHT,
    FONT_STYLE,
    LEADING,
    TEXT_ALIGN,
    HORIZONTAL_ALIGN,
    VERTICAL_ALIGN,
    WIDTH,
    HEIGHT,
    MARGIN_TOP,
    MARGIN_RIGHT,
    MARGIN_BOTTOM,
    MARGIN_LEFT,
    PADDING_TOP,
    PADDING_RIGHT,
    PADDING_BOTTOM,
    PADDING_LEFT;

    private static final boolean[] INHERITED_PROPERTIES;

    static {
        INHERITED_PROPERTIES = new boolean[Attribute.values().length];

        INHERITED_PROPERTIES[COLOR.ordinal()] = true;
        INHERITED_PROPERTIES[FONT_FAMILY.ordinal()] = true;
        INHERITED_PROPERTIES[FONT_SIZE.ordinal()] = true;
        INHERITED_PROPERTIES[FONT_WEIGHT.ordinal()] = true;
        INHERITED_PROPERTIES[FONT_STYLE.ordinal()] = true;
        INHERITED_PROPERTIES[LEADING.ordinal()] = true;
        INHERITED_PROPERTIES[TEXT_ALIGN.ordinal()] = true;
    }

    public boolean isInherited() {
        return INHERITED_PROPERTIES[this.ordinal()];
    }
}
