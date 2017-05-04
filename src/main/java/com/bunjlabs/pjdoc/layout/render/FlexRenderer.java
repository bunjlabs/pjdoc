package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.elements.Flex;
import com.bunjlabs.pjdoc.utils.RectangleUtils;
import java.util.Iterator;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class FlexRenderer extends BlockRenderer<Flex> {

    public FlexRenderer(Flex modelElement) {
        super(modelElement);
    }

    @Override
    public LayoutResult render(RenderContext renderContext, LayoutContext layoutContext) {
        PDRectangle parentBoundingBox = layoutContext.getBoundingBox();

        PDRectangle boundingBox = RectangleUtils.clone(parentBoundingBox);
        applyMargins(boundingBox);

        drawBackground(renderContext, boundingBox);
        drawBorder(renderContext, boundingBox);

        applyPaddings(boundingBox);

        int childrens = childRenderers.size();
        float flexWidth = boundingBox.getWidth() / childrens;

        PDRectangle[] childrenBoundingBox = new PDRectangle[childrens];

        float heigthDiff = 0;

        for (int i = 0; i < childrens; i++) {
            childrenBoundingBox[i] = RectangleUtils.clone(boundingBox);
            childrenBoundingBox[i].setLowerLeftX(childrenBoundingBox[i].getLowerLeftX() + (flexWidth * i));
            childrenBoundingBox[i].setUpperRightX(childrenBoundingBox[i].getLowerLeftX() + (flexWidth * (i + 1)));

            Renderer renderer = childRenderers.get(i);
            LayoutResult layoutResult = renderer.render(renderContext, new LayoutContext(parentBoundingBox, childrenBoundingBox[i]));

            heigthDiff = Math.max(parentBoundingBox.getHeight() - childrenBoundingBox[i].getHeight(), heigthDiff);

        }

        parentBoundingBox.setUpperRightY(parentBoundingBox.getUpperRightY() - heigthDiff);

        return new LayoutResult(LayoutResult.FULL);
    }

    @Override
    public void renderChildren(RenderContext renderContext, PDRectangle parentBoundingBox, PDRectangle boundingBox) {
        for (Iterator<Renderer> it = childRenderers.iterator(); it.hasNext();) {
            Renderer renderer = it.next();
            LayoutResult layoutResult = renderer.render(renderContext, new LayoutContext(parentBoundingBox, boundingBox));

            if (layoutResult.getType() == LayoutResult.PARTIAL) {
                break;
            }

            it.remove();
        }
    }
}
