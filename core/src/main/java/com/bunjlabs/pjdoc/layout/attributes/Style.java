package com.bunjlabs.pjdoc.layout.attributes;

import com.bunjlabs.pjdoc.font.FontFamily;
import com.bunjlabs.pjdoc.font.FontStyle;
import com.bunjlabs.pjdoc.font.FontWeight;
import java.awt.Color;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class Style extends ElementAttributeContainer<Style> {

    public Style setBorder(Border border) {
        setAttribute(Attribute.BORDER_TOP, border);
        setAttribute(Attribute.BORDER_RIGHT, border);
        setAttribute(Attribute.BORDER_BOTTOM, border);
        setAttribute(Attribute.BORDER_LEFT, border);
        return this;
    }

    public Style setBorder(Border topBottom, Border leftRight) {
        setAttribute(Attribute.BORDER_TOP, topBottom);
        setAttribute(Attribute.BORDER_RIGHT, leftRight);
        setAttribute(Attribute.BORDER_BOTTOM, topBottom);
        setAttribute(Attribute.BORDER_LEFT, leftRight);
        return this;
    }

    public Style setBorder(Border top, Border right, Border bottom, Border left) {
        setAttribute(Attribute.BORDER_TOP, top);
        setAttribute(Attribute.BORDER_RIGHT, right);
        setAttribute(Attribute.BORDER_BOTTOM, bottom);
        setAttribute(Attribute.BORDER_LEFT, left);
        return this;
    }

    public Style setBorderTop(Border border) {
        setAttribute(Attribute.BORDER_TOP, border);
        return this;
    }

    public Style setBorderRight(Border border) {
        setAttribute(Attribute.BORDER_RIGHT, border);
        return this;
    }

    public Style setBorderBottom(Border border) {
        setAttribute(Attribute.BORDER_BOTTOM, border);
        return this;
    }

    public Style setBorderLeft(Border border) {
        setAttribute(Attribute.BORDER_LEFT, border);
        return this;
    }

    public Style setFont(FontFamily fontFamily) {
        setAttribute(Attribute.FONT_FAMILY, fontFamily);
        return this;
    }

    public Style setFontSize(float fontSize) {
        setAttribute(Attribute.FONT_SIZE, fontSize);
        return this;
    }

    public Style setFontStyle(FontStyle fontStyle) {
        setAttribute(Attribute.FONT_STYLE, fontStyle);
        return this;
    }

    public Style setFontWeight(FontWeight fontWeight) {
        setAttribute(Attribute.FONT_WEIGHT, fontWeight);
        return this;
    }

    public Style setColor(Color color) {
        setAttribute(Attribute.COLOR, color);
        return this;
    }

    public Style setBackgroundColor(Color color) {
        setAttribute(Attribute.BACKGROUND_COLOR, color);
        return this;
    }

    public Style setLeading(float leading) {
        setAttribute(Attribute.LEADING, leading);
        return this;
    }

    public Style setTextAlign(TextAlign textAlign) {
        setAttribute(Attribute.TEXT_ALIGN, textAlign);
        return this;
    }

    public Style setHorizontalAlign(HorizontalAlign horizontalAlign) {
        setAttribute(Attribute.HORIZONTAL_ALIGN, horizontalAlign);
        return this;
    }

    public Style setVerticalAlign(VerticalAlign verticalAlign) {
        setAttribute(Attribute.VERTICAL_ALIGN, verticalAlign);
        return this;
    }

    public Style setWidth(float width) {
        setAttribute(Attribute.WIDTH, width);
        return this;
    }

    public Style setHeight(float height) {
        setAttribute(Attribute.HEIGHT, height);
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

    public Style setMargin(float marginTopBottom, float marginLeftRight) {
        setAttribute(Attribute.MARGIN_TOP, marginTopBottom);
        setAttribute(Attribute.MARGIN_RIGHT, marginLeftRight);
        setAttribute(Attribute.MARGIN_BOTTOM, marginTopBottom);
        setAttribute(Attribute.MARGIN_LEFT, marginLeftRight);
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

    public Style setPadding(float paddingTopBottom, float paddingLeftRight) {
        setAttribute(Attribute.PADDING_TOP, paddingTopBottom);
        setAttribute(Attribute.PADDING_RIGHT, paddingLeftRight);
        setAttribute(Attribute.PADDING_BOTTOM, paddingTopBottom);
        setAttribute(Attribute.PADDING_LEFT, paddingLeftRight);
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
