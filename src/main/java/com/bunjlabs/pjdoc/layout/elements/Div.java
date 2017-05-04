package com.bunjlabs.pjdoc.layout.elements;

import com.bunjlabs.pjdoc.layout.render.DivRenderer;
import com.bunjlabs.pjdoc.layout.render.Renderer;
import java.util.Collection;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public final class Div extends BlockElement<Div> {

    public Div add(BlockElement element) {
        childElements.add(element);
        element.parentElement = this;
        return this;
    }

    public Div addAll(Collection<BlockElement> elements) {
        childElements.addAll(elements);
        elements.forEach((e) -> e.parentElement = this);
        return this;
    }

    @Override
    public Renderer createRenderer() {
        return new DivRenderer(this);
    }
}
