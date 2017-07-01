package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.PjContentStream;
import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.attributes.Attribute;
import com.bunjlabs.pjdoc.layout.attributes.Border;
import com.bunjlabs.pjdoc.layout.attributes.IAttributeContainer;
import com.bunjlabs.pjdoc.layout.elements.Element;
import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public abstract class Renderer<E extends Element> implements IAttributeContainer {

    protected final List<Renderer> childRenderers = new LinkedList<>();
    protected E modelElement;
    protected LayoutArea occupiedArea;

    public Renderer() {
        this.modelElement = null;
    }

    protected Renderer(E modelElement) {
        this.modelElement = modelElement;
    }

    public void add(Renderer renderer) {
        childRenderers.add(renderer);
    }

    public abstract LayoutResult layout(LayoutContext layoutContext);

    public void render(RenderContext renderContext) throws IOException {
        if (occupiedArea == null) {
            return;
        }
        Rectangle boundingBox = occupiedArea.getBoundingBox();

        applyMargins(boundingBox);

        drawBackground(renderContext, boundingBox);
        drawBorder(renderContext, boundingBox);

        applyPaddings(boundingBox);

        renderChildren(renderContext);

    }

    public void renderChildren(RenderContext renderContext) throws IOException {
        for (Renderer renderer : childRenderers) {
            renderer.render(renderContext);
        }
    }

    public abstract Renderer getNextRenderer();

    @Override
    public void deleteAttribute(Attribute attr) {
        modelElement.deleteAttribute(attr);
    }

    @Override
    public <T1> T1 getAttribute(Attribute attr) {
        return (T1) modelElement.<T1>getAttribute(attr);
    }

    @Override
    public <T1> T1 getAttribute(Attribute attr, T1 defaultValue) {
        return (T1) modelElement.<T1>getAttribute(attr, defaultValue);
    }

    @Override
    public boolean hasAttribute(Attribute attr) {
        return modelElement.hasAttribute(attr);
    }

    @Override
    public void setAttribute(Attribute attr, Object value) {
        modelElement.setAttribute(attr, value);
    }

    protected void applyMargins(Rectangle boundingBox) {
        float marginTop = getAttribute(Attribute.MARGIN_TOP, 0f);
        float marginRight = getAttribute(Attribute.MARGIN_RIGHT, 0f);
        float marginBottom = getAttribute(Attribute.MARGIN_BOTTOM, 0f);
        float marginLeft = getAttribute(Attribute.MARGIN_LEFT, 0f);

        boundingBox.applyIndents(marginTop, marginRight, marginBottom, marginLeft);
    }

    protected void applyPaddings(Rectangle boundingBox) {
        float paddingTop = getAttribute(Attribute.PADDING_TOP, 0f);
        float paddingRight = getAttribute(Attribute.PADDING_RIGHT, 0f);
        float paddingBottom = getAttribute(Attribute.PADDING_BOTTOM, 0f);
        float paddingLeft = getAttribute(Attribute.PADDING_LEFT, 0f);

        boundingBox.applyIndents(paddingTop, paddingRight, paddingBottom, paddingLeft);
    }

    protected void applyBorderBox(Rectangle boundingBox) {
        Border[] borders = getBorders();
        float topWidth = borders[0] != null ? borders[0].getWidth() : 0;
        float rightWidth = borders[1] != null ? borders[1].getWidth() : 0;
        float bottomWidth = borders[2] != null ? borders[2].getWidth() : 0;
        float leftWidth = borders[3] != null ? borders[3].getWidth() : 0;

        boundingBox.applyIndents(topWidth / 2f, rightWidth / 2f, bottomWidth / 2f, leftWidth / 2f);
    }

    protected void removeMargins(Rectangle boundingBox) {
        float marginTop = getAttribute(Attribute.MARGIN_TOP, 0f);
        float marginRight = getAttribute(Attribute.MARGIN_RIGHT, 0f);
        float marginBottom = getAttribute(Attribute.MARGIN_BOTTOM, 0f);
        float marginLeft = getAttribute(Attribute.MARGIN_LEFT, 0f);

        boundingBox.applyIndents(-marginTop, -marginRight, -marginBottom, -marginLeft);
    }

    protected void removePaddings(Rectangle boundingBox) {
        float paddingTop = getAttribute(Attribute.PADDING_TOP, 0f);
        float paddingRight = getAttribute(Attribute.PADDING_RIGHT, 0f);
        float paddingBottom = getAttribute(Attribute.PADDING_BOTTOM, 0f);
        float paddingLeft = getAttribute(Attribute.PADDING_LEFT, 0f);

        boundingBox.applyIndents(-paddingTop, -paddingRight, -paddingBottom, -paddingLeft);
    }

    protected void drawBackground(RenderContext renderContext, Rectangle boundingBox) throws IOException {
        PjContentStream stream = renderContext.getPageContentStream(occupiedArea.getPageNumber());
        Color backgroundColor = getAttribute(Attribute.BACKGROUND_COLOR);

        if (backgroundColor == null) {
            return;
        }

        stream.drawRectFilled(backgroundColor, boundingBox);

    }

    protected void drawBorder(RenderContext renderContext, Rectangle boundingBox) throws IOException {
        PjContentStream stream = renderContext.getPageContentStream(occupiedArea.getPageNumber());
        Border[] borders = getBorders();

        Rectangle borderBox = occupiedArea.getBoundingBox().clone();
        applyBorderBox(borderBox);

        float x1 = borderBox.getX();
        float y1 = borderBox.getY();
        float x2 = borderBox.getX() + borderBox.getWidth();
        float y2 = borderBox.getY() + borderBox.getHeight();

        if (borders[0] != null) {
            stream.drawLineStroked(borders[0].getColor(), borders[0].getLineStyle(), x1, y2, x2, y2, borders[0].getWidth());
        }
        if (borders[1] != null) {
            stream.drawLineStroked(borders[0].getColor(), borders[0].getLineStyle(), x2, y2, x2, y1, borders[0].getWidth());
        }
        if (borders[2] != null) {
            stream.drawLineStroked(borders[0].getColor(), borders[0].getLineStyle(), x1, y1, x2, y1, borders[0].getWidth());
        }
        if (borders[3] != null) {
            stream.drawLineStroked(borders[0].getColor(), borders[0].getLineStyle(), x1, y1, x1, y2, borders[0].getWidth());
        }
    }

    protected Border[] getBorders() {
        Border topBorder = getAttribute(Attribute.BORDER_TOP, null);
        Border rightBorder = getAttribute(Attribute.BORDER_RIGHT, null);
        Border bottomBorder = getAttribute(Attribute.BORDER_BOTTOM, null);
        Border leftBorder = getAttribute(Attribute.BORDER_LEFT, null);

        return new Border[]{topBorder, rightBorder, bottomBorder, leftBorder};
    }
}
