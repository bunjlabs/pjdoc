package com.bunjlabs.pjdoc.layout.elements;

import com.bunjlabs.pjdoc.layout.render.DivRenderer;
import com.bunjlabs.pjdoc.layout.render.Renderer;

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

    @Override
    public Renderer createRenderer() {
        return new DivRenderer(this);
    }
}
