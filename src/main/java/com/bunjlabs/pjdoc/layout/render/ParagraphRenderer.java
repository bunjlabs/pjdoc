package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.elements.Paragraph;
import java.util.Iterator;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class ParagraphRenderer extends BlockRenderer<Paragraph> {

    private final LineRenderer lineRenderer;

    public ParagraphRenderer(Paragraph modelElement) {
        super(modelElement);

        lineRenderer = new LineRenderer();
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        Rectangle parentBoundingBox = layoutContext.getBoundingBox();

        Rectangle boundingBox = parentBoundingBox.clone();
        applyMargins(boundingBox);

        applyPaddings(boundingBox);

        for (Iterator<Renderer> it = childRenderers.iterator(); it.hasNext();) {
            Renderer renderer = it.next();
            if (renderer instanceof TextRenderer) {
                lineRenderer.childRenderers.add(renderer);
            }
            it.remove();
        }

        LayoutResult lineResult = lineRenderer.layout(new LayoutContext(layoutContext.getMediaArea(), boundingBox));

        while (lineResult.getType() == LayoutResult.PARTIAL) {

            lineResult = lineRenderer.layout(new LayoutContext(layoutContext.getMediaArea(), boundingBox));
        }

        parentBoundingBox.addHeight(boundingBox.getHeight());

        if (lineResult.getType() != LayoutResult.PARTIAL_OVERFLOW) {
            return new LayoutResult(LayoutResult.FULL, null);
        } else {
            return new LayoutResult(LayoutResult.PARTIAL, null);
        }
    }

    @Override
    public Renderer getNextRenderer() {
        return new ParagraphRenderer(modelElement);
    }

}
