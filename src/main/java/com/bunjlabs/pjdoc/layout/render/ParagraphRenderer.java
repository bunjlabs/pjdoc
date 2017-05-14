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
            LayoutResult lineResult = currentLineRenderer.layout(layoutContext.extend(layoutBox));

            if (lineResult.getType() == LayoutResult.NOTHING) {
                ParagraphRenderer leftRenderer = createLeftRenderer();
                leftRenderer.lines.addAll(lines);

                ParagraphRenderer rightRenderer = createRightRenderer();
                rightRenderer.childRenderers.addAll(currentLineRenderer.childRenderers);

                return new LayoutResult(LayoutResult.PARTIAL, occupiedArea, leftRenderer, rightRenderer);
            }

            float lineHight = lineResult.getOccupiedArea().getBoundingBox().getHeight();
            occupiedArea.getBoundingBox().addBottom(lineHight);
            layoutBox.addHeight(-lineHight);

            lines.add(currentLineRenderer);
            currentLineRenderer = lineResult.getSplitRenderers().length > 1 ? (LineRenderer) lineResult.getSplitRenderers()[1] : null;
        }

        removePaddings(occupiedArea.getBoundingBox());
        removeMargins(occupiedArea.getBoundingBox());

        return new LayoutResult(LayoutResult.FULL, occupiedArea);
    }

    @Override
    public void renderChildren(RenderContext renderContext) {
        for (Renderer renderer : lines) {
            renderer.render(renderContext);
        }
    }

    protected ParagraphRenderer createLeftRenderer() {
        ParagraphRenderer renderer = (ParagraphRenderer) getNextRenderer();
        renderer.modelElement = modelElement;
        renderer.occupiedArea = occupiedArea;
        return renderer;
    }

    protected ParagraphRenderer createRightRenderer() {
        ParagraphRenderer renderer = (ParagraphRenderer) getNextRenderer();
        renderer.modelElement = modelElement;
        return renderer;
    }

    @Override
    public Renderer getNextRenderer() {
        return new ParagraphRenderer(modelElement);
    }

}
