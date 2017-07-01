package com.bunjlabs.pjdoc.layout.elements;

import com.bunjlabs.pjdoc.layout.render.Renderer;
import com.bunjlabs.pjdoc.layout.render.TableDataRenderer;
import java.util.Collection;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class TableData extends BlockElement<TableRow> {

    public TableData add(BlockElement element) {
        childElements.add(element);
        element.parentElement = this;
        return this;
    }

    public TableData addAll(Collection<BlockElement> elements) {
        childElements.addAll(elements);
        elements.forEach((e) -> e.parentElement = this);
        return this;
    }

    @Override
    public Renderer createRenderer() {
        return new TableDataRenderer(this);
    }

}
