package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.attributes.Attribute;
import com.bunjlabs.pjdoc.layout.attributes.HorizontalAlign;
import com.bunjlabs.pjdoc.layout.attributes.VerticalAlign;
import com.bunjlabs.pjdoc.layout.elements.BlockElement;
import java.util.Iterator;

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
        Rectangle boundingBox = layoutContext.getBoundingBox();

        applyMargins(boundingBox);
        applyPaddings(boundingBox);

        occupiedArea = new LayoutArea(layoutContext.getMediaArea().getPageNumber(), new Rectangle(boundingBox.getLeft(), boundingBox.getTop(), boundingBox.getWidth(), 0));

        Rectangle layoutBox = boundingBox.clone();

        float fullWidth = layoutBox.getWidth();
        float fullHeigth = layoutBox.getHeight();

        if (hasAttribute(Attribute.WIDTH)) {
            float fixedWidth = getAttribute(Attribute.WIDTH);
            float freeWidth = fullWidth - fixedWidth;

            HorizontalAlign horizontalAlign = getAttribute(Attribute.HORIZONTAL_ALIGN, HorizontalAlign.LEFT);

            layoutBox.setWidth(fixedWidth);

            if (horizontalAlign == HorizontalAlign.CENTER) {
                layoutBox.moveX(freeWidth / 2);
            } else if (horizontalAlign == HorizontalAlign.RIGHT) {
                layoutBox.moveX(freeWidth);
            }

            occupiedArea.getBoundingBox().copyHorizontalComponents(layoutBox);
        }

        if (hasAttribute(Attribute.HEIGHT)) {
            float fixedHeight = getAttribute(Attribute.HEIGHT);
            float freeHeight = fullHeigth - fixedHeight;

            VerticalAlign verticalAlign = getAttribute(Attribute.VERTICAL_ALIGN, VerticalAlign.TOP);

            layoutBox.addBottom(-freeHeight);

            if (verticalAlign == VerticalAlign.MIDDLE) {
                layoutBox.moveY(-freeHeight / 2);
            } else if (verticalAlign == VerticalAlign.BOTTOM) {
                layoutBox.moveY(-freeHeight);
            }

            occupiedArea.getBoundingBox().copyVerticalComponents(layoutBox);
        }

        int currentChild = 0;

        for (Iterator<Renderer> it = childRenderers.iterator(); it.hasNext(); currentChild++) {
            Renderer renderer = it.next();

            LayoutResult layoutResult;
            while ((layoutResult = renderer.layout(layoutContext.extend(layoutBox))).getType() != LayoutResult.FULL) {
                if (layoutResult.getType() == LayoutResult.PARTIAL) {
                    Renderer leftRenderer = createLeftRenderer();
                    leftRenderer.childRenderers.addAll(childRenderers.subList(0, currentChild));
                    leftRenderer.childRenderers.add(layoutResult.getSplitRenderers()[0]);

                    Renderer rightRenderer = createRightRenderer();
                    rightRenderer.childRenderers.add(layoutResult.getSplitRenderers()[1]);
                    rightRenderer.childRenderers.addAll(childRenderers.subList(currentChild + 1, childRenderers.size()));

                    removePaddings(occupiedArea.getBoundingBox());

                    return new LayoutResult(LayoutResult.PARTIAL, occupiedArea, leftRenderer, rightRenderer);
                }
            }

            if (!hasAttribute(Attribute.WIDTH)) {
                occupiedArea.getBoundingBox().commonHorizontalRectangle(layoutResult.getOccupiedArea().getBoundingBox());
            }

            if (!hasAttribute(Attribute.HEIGHT)) {
                occupiedArea.getBoundingBox().commonVerticalRectangle(layoutResult.getOccupiedArea().getBoundingBox());
            }

            if (layoutResult.getType() == LayoutResult.FULL) {
                layoutBox.addHeight(-layoutResult.getOccupiedArea().getBoundingBox().getHeight());
            }
        }

        removePaddings(occupiedArea.getBoundingBox());
        removeMargins(occupiedArea.getBoundingBox());

        return new LayoutResult(LayoutResult.FULL, occupiedArea);

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

}
