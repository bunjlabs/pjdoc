package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.attributes.Attribute;
import com.bunjlabs.pjdoc.layout.attributes.VerticalAlign;
import com.bunjlabs.pjdoc.layout.elements.TableData;
import java.util.Iterator;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class TableDataRenderer extends BlockRenderer<TableData> {

    public TableDataRenderer(TableData modelElement) {
        super(modelElement);
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        Rectangle boundingBox = layoutContext.getBoundingBox();

        applyMargins(boundingBox);
        applyPaddings(boundingBox);

        occupiedArea = new LayoutArea(layoutContext.getMediaArea().getPageNumber(), new Rectangle(boundingBox.getLeft(), boundingBox.getTop(), boundingBox.getWidth(), 0));

        Rectangle layoutBox = boundingBox.clone();

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

            occupiedArea.getBoundingBox().commonHorizontalRectangle(layoutResult.getOccupiedArea().getBoundingBox());
            occupiedArea.getBoundingBox().commonVerticalRectangle(layoutResult.getOccupiedArea().getBoundingBox());

            if (layoutResult.getType() == LayoutResult.FULL) {
                layoutBox.addHeight(-layoutResult.getOccupiedArea().getBoundingBox().getHeight());
            }
        }

        removePaddings(occupiedArea.getBoundingBox());
        removeMargins(occupiedArea.getBoundingBox());

        return new LayoutResult(LayoutResult.FULL, occupiedArea);

    }

    @Override
    public Renderer getNextRenderer() {
        return new TableDataRenderer(modelElement);
    }

}
