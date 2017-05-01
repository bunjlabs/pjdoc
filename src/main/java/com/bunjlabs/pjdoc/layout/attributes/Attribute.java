package com.bunjlabs.pjdoc.layout.attributes;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public enum Attribute {
    COLOR,
    FONT,
    FONT_SIZE,
    LEADING,
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
        INHERITED_PROPERTIES[FONT.ordinal()] = true;
        INHERITED_PROPERTIES[FONT_SIZE.ordinal()] = true;
        INHERITED_PROPERTIES[LEADING.ordinal()] = true;
    }

    public boolean isInherited() {
        return INHERITED_PROPERTIES[this.ordinal()];
    }
}
