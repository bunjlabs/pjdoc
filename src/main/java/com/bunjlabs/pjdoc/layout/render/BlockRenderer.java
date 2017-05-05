package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.attributes.Attribute;
import com.bunjlabs.pjdoc.layout.elements.BlockElement;
import java.awt.Color;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public abstract class BlockRenderer<E extends BlockElement> extends Renderer<E> {

    public BlockRenderer(E modelElement) {
        super(modelElement);
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        Rectangle parentBoundingBox = layoutContext.getBoundingBox();

        applyMargins(parentBoundingBox);
        applyPaddings(parentBoundingBox);

        occupiedArea = new LayoutArea(0, new Rectangle(parentBoundingBox.getLeft(), parentBoundingBox.getTop(), parentBoundingBox.getWidth(), 0));

        Rectangle layoutBox = parentBoundingBox.clone();

        int processedChildRenderers = 0;

        for (Renderer renderer : childRenderers) {

            LayoutResult layoutResult;
            while ((layoutResult = renderer.layout(new LayoutContext(layoutContext.getMediaArea(), layoutBox))).getType() != LayoutResult.FULL) {
                if (layoutResult.getType() == LayoutResult.PARTIAL) {
                    Renderer leftRenderer = createLeftRenderer();
                    leftRenderer.childRenderers.addAll(childRenderers.subList(0, processedChildRenderers));
                    leftRenderer.childRenderers.addAll(layoutResult.getSplitRenderers()[0].childRenderers);

                    Renderer rightRenderer = createRightRenderer();
                    rightRenderer.childRenderers.addAll(layoutResult.getSplitRenderers()[1].childRenderers);
                    rightRenderer.childRenderers.addAll(childRenderers.subList(processedChildRenderers, childRenderers.size()));

                    removePaddings(occupiedArea.getBoundingBox());
                    
                    return new LayoutResult(LayoutResult.PARTIAL, occupiedArea, leftRenderer, rightRenderer);
                }
            }

            occupiedArea.setBoundingBox(Rectangle.getCommonRectangle(occupiedArea.getBoundingBox(), layoutResult.getOccupiedArea().getBoundingBox()));

            if (layoutResult.getType() == LayoutResult.FULL) {
                layoutBox.setHeight(layoutResult.getOccupiedArea().getBoundingBox().getY() - layoutBox.getY());
            }

            processedChildRenderers++;
        }

        removePaddings(occupiedArea.getBoundingBox());
        //removeMargins(occupiedArea.getBoundingBox());

        return new LayoutResult(LayoutResult.FULL, occupiedArea);

    }

    @Override
    public void render(RenderContext renderContext) {
        Rectangle boundingBox = occupiedArea.getBoundingBox();

        drawBackground(renderContext, boundingBox);
        drawBorder(renderContext, boundingBox);

        applyPaddings(boundingBox);

        for (Renderer renderer : childRenderers) {
            renderer.render(renderContext);
        }

    }

    protected Renderer createLeftRenderer() {
        Renderer renderer = getNextRenderer();
        renderer.modelElement = modelElement;
        renderer.occupiedArea = occupiedArea;
        return renderer;
    }

    protected Renderer createRightRenderer() {
        Renderer renderer = getNextRenderer();
        renderer.modelElement = modelElement;
        return renderer;
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
