package com.bunjlabs.pjdoc.layout.render;

import com.bunjlabs.pjdoc.layout.elements.Element;
import java.io.IOException;

/**
 *
 * @author Artem Shurygin <artem.shurygin@bunjlabs.com>
 */
public interface ElementRenderer<E extends Element> {

    public void render(E element, ElementRenderContext ctx) throws IOException;
}
