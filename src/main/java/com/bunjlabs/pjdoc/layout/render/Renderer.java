package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.attributes.Attribute;
import com.bunjlabs.pjdoc.layout.attributes.IAttributeContainer;
import com.bunjlabs.pjdoc.layout.elements.Element;
import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

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

    public void render(RenderContext renderContext) {
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

    public void renderChildren(RenderContext renderContext) {
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

    protected void drawBackground(RenderContext renderContext, Rectangle boundingBox) {
        PDPageContentStream stream = renderContext.getPageContentStream(occupiedArea.getPageNumber());
        Color backgroundColor = getAttribute(Attribute.BACKGROUND_COLOR);

        if (backgroundColor == null) {
            return;
        }

        try {
            stream.setStrokingColor(backgroundColor);
            stream.setNonStrokingColor(backgroundColor);
            stream.addRect(boundingBox.getX(), boundingBox.getY(), boundingBox.getWidth(), boundingBox.getHeight());
            stream.fill();
            stream.restoreGraphicsState();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void drawBorder(RenderContext renderContext, Rectangle boundingBox) {
    }
}
