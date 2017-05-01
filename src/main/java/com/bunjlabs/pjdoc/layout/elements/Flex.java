package com.bunjlabs.pjdoc.layout.elements;

import com.bunjlabs.pjdoc.layout.render.FlexRenderer;
import com.bunjlabs.pjdoc.layout.render.Renderer;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public final class Flex extends BlockElement<Flex> {

    public Flex add(BlockElement element) {
        childElements.add(element);
        element.parentElement = this;
        return this;
    }

    @Override
    public Renderer createRenderer() {
        return new FlexRenderer(this);
    }
}
