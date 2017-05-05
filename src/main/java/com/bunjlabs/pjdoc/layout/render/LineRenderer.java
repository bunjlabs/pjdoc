package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.elements.Text;
import java.util.Iterator;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class LineRenderer extends Renderer<Text> {

    public LineRenderer() {
        super();
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        Rectangle parentBoundingBox = layoutContext.getBoundingBox();
        Rectangle boundingBox = layoutContext.getBoundingBox();

        for (Iterator<Renderer> it = childRenderers.iterator(); it.hasNext();) {
            Renderer renderer = it.next();

            LayoutResult layoutResult = renderer.layout(new LayoutContext(layoutContext.getMediaArea(), boundingBox));

            if (layoutResult.getType() == LayoutResult.PARTIAL) {
                break;
            }

            it.remove();
        }

        if (childRenderers.isEmpty()) {
            return new LayoutResult(LayoutResult.FULL, null);
        } else {
            return new LayoutResult(LayoutResult.PARTIAL, null);
        }
    }

    @Override
    public void render(RenderContext renderContext) {
    }

    @Override
    public Renderer getNextRenderer() {
        return new LineRenderer();
    }

}
