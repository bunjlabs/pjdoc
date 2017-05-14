package com.bunjlabs.pjdoc.font;

import java.util.Objects;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class FontDescriptor {

    private final FontFamily fontFamily;
    private final FontStyle fontStyle;
    private final FontWeight fontWeight;

    public FontDescriptor(FontFamily fontFamily, FontStyle fontStyle, FontWeight fontWeight) {
        this.fontFamily = fontFamily;
        this.fontStyle = fontStyle;
        this.fontWeight = fontWeight;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.fontFamily);
        hash = 13 * hash + Objects.hashCode(this.fontStyle);
        hash = 13 * hash + Objects.hashCode(this.fontWeight.getWeight());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FontDescriptor other = (FontDescriptor) obj;
        if (!Objects.equals(this.fontFamily, other.fontFamily)) {
            return false;
        }
        if (this.fontStyle != other.fontStyle) {
            return false;
        }
        if (this.fontWeight.getWeight() != other.fontWeight.getWeight()) {
            return false;
        }
        return true;
    }

}
