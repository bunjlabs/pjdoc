package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.LayoutArea;
import com.bunjlabs.pjdoc.layout.Rectangle;
import com.bunjlabs.pjdoc.layout.elements.TableRow;
import java.util.Iterator;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class TableRowRenderer extends BlockRenderer<TableRow> {

    private int maxChildNumber;
    private float maxChildWidth[];

    public TableRowRenderer(TableRow modelElement) {
        super(modelElement);
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        Rectangle boundingBox = layoutContext.getBoundingBox();

        applyMargins(boundingBox);
        applyPaddings(boundingBox);

        occupiedArea = new LayoutArea(layoutContext.getMediaArea().getPageNumber(), new Rectangle(boundingBox.getLeft(), boundingBox.getTop(), boundingBox.getWidth(), 0));

        Rectangle layoutBox = boundingBox.clone();

        float itemsWidth[] = new float[maxChildNumber];
        float itemWidthSumm = 0;
        for (int i = 0; i < itemsWidth.length; i++) {
            if (maxChildWidth[i] > 0) {
                itemsWidth[i] = maxChildWidth[i];
            } else {
                itemsWidth[i] = (layoutBox.getWidth() - itemWidthSumm) / (maxChildNumber - i);
            }
            itemWidthSumm += itemsWidth[i];
        }

        Rectangle itemsLayoutBox = layoutBox.clone();
        float finalHeigth = 0;
        int currentChild = 0;

        for (Iterator<Renderer> it = childRenderers.iterator(); it.hasNext(); currentChild++) {
            Renderer renderer = it.next();
            Rectangle itemLayoutBox = itemsLayoutBox.clone();
            itemLayoutBox.setWidth(itemsWidth[currentChild]);

            LayoutResult layoutResult = renderer.layout(layoutContext.extend(itemLayoutBox));
            finalHeigth = Math.max(finalHeigth, layoutResult.getOccupiedArea().getBoundingBox().getHeight());

            itemsLayoutBox.moveX(itemsWidth[currentChild]);
        }

        occupiedArea.getBoundingBox().setHeight(finalHeigth).moveY(-finalHeigth);

        removePaddings(occupiedArea.getBoundingBox());
        //removeMargins(occupiedArea.getBoundingBox());

        return new LayoutResult(LayoutResult.FULL, occupiedArea);
    }

    @Override
    public Renderer getNextRenderer() {
        return new TableRowRenderer(modelElement);
    }

    protected void setMaxChildNumber(int maxChildNumber) {
        this.maxChildNumber = maxChildNumber;
    }

    protected void setMaxChildWidth(float[] maxChildWidth) {
        this.maxChildWidth = maxChildWidth;
    }

}
