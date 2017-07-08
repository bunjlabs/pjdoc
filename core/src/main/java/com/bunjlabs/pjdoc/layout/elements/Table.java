package com.bunjlabs.pjdoc.layout.elements;

import com.bunjlabs.pjdoc.layout.render.Renderer;
import com.bunjlabs.pjdoc.layout.render.TableRenderer;
import java.util.Collection;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public class Table extends BlockElement<Table> {

    public Table add(TableRow element) {
        childElements.add(element);
        element.parentElement = this;
        return this;
    }

    public Table addAll(Collection<TableRow> elements) {
        childElements.addAll(elements);
        elements.forEach((e) -> e.parentElement = this);
        return this;
    }

    @Override
    public Renderer createRenderer() {
        return new TableRenderer(this);
    }
}
