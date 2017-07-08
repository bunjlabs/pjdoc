package com.bunjlabs.pjdoc.layout.attributes;

import java.awt.Color;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class Border {

    private LineStyle lineStyle;
    private float width;
    private Color color;

    public Border() {
        this.lineStyle = LineStyle.SOLID;
        this.width = 1f;
        this.color = Color.black;
    }

    public Border(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
        this.width = 1f;
        this.color = Color.black;
    }

    public Border(float width) {
        this.lineStyle = LineStyle.SOLID;
        this.width = width;
        this.color = Color.black;
    }

    public Border(Color color) {
        this.lineStyle = LineStyle.SOLID;
        this.width = 1f;
        this.color = color;
    }

    public Border(LineStyle lineStyle, float width) {
        this.lineStyle = lineStyle;
        this.width = width;
        this.color = Color.black;
    }

    public Border(LineStyle lineStyle, float width, Color color) {
        this.lineStyle = lineStyle;
        this.width = width;
        this.color = color;
    }

    public float getWidth() {
        return width;
    }

    public LineStyle getLineStyle() {
        return lineStyle;
    }

    public Color getColor() {
        return color;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
