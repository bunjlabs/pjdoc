package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.elements.Paragraph;
import java.util.Iterator;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class LineRenderer extends Renderer<Paragraph> {

    protected float maxHeight = 0;

    public LineRenderer(Paragraph modelElement) {
        super(modelElement);
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        Rectangle boundingBox = layoutContext.getBoundingBox();

        occupiedArea = new LayoutArea(
                layoutContext.getMediaArea().getPageNumber(),
                new Rectangle(boundingBox.getLeft(), boundingBox.getTop(), boundingBox.getWidth(), 0));

        maxHeight = 0;
        int currentChild = 0;
        float currentXOffset = 0;

        for (Iterator<Renderer> it = childRenderers.iterator(); it.hasNext(); currentChild++) {
            Renderer renderer = it.next();

            Rectangle linebox = new Rectangle(
                    boundingBox.getX() + currentXOffset,
                    boundingBox.getY(),
                    boundingBox.getWidth() - currentXOffset,
                    boundingBox.getHeight());

            if (!(renderer instanceof TextRenderer)) {
                continue;
            }

            LayoutResult layoutResult = renderer.layout(new LayoutContext(layoutContext.getMediaArea(), linebox));

            currentXOffset += layoutResult.getOccupiedArea().getBoundingBox().getWidth();
            maxHeight = Math.max(maxHeight, layoutResult.getOccupiedArea().getBoundingBox().getHeight());

            occupiedArea.setBoundingBox(new Rectangle(
                    boundingBox.getLeft(),
                    boundingBox.getTop() - maxHeight,
                    currentXOffset,
                    maxHeight));

            // should break line
            if (layoutResult.getType() == LayoutResult.PARTIAL_OVERFLOW) {
                Renderer leftRenderer = createLeftRenderer();
                leftRenderer.childRenderers.addAll(childRenderers.subList(0, currentChild));
                leftRenderer.childRenderers.add(layoutResult.getSplitRenderers()[0]);

                Renderer rightRenderer = createRightRenderer();
                rightRenderer.childRenderers.add(layoutResult.getSplitRenderers()[1]);
                rightRenderer.childRenderers.addAll(childRenderers.subList(currentChild + 1, childRenderers.size()));

                return new LayoutResult(LayoutResult.PARTIAL, occupiedArea, leftRenderer, rightRenderer);
            }
        }

        // TODO: remove kostyl'
        occupiedArea.getBoundingBox().addBottom(maxHeight / 2);

        return new LayoutResult(LayoutResult.FULL, occupiedArea);

    }

    protected Renderer createLeftRenderer() {
        LineRenderer renderer = (LineRenderer) getNextRenderer();
        renderer.occupiedArea = occupiedArea;
        renderer.maxHeight = maxHeight;
        return renderer;
    }

    protected Renderer createRightRenderer() {
        LineRenderer renderer = (LineRenderer) getNextRenderer();
        return renderer;
    }

    @Override
    public Renderer getNextRenderer() {
        return new LineRenderer(modelElement);
    }

}
