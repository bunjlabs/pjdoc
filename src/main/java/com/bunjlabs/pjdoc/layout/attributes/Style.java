package com.bunjlabs.pjdoc.layout.attributes;

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

    public Style setMarginTop(float marginTop) {
        setAttribute(Attribute.MARGIN_TOP, marginTop);
        return this;
    }

    public Style setMarginRight(float marginRight) {
        setAttribute(Attribute.MARGIN_RIGHT, marginRight);
        return this;
    }

    public Style setMarginBottom(float marginBottom) {
        setAttribute(Attribute.MARGIN_BOTTOM, marginBottom);
        return this;
    }

    public Style setMarginLeft(float marginLeft) {
        setAttribute(Attribute.MARGIN_LEFT, marginLeft);
        return this;
    }

    public Style setMargin(float marginTop, float marginRight, float marginBottom, float marginLeft) {
        setAttribute(Attribute.MARGIN_TOP, marginTop);
        setAttribute(Attribute.MARGIN_RIGHT, marginRight);
        setAttribute(Attribute.MARGIN_BOTTOM, marginBottom);
        setAttribute(Attribute.MARGIN_LEFT, marginLeft);
        return this;
    }

    public Style setMargin(float margin) {
        setAttribute(Attribute.MARGIN_TOP, margin);
        setAttribute(Attribute.MARGIN_RIGHT, margin);
        setAttribute(Attribute.MARGIN_BOTTOM, margin);
        setAttribute(Attribute.MARGIN_LEFT, margin);
        return this;
    }

    public Style setPaddingTop(float paddingTop) {
        setAttribute(Attribute.PADDING_TOP, paddingTop);
        return this;
    }

    public Style setPaddingRight(float paddingRight) {
        setAttribute(Attribute.PADDING_RIGHT, paddingRight);
        return this;
    }

    public Style setPaddingBottom(float paddingBottom) {
        setAttribute(Attribute.PADDING_BOTTOM, paddingBottom);
        return this;
    }

    public Style setPaddingLeft(float paddingLeft) {
        setAttribute(Attribute.PADDING_LEFT, paddingLeft);
        return this;
    }

    public Style setPadding(float paddingTop, float paddingRight, float paddingBottom, float paddingLeft) {
        setAttribute(Attribute.PADDING_TOP, paddingTop);
        setAttribute(Attribute.PADDING_RIGHT, paddingRight);
        setAttribute(Attribute.PADDING_BOTTOM, paddingBottom);
        setAttribute(Attribute.PADDING_LEFT, paddingLeft);
        return this;
    }

    public Style setPadding(float padding) {
        setAttribute(Attribute.PADDING_TOP, padding);
        setAttribute(Attribute.PADDING_RIGHT, padding);
        setAttribute(Attribute.PADDING_BOTTOM, padding);
        setAttribute(Attribute.PADDING_LEFT, padding);
        return this;
    }
}
