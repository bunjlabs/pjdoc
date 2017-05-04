package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.attributes.Attribute;
import com.bunjlabs.pjdoc.utils.RectangleUtils;
import com.bunjlabs.pjdoc.layout.elements.BlockElement;
import java.util.Iterator;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class BlockRenderer<E extends BlockElement> extends Renderer<E> {

    public BlockRenderer(E modelElement) {
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

        renderChildren(renderContext, parentBoundingBox, boundingBox);

        float heigthDiff = parentBoundingBox.getHeight() - boundingBox.getHeight();

        parentBoundingBox.setUpperRightY(parentBoundingBox.getUpperRightY() - heigthDiff);

        if (childRenderers.isEmpty()) {
            return new LayoutResult(LayoutResult.FULL);
        } else {
            return new LayoutResult(LayoutResult.PARTIAL);
        }
    }

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

    protected void applyMargins(PDRectangle boundingBox) {
        float marginTop = getAttribute(Attribute.MARGIN_TOP, 0f);
        float marginRight = getAttribute(Attribute.MARGIN_RIGHT, 0f);
        float marginBottom = getAttribute(Attribute.MARGIN_BOTTOM, 0f);
        float marginLeft = getAttribute(Attribute.MARGIN_LEFT, 0f);

        boundingBox.setLowerLeftX(boundingBox.getLowerLeftX() + marginLeft);
        boundingBox.setUpperRightX(boundingBox.getUpperRightX() - marginRight);

        boundingBox.setLowerLeftY(boundingBox.getLowerLeftY() + marginBottom);
        boundingBox.setUpperRightY(boundingBox.getUpperRightY() - marginTop);
    }

    protected void applyPaddings(PDRectangle boundingBox) {
        float paddingTop = getAttribute(Attribute.PADDING_TOP, 0f);
        float paddingRight = getAttribute(Attribute.PADDING_RIGHT, 0f);
        float paddingBottom = getAttribute(Attribute.PADDING_BOTTOM, 0f);
        float paddingLeft = getAttribute(Attribute.PADDING_LEFT, 0f);

        boundingBox.setLowerLeftX(boundingBox.getLowerLeftX() + paddingLeft);
        boundingBox.setUpperRightX(boundingBox.getUpperRightX() - paddingRight);

        boundingBox.setLowerLeftY(boundingBox.getLowerLeftY() + paddingBottom);
        boundingBox.setUpperRightY(boundingBox.getUpperRightY() - paddingTop);
    }

    protected void drawBackground(RenderContext renderContext, PDRectangle parentBoundingBox) {
    }

    protected void drawBorder(RenderContext renderContext, PDRectangle parentBoundingBox) {
    }
}
