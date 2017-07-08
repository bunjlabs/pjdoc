package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.attributes.Attribute;
import com.bunjlabs.pjdoc.layout.elements.Table;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class TableRenderer extends BlockRenderer<Table> {

    public TableRenderer(Table modelElement) {
        super(modelElement);
    }

    @Override
    public LayoutResult layout(LayoutContext layoutContext) {
        int maxChilds = 0;
        float maxChildWidth = 0;

        for (Iterator<Renderer> it = childRenderers.iterator(); it.hasNext();) {
            TableRowRenderer rowRenderer = (TableRowRenderer) it.next();
            maxChilds = Math.max(maxChilds, rowRenderer.childRenderers.size());
        }

        float[] maxChildWidths = new float[maxChilds];

        for (Iterator<Renderer> it = childRenderers.iterator(); it.hasNext();) {
            TableRowRenderer rowRenderer = (TableRowRenderer) it.next();
            for (int i = 0; i < rowRenderer.childRenderers.size(); i++) {
                TableDataRenderer dataRenderer = (TableDataRenderer) rowRenderer.childRenderers.get(i);

                maxChildWidths[i] = Math.max(
                        dataRenderer.getAttribute(Attribute.WIDTH, 0f),
                        maxChildWidths[i]
                );
            }
        }

        int currentChild = 0;
        for (Iterator<Renderer> it = childRenderers.iterator(); it.hasNext(); currentChild++) {
            TableRowRenderer r = (TableRowRenderer) it.next();

            r.setMaxChildNumber(maxChilds);
            r.setMaxChildWidth(maxChildWidths);
        }

        return super.layout(layoutContext);
    }

    @Override
    public Renderer getNextRenderer() {
        return new TableRenderer(modelElement);
    }

}
