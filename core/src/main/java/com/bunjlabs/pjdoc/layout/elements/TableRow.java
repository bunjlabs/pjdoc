package com.bunjlabs.pjdoc.layout.elements;

import com.bunjlabs.pjdoc.layout.render.Renderer;
import com.bunjlabs.pjdoc.layout.render.TableRowRenderer;
import java.util.Collection;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class TableRow extends BlockElement<TableRow> {

    public TableRow add(TableData element) {
        childElements.add(element);
        element.parentElement = this;
        return this;
    }

    public TableRow addAll(Collection<TableData> elements) {
        childElements.addAll(elements);
        elements.forEach((e) -> e.parentElement = this);
        return this;
    }

    @Override
    public Renderer createRenderer() {
        return new TableRowRenderer(this);
    }

}
