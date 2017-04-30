package com.bunjlabs.pjdoc.layout;

import java.awt.Color;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class Style extends ElementAttributeContainer<Style> {

    public Style setFont(PDFont font) {
        setAttribute(Attribute.FONT, font);
        return this;
    }

    public Style setFontSize(float fontSize) {
        setAttribute(Attribute.FONT_SIZE, fontSize);
        return this;
    }

    public Style setColor(Color color) {
        setAttribute(Attribute.COLOR, color);
        return this;
    }

    public Style setLeading(float leading) {
        setAttribute(Attribute.LEADING, leading);
        return this;
    }
}
