package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.elements.Paragraph;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class ParagraphRenderer extends BlockRenderer<Paragraph> {

    private List<LineRenderer> lines = new LinkedList<>();

    public ParagraphRenderer(Paragraph modelElement) {
        super(modelElement);
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        Rectangle boundingBox = layoutContext.getBoundingBox();

        applyMargins(boundingBox);
        applyPaddings(boundingBox);

        occupiedArea = new LayoutArea(layoutContext.getMediaArea().getPageNumber(), new Rectangle(boundingBox.getLeft(), boundingBox.getTop(), boundingBox.getWidth(), 0));

        LineRenderer currentLineRenderer = new LineRenderer(modelElement);

        for (Iterator<Renderer> it = childRenderers.iterator(); it.hasNext();) {
            Renderer renderer = it.next();
            if (renderer instanceof TextRenderer) {
                currentLineRenderer.childRenderers.add(renderer);
            }
            it.remove();
        }

        Rectangle layoutBox = boundingBox.clone();

        while (currentLineRenderer != null) {
            LayoutResult lineResult = currentLineRenderer.layout(new LayoutContext(layoutContext.getMediaArea(), layoutBox));

            occupiedArea.setBoundingBox(Rectangle.getCommonRectangle(occupiedArea.getBoundingBox(), lineResult.getOccupiedArea().getBoundingBox()));
            layoutBox.setHeight(lineResult.getOccupiedArea().getBoundingBox().getY() - layoutBox.getY());

            lines.add(currentLineRenderer);
            currentLineRenderer = lineResult.getSplitRenderers().length > 1 ? (LineRenderer) lineResult.getSplitRenderers()[1] : null;
        }

        removePaddings(occupiedArea.getBoundingBox());

        return new LayoutResult(LayoutResult.FULL, occupiedArea);
    }

    @Override
    public void renderChildren(RenderContext renderContext) {
        for (Renderer renderer : lines) {
            renderer.render(renderContext);
        }
    }

    @Override
    public Renderer getNextRenderer() {
        return new ParagraphRenderer(modelElement);
    }

}
