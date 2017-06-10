package com.bunjlabs.pjdoc.layout;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class Rectangle {

    private float x;
    private float y;
    private float width;
    private float height;

    public Rectangle() {
    }

    public Rectangle(PDRectangle other) {
        this.x = other.getLowerLeftX();
        this.y = other.getLowerLeftY();
        this.width = other.getWidth();
        this.height = other.getHeight();
    }

    public Rectangle(Rectangle other) {
        this.x = other.x;
        this.y = other.y;
        this.width = other.width;
        this.height = other.height;
    }

    public Rectangle(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle moveX(float move) {
        x += move;
        return this;
    }

    public Rectangle moveY(float move) {
        y += move;
        return this;
    }

    public float getLeft() {
        return x;
    }

    public Rectangle setLeft(float x) {
        this.x = x;
        return this;
    }

    public Rectangle addLeft(float x) {
        this.x -= x;
        this.width += x;
        return this;
    }

    public float getBottom() {
        return y;
    }

    public Rectangle setBottom(float y) {
        this.y = y;
        return this;
    }

    public Rectangle addBottom(float y) {
        this.y -= y;
        this.height += y;
        return this;
    }

    public float getRight() {
        return x + width;
    }

    public Rectangle setRight(float x) {
        this.width = x - this.x;
        return this;
    }

    public Rectangle addRight(float x) {
        this.width += x;
        return this;
    }

    public float getTop() {
        return y + height;
    }

    public Rectangle setTop(float y) {
        this.height = y - this.y;
        return this;
    }

    public Rectangle addTop(float y) {
        this.height += y;
        return this;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public Rectangle setHeight(float height) {
        this.height = height;
        return this;
    }

    public Rectangle setWidth(float width) {
        this.width = width;
        return this;
    }

    public Rectangle addHeight(float height) {
        this.height += height;
        return this;
    }

    public Rectangle addWidth(float width) {
        this.width += width;
        return this;
    }

    public Rectangle copyHorizontalComponents(Rectangle rectangle) {
        this.x = rectangle.x;
        this.width = rectangle.width;
        return this;
    }

    public Rectangle copyVerticalComponents(Rectangle rectangle) {
        this.y = rectangle.y;
        this.height = rectangle.height;
        return this;
    }

    public Rectangle applyIndents(float top, float right, float bottom, float left) {
        x += left;
        y += bottom;
        width -= left + right;
        height -= top + bottom;
        return this;
    }

    public PDRectangle toPdRectangle() {
        return new PDRectangle(x, y, getWidth(), getHeight());
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this);
    }

    public Rectangle commonHorizontalRectangle(Rectangle... rectangles) {
        float olx = this.x;
        float orx = this.x + this.width;

        for (Rectangle rec : rectangles) {
            if (rec == null) {
                continue;
            }

            if (rec.getX() < olx) {
                olx = rec.getX();
            }
            if (rec.getX() + rec.getWidth() > orx) {
                orx = rec.getX() + rec.getWidth();
            }
        }

        this.x = olx;
        this.width = orx - olx;

        return this;
    }

    public Rectangle commonVerticalRectangle(Rectangle... rectangles) {
        float oly = this.y;
        float ory = this.y + this.height;

        for (Rectangle rec : rectangles) {
            if (rec == null) {
                continue;
            }

            if (rec.getY() < oly) {
                oly = rec.getY();
            }
            if (rec.getY() + rec.getHeight() > ory) {
                ory = rec.getY() + rec.getHeight();
            }
        }

        this.y = oly;
        this.height = ory - oly;

        return this;
    }
}
