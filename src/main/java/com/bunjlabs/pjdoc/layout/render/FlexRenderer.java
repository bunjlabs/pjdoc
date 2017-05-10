package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.elements.Flex;
import java.util.Iterator;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class FlexRenderer extends BlockRenderer<Flex> {

    public FlexRenderer(Flex modelElement) {
        super(modelElement);
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        Rectangle boundingBox = layoutContext.getBoundingBox();

        applyMargins(boundingBox);
        applyPaddings(boundingBox);

        occupiedArea = new LayoutArea(layoutContext.getMediaArea().getPageNumber(), new Rectangle(boundingBox.getLeft(), boundingBox.getTop(), boundingBox.getWidth(), 0));

        Rectangle layoutBox = boundingBox.clone();

        float itemWidth = layoutBox.getWidth() / childRenderers.size();
        float finalHeigth = 0;

        Rectangle itemLayoutBox = layoutBox.clone();
        itemLayoutBox.setWidth(itemWidth);

        for (Iterator<Renderer> it = childRenderers.iterator(); it.hasNext();) {
            Renderer renderer = it.next();

            LayoutResult layoutResult = renderer.layout(new LayoutContext(layoutContext.getMediaArea(), itemLayoutBox));
            finalHeigth = Math.max(finalHeigth, layoutResult.getOccupiedArea().getBoundingBox().getHeight());

            occupiedArea.setBoundingBox(Rectangle.getCommonRectangle(occupiedArea.getBoundingBox(), layoutResult.getOccupiedArea().getBoundingBox()));

            itemLayoutBox.moveX(itemWidth);
        }

        removePaddings(occupiedArea.getBoundingBox());
        //removeMargins(occupiedArea.getBoundingBox());

        return new LayoutResult(LayoutResult.FULL, occupiedArea);

    }

    @Override
    public Renderer getNextRenderer() {
        return new FlexRenderer(modelElement);
    }

}
